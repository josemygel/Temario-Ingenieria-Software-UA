/*

Este programa demuestra el uso de clave pública para establecer una clave de sesión y un túnel cifrado
en una arquitectura cliente servidor:
	- intercambio de claves con RSA
	- transmisión de mensajes utilizando encoding (JSON, pero puede ser gob, etc.)

El servidor es concurrente, siendo capaz de manejar múltiples clientes simultáneamente.

ejemplos de uso:

go run pub.go srv

go run pub.go cli

*/

package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/rsa"
	"encoding/json"
	"fmt"
	"net"
	"os"
)

// función para comprobar errores (ahorra escritura)
func chk(e error) {
	if e != nil {
		panic(e)
	}
}

func main() {

	fmt.Println("pub.go :: un ejemplo de clave pública en Go.")
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

// Mensaje genérico con un identificador y un argumento asociado
type Msg struct {
	Id  string
	Arg interface{}
}

// gestiona el modo servidor
func server() {

	srv_keys, err := rsa.GenerateKey(rand.Reader, 1024) // generamos un par de claves (privada, pública) para el servidor
	chk(err)
	srv_keys.Precompute() // aceleramos su uso con un precálculo

	ln, err := net.Listen("tcp", "localhost:1337") // escucha en espera de conexión
	chk(err)
	defer ln.Close() // nos aseguramos que cerramos las conexiones aunque el programa falle

	for { // búcle infinito, se sale con ctrl+c
		conn, err := ln.Accept() // para cada nueva petición de conexión
		chk(err)
		go func() { // lanzamos un cierre (lambda, función anónima) en concurrencia

			_, port, err := net.SplitHostPort(conn.RemoteAddr().String()) // obtenemos el puerto remoto para identificar al cliente (decorativo)
			chk(err)

			fmt.Println("conexión: ", conn.LocalAddr(), " <--> ", conn.RemoteAddr())

			var cli_pub rsa.PublicKey // contendrá la clave pública del cliente

			je := json.NewEncoder(conn) // creamos un encoder/decoder de JSON sobre la conexión
			jd := json.NewDecoder(conn)

			err = je.Encode(&srv_keys.PublicKey) // envíamos la clave pública del servidor
			chk(err)

			err = jd.Decode(&cli_pub) // recibimos la clave pública del cliente
			chk(err)

			srv_token := make([]byte, 48) // 384 bits (256 bits de clave + 128 bits para el IV)
			buff := make([]byte, 256)     // contendrá el token cifrado con clave pública (puede ocupar más que el texto en claro)
			rand.Read(srv_token)          // generación del token aleatorio para el servidor

			// ciframos el token del servidor con la clave pública del cliente
			enctoken, err := rsa.EncryptPKCS1v15(rand.Reader, &cli_pub, srv_token)
			chk(err)

			err = je.Encode(&enctoken) // envíamos el token cifrado codificado con JSON
			chk(err)

			err = jd.Decode(&buff) // leemos el token cifrado procedente del cliente
			chk(err)

			// desciframos el token del cliente con nuestra clave privada
			session_key, err := rsa.DecryptPKCS1v15(rand.Reader, srv_keys, buff)
			chk(err)

			// realizamos el XOR entre ambos tokens (cliente y servidor acaban con la misma clave de sesión)
			for i := 0; i < len(srv_token); i++ {
				session_key[i] ^= srv_token[i]
			}

			aesblock, err := aes.NewCipher(session_key[:32]) // cifrador en bloque AES con la clave de sesión
			chk(err)

			aeswr := cipher.StreamWriter{S: cipher.NewCTR(aesblock, session_key[32:48]), W: conn} // un writer AES en modo CTR sobre la conexión
			aesrd := cipher.StreamReader{S: cipher.NewCTR(aesblock, session_key[32:48]), R: conn} // un reader AES en modo CTR sobre la conexión

			// redefinimos los encoder/decoder JSON para que trabajen sobre la conexión cifrada con AES
			je = json.NewEncoder(aeswr)
			jd = json.NewDecoder(aesrd)

			// envíamos un mensaje de HELLO (ejemplo)
			je.Encode(&Msg{Id: "HELLO", Arg: nil})

			// leemos el mensaje de HELLO del cliente y lo imprimimos
			var m Msg
			jd.Decode(&m)
			fmt.Println(m)

			je.Encode(&Msg{Id: "TEST", Arg: "prueba"})
			jd.Decode(&m)
			fmt.Println(m.Arg)

			conn.Close() // cerramos la conexión
			fmt.Println("cierre[", port, "]")

		}()
	}
}

func client() {

	cli_keys, err := rsa.GenerateKey(rand.Reader, 1024) // generamos un par de claves (privada, pública) para el servidor
	chk(err)
	cli_keys.Precompute() // aceleramos su uso con un precálculo

	conn, err := net.Dial("tcp", "localhost:1337") // llamamos al servidor
	chk(err)
	defer conn.Close() // es importante cerrar la conexión al finalizar

	fmt.Println("conectado a ", conn.RemoteAddr())

	var srv_pub rsa.PublicKey // contendrá la clave pública del servidor

	je := json.NewEncoder(conn) // creamos un encoder/decoder de JSON sobre la conexión
	jd := json.NewDecoder(conn)

	err = je.Encode(&cli_keys.PublicKey) // envíamos la clave pública del cliente
	chk(err)

	err = jd.Decode(&srv_pub) // recibimos la clave pública del servidor
	chk(err)

	cli_token := make([]byte, 48) // 384 bits (256 bits de clave + 128 bits para el IV)
	buff := make([]byte, 256)     // contendrá el token cifrado con clave pública (puede ocupar más que el texto en claro)
	rand.Read(cli_token)          // generación del token aleatorio para el cliente

	// ciframos el token del cliente con la clave pública del servidor
	enctoken, err := rsa.EncryptPKCS1v15(rand.Reader, &srv_pub, cli_token)
	chk(err)

	err = je.Encode(&enctoken) // envíamos el token cifrado codificado con JSON
	chk(err)

	err = jd.Decode(&buff) // leemos el token cifrado procedente del servidor
	chk(err)

	// desciframos el token del servidor con nuestra clave privada
	session_key, err := rsa.DecryptPKCS1v15(rand.Reader, cli_keys, buff)
	chk(err)

	// realizamos el XOR entre ambos tokens (cliente y servidor acaban con la misma clave de sesión)
	for i := 0; i < len(cli_token); i++ {
		session_key[i] ^= cli_token[i]
	}

	aesblock, err := aes.NewCipher(session_key[:32]) // cifrador en bloque AES con la clave de sesión
	chk(err)

	aeswr := cipher.StreamWriter{S: cipher.NewCTR(aesblock, session_key[32:48]), W: conn} // un writer AES en modo CTR sobre la conexión
	aesrd := cipher.StreamReader{S: cipher.NewCTR(aesblock, session_key[32:48]), R: conn} // un reader AES en modo CTR sobre la conexión

	// redefinimos los encoder/decoder JSON para que trabajen sobre la conexión cifrada con AES
	je = json.NewEncoder(aeswr)
	jd = json.NewDecoder(aesrd)

	// envíamos un mensaje de HELLO (ejemplo)
	je.Encode(&Msg{Id: "HELLO", Arg: nil})

	// leemos el mensaje de HELLO del servidor y lo imprimimos
	var m Msg
	jd.Decode(&m)
	fmt.Println(m)

	je.Encode(&Msg{Id: "TEST", Arg: "prueba"})
	jd.Decode(&m)
	fmt.Println(m.Arg)
}
