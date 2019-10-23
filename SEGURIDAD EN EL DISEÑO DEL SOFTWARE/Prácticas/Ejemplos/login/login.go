/*

Este programa muestra cómo hacer login y registro entre cliente y servidor,
así como el uso de HTTPS (HTTP sobre TLS) mediante certificados (autofirmados).

Conceptos: JSON, AES-CTR, RSA, compresión, Base64, TLS

ejemplos de uso:

go run login.go srv

go run login.go cli

*/

package main

import (
	"bytes"
	"compress/zlib"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/rsa"
	"crypto/sha512"
	"crypto/tls"
	"encoding/base64"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"net/url"
	"os"

	"golang.org/x/crypto/scrypt"
)

// función para comprobar errores (ahorra escritura)
func chk(e error) {
	if e != nil {
		panic(e)
	}
}

// función para cifrar (con AES en este caso), adjunta el IV al principio
func encrypt(data, key []byte) (out []byte) {
	out = make([]byte, len(data)+16)    // reservamos espacio para el IV al principio
	rand.Read(out[:16])                 // generamos el IV
	blk, err := aes.NewCipher(key)      // cifrador en bloque (AES), usa key
	chk(err)                            // comprobamos el error
	ctr := cipher.NewCTR(blk, out[:16]) // cifrador en flujo: modo CTR, usa IV
	ctr.XORKeyStream(out[16:], data)    // ciframos los datos
	return
}

// función para descifrar (con AES en este caso)
func decrypt(data, key []byte) (out []byte) {
	out = make([]byte, len(data)-16)     // la salida no va a tener el IV
	blk, err := aes.NewCipher(key)       // cifrador en bloque (AES), usa key
	chk(err)                             // comprobamos el error
	ctr := cipher.NewCTR(blk, data[:16]) // cifrador en flujo: modo CTR, usa IV
	ctr.XORKeyStream(out, data[16:])     // desciframos (doble cifrado) los datos
	return
}

// función para comprimir
func compress(data []byte) []byte {
	var b bytes.Buffer      // b contendrá los datos comprimidos (tamaño variable)
	w := zlib.NewWriter(&b) // escritor que comprime sobre b
	w.Write(data)           // escribimos los datos
	w.Close()               // cerramos el escritor (buffering)
	return b.Bytes()        // devolvemos los datos comprimidos
}

// función para descomprimir
func decompress(data []byte) []byte {
	var b bytes.Buffer // b contendrá los datos descomprimidos

	r, err := zlib.NewReader(bytes.NewReader(data)) // lector descomprime al leer

	chk(err)         // comprobamos el error
	io.Copy(&b, r)   // copiamos del descompresor (r) al buffer (b)
	r.Close()        // cerramos el lector (buffering)
	return b.Bytes() // devolvemos los datos descomprimidos
}

// función para codificar de []bytes a string (Base64)
func encode64(data []byte) string {
	return base64.StdEncoding.EncodeToString(data) // sólo utiliza caracteres "imprimibles"
}

// función para decodificar de string a []bytes (Base64)
func decode64(s string) []byte {
	b, err := base64.StdEncoding.DecodeString(s) // recupera el formato original
	chk(err)                                     // comprobamos el error
	return b                                     // devolvemos los datos originales
}

// respuesta del servidor
type resp struct {
	Ok  bool   // true -> correcto, false -> error
	Msg string // mensaje adicional
}

// función para escribir una respuesta del servidor
func response(w io.Writer, ok bool, msg string) {
	r := resp{Ok: ok, Msg: msg}    // formateamos respuesta
	rJSON, err := json.Marshal(&r) // codificamos en JSON
	chk(err)                       // comprobamos error
	w.Write(rJSON)                 // escribimos el JSON resultante
}

func main() {

	fmt.Println("login.go :: un ejemplo de login mediante TLS/HTTP en Go.")
	s := "Introduce srv para funcionalidad de servidor y cli para funcionalidad de cliente"

	if len(os.Args) > 1 {
		switch os.Args[1] {
		case "srv":
			fmt.Println("Entrando en modo servidor...")
			server()
		case "cli":
			fmt.Println("Entrando en modo cliente...")
			client()
		default:
			fmt.Println("Parámetro '", os.Args[1], "' desconocido. ", s)
		}
	} else {
		fmt.Println(s)
	}
}

/***
SERVIDOR
***/

// ejemplo de tipo para un usuario
type user struct {
	Name string            // nombre de usuario
	Hash []byte            // hash de la contraseña
	Salt []byte            // sal para la contraseña
	Data map[string]string // datos adicionales del usuario
}

// mapa con todos los usuarios
// (se podría codificar en JSON y escribir/leer de disco para persistencia)
var gUsers map[string]user

