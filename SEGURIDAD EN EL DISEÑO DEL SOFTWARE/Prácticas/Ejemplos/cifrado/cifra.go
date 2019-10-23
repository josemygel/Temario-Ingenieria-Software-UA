package main

import (
	"compress/zlib"
	"crypto/aes"
	"crypto/cipher"
	"crypto/des"
	"crypto/rc4"
	"crypto/sha256"
	"errors"
	"flag"
	"fmt"
	"io"
	"os"
	"strings"
	"time"

	"golang.org/x/crypto/blowfish"
	"golang.org/x/crypto/salsa20"
	"golang.org/x/crypto/twofish"
)

func check(e error) {
	if e != nil {
		panic(e)
	}
}

type salsaStream struct {
	nonce []byte
	key   [32]byte
}

func (s *salsaStream) XORKeyStream(dst, src []byte) {
	salsa20.XORKeyStream(dst, src, s.nonce, &s.key)
}

func NewSalsa(key, nonce []byte) (s *salsaStream, e error) {
	if len(key) != 32 {
		e = errors.New("salsa20: la clave debe tener 32 bytes")
		return
	}
	if (len(nonce) != 8) && (len(nonce) != 24) {
		e = errors.New("salsa20: el nonce debe ser de 8 o 24 bytes")
		return
	}

	s = new(salsaStream)
	s.nonce = make([]byte, len(nonce), len(nonce))

	for i := 0; i < len(key); i++ {
		s.key[i] = key[i]
	}

	for i := 0; i < len(nonce); i++ {
		s.nonce[i] = nonce[i]
	}
	return s, e
}

func main() {

	//flags
	pM := flag.String("m", "C", "modo: C cifrar (por defecto), D descifrar")
	pA := flag.String("a", "AES128", "algoritmo de cifrado (AES128, AES256, DES, TDES, RC4, BLOWFISH, SALSA20, TWOFISH)")
	pK := flag.String("k", "", "clave para cifrar o descifrar")
	pI := flag.String("i", "", "fichero de entrada (o entrada estándar)")
	pO := flag.String("o", "", "fichero de salida (o salida estándar)")
	pC := flag.Bool("c", true, "compresión (activada por defecto")

	flag.Parse()

	if *pK == "" {
		flag.PrintDefaults()
		os.Exit(1)
	}

	// hash de clave e IV
	h := sha256.New()
	h.Reset()
	_, err := h.Write([]byte(*pK))
	check(err)
	key := h.Sum(nil)

	h.Reset()
	_, err = h.Write([]byte("<inicializar>"))
	check(err)
	iv := h.Sum(nil)

	// modo de operación
	var cifrando bool
	if strings.ToUpper(*pM) == "C" {
		cifrando = true
	} else if strings.ToUpper(*pM) == "D" {
		cifrando = false
	} else {
		flag.PrintDefaults()
		os.Exit(1)
	}

	//ficheros
	var fin, fout *os.File

	if *pI == "" {
		fin = os.Stdin
	} else {
		fin, err = os.Open(*pI)
		check(err)
		defer fin.Close()
	}

	if *pO == "" {
		fout = os.Stdout
	} else {
		fout, err = os.Create(*pO)
		check(err)
		defer fout.Close()
	}

	t := time.Now()
	//cifrar y descifrar
	var S cipher.Stream
	switch strings.ToUpper(*pA) {

	case "AES128":
		block, err := aes.NewCipher(key[:16])
		check(err)
		S = cipher.NewCTR(block, iv[:16])

	case "AES256":
		block, err := aes.NewCipher(key)
		check(err)
		S = cipher.NewCTR(block, iv[:16])

	case "DES":
		block, err := des.NewCipher(key[:8])
		check(err)
		S = cipher.NewCTR(block, iv[:8])

	case "TDES":
		block, err := des.NewTripleDESCipher(key[:24])
		check(err)
		S = cipher.NewCTR(block, iv[:8])

	case "RC4":
		c, err := rc4.NewCipher(key)
		check(err)
		S = c

	case "BLOWFISH":
		block, err := blowfish.NewCipher(key)
		check(err)
		S = cipher.NewCTR(block, iv[:8])

	case "TWOFISH":
		block, err := twofish.NewCipher(key)
		check(err)
		S = cipher.NewCTR(block, iv[:16])

	case "SALSA20":
		c, err := NewSalsa(key, iv[:24])
		check(err)
		S = c

	default:
		flag.PrintDefaults()
		os.Exit(1)
	}

	var rd io.Reader
	var wr io.WriteCloser

	if cifrando {
		var enc cipher.StreamWriter
		enc.S = S
		enc.W = fout

		rd = fin

		if *pC {
			wr = zlib.NewWriter(enc)
		} else {
			wr = enc
		}

	} else {
		var dec cipher.StreamReader
		dec.S = S
		dec.R = fin

		wr = fout

		if *pC {
			rd, err = zlib.NewReader(dec)
			check(err)
		} else {
			rd = dec
		}
	}

	_, err = io.Copy(wr, rd)
	check(err)
	wr.Close()

	fmt.Println(time.Since(t))

}
