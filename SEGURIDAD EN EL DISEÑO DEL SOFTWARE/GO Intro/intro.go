package main

import (
	"crypto/md5"
	"crypto/sha512"
	"encoding/hex"
	"fmt"
	"os"
	"time"
)

/*

La siguiente funcion y la siguiente linea son equivaentes:
1) Crea el crypto (MD5) con md5.New()
2) Añadimos al Crypto los bytes para generar el hash pasandole []byte(elTexto)
3)con hex.EncodeToString(BYTES) convertimos los bytes a una cadena en hexadecimal =)
	hex.EncodeToString(md5.New().Sum([]byte(pw)))

// GetMD5Hash is for convert a String in a MD5 HASH
func GetMD5Hash(text string) string {
	hasher := md5.New()
	hasher.Write([]byte(text))
	return hex.EncodeToString(hasher.Sum(nil))
}

// GetMD5Hash is for convert a String in a SHA512 HASH
func GetSHA512Hash(text string) string {
	hasher := sha512.New()
	hasher.Write([]byte(text))
	return hex.EncodeToString(hasher.Sum(nil))
}
*/

func main() {
	var h = int64(time.Now().Nanosecond())

	fmt.Println(h)
	var pw string
	var pwMd5 = hex.EncodeToString(md5.New().Sum([]byte(pw)))
	var pwSha512 = hex.EncodeToString(sha512.New().Sum([]byte(pw)))
	//	var pw_sha512 string

	fmt.Print("Enter the password: ")
	fmt.Fscanf(os.Stdin, "%s", &pw)

	fmt.Println("\nThe password is " + pw)
	fmt.Println("The password encrypted with MD5 is " + pwMd5)
	fmt.Println("The password encrypted with SHA512 is " + pwSha512)
}