// gestiona el modo servidor
func server() {
	gUsers = make(map[string]user) // inicializamos mapa de usuarios

	http.HandleFunc("/", handler) // asignamos un handler global

	// escuchamos el puerto 10443 con https y comprobamos el error
	// Para generar certificados autofirmados con openssl usar:
	//    openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -days 365 -nodes -subj "/C=ES/ST=Alicante/L=Alicante/O=UA/OU=Org/CN=www.ua.com"
	chk(http.ListenAndServeTLS(":10443", "cert.pem", "key.pem", nil))
}

func handler(w http.ResponseWriter, req *http.Request) {
	req.ParseForm()                              // es necesario parsear el formulario
	w.Header().Set("Content-Type", "text/plain") // cabecera estándar

	switch req.Form.Get("cmd") { // comprobamos comando desde el cliente
	case "register": // ** registro
		u := user{}
		u.Name = req.Form.Get("user")              // nombre
		u.Salt = make([]byte, 16)                  // sal (16 bytes == 128 bits)
		rand.Read(u.Salt)                          // la sal es aleatoria
		u.Data = make(map[string]string)           // reservamos mapa de datos de usuario
		u.Data["private"] = req.Form.Get("prikey") // clave privada
		u.Data["public"] = req.Form.Get("pubkey")  // clave pública
		password := decode64(req.Form.Get("pass")) // contraseña (keyLogin)

		// "hasheamos" la contraseña con scrypt
		u.Hash, _ = scrypt.Key(password, u.Salt, 16384, 8, 1, 32)

		_, ok := gUsers[u.Name] // ¿existe ya el usuario?
		if ok {
			response(w, false, "Usuario ya registrado")
		} else {
			gUsers[u.Name] = u
			response(w, true, "Usuario registrado")
		}

	case "login": // ** login
		u, ok := gUsers[req.Form.Get("user")] // ¿existe ya el usuario?
		if !ok {
			response(w, false, "Usuario inexistente")
			return
		}

		password := decode64(req.Form.Get("pass"))               // obtenemos la contraseña
		hash, _ := scrypt.Key(password, u.Salt, 16384, 8, 1, 32) // scrypt(contraseña)
		if bytes.Compare(u.Hash, hash) != 0 {                    // comparamos
			response(w, false, "Credenciales inválidas")
			return
		}
		response(w, true, "Credenciales válidas")

	default:
		response(w, false, "Comando inválido")
	}

}

/***
CLIENTE
***/

// gestiona el modo cliente
func client() {

	/* creamos un cliente especial que no comprueba la validez de los certificados
	esto es necesario por que usamos certificados autofirmados (para pruebas) */
	tr := &http.Transport{
		TLSClientConfig: &tls.Config{InsecureSkipVerify: true},
	}
	client := &http.Client{Transport: tr}

	// hash con SHA512 de la contraseña
	keyClient := sha512.Sum512([]byte("contraseña del cliente"))
	keyLogin := keyClient[:32]  // una mitad para el login (256 bits)
	keyData := keyClient[32:64] // la otra para los datos (256 bits)

	// generamos un par de claves (privada, pública) para el servidor
	pkClient, err := rsa.GenerateKey(rand.Reader, 1024)
	chk(err)
	pkClient.Precompute() // aceleramos su uso con un precálculo

	pkJSON, err := json.Marshal(&pkClient) // codificamos con JSON
	chk(err)

	keyPub := pkClient.Public()           // extraemos la clave pública por separado
	pubJSON, err := json.Marshal(&keyPub) // y codificamos con JSON
	chk(err)

	// ** ejemplo de registro
	data := url.Values{}                 // estructura para contener los valores
	data.Set("cmd", "register")          // comando (string)
	data.Set("user", "usuario")          // usuario (string)
	data.Set("pass", encode64(keyLogin)) // "contraseña" a base64

	// comprimimos y codificamos la clave pública
	data.Set("pubkey", encode64(compress(pubJSON)))

	// comprimimos, ciframos y codificamos la clave privada
	data.Set("prikey", encode64(encrypt(compress(pkJSON), keyData)))

	r, err := client.PostForm("https://localhost:10443", data) // enviamos por POST
	chk(err)
	io.Copy(os.Stdout, r.Body) // mostramos el cuerpo de la respuesta (es un reader)
	fmt.Println()

	// ** ejemplo de login
	data = url.Values{}
	data.Set("cmd", "login")             // comando (string)
	data.Set("user", "usuario")          // usuario (string)
	data.Set("pass", encode64(keyLogin)) // contraseña (a base64 porque es []byte)
	r, err = client.PostForm("https://localhost:10443", data)
	chk(err)
	io.Copy(os.Stdout, r.Body) // mostramos el cuerpo de la respuesta (es un reader)
	fmt.Println()
}
