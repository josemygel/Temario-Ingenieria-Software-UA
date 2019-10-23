/*

Este programa demuestra una arquitectura cliente servidor sencilla.
El cliente envía líneas desde la entrada estandar y el servidor le devuelve un reconomiento de llegada (acknowledge).
El servidor es concurrente, siendo capaz de manejar múltiples clientes simultáneamente.
Las entradas se procesan mediante un scanner (bufio).

ejemplos de uso:

go run cnx.go srv

go run cnx.go cli

*/

package main

import (
	"bufio"
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

	fmt.Println("cnx.go :: un ejemplo para gestionar conexiones TCP en Go.")
	if len(os.Args) > 1 {
		switch os.Args[1] {
		case "srv":
			fmt.Println("Entrando en modo servidor...")
			server()
		case "cli":
			fmt.Println("Entrando en modo cliente...")
			client()
		default:
			fmt.Println("Parámetro '", os.Args[1], "' desconocido. Introduce srv para funcionalidad de servidor y cli para funcionalidad de cliente")
		}
	} else {
		fmt.Println("Introduce srv para funcionalidad de servidor y cli para funcionalidad de cliente")
	}
}

// gestiona el modo servidor
func server() {
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

			scanner := bufio.NewScanner(conn) // el scanner nos permite trabajar con la entrada línea a línea (por defecto)

			for scanner.Scan() { // escaneamos la conexión
				fmt.Println("cliente[", port, "]: ", scanner.Text()) // mostramos el mensaje del cliente
				fmt.Fprintln(conn, "ack: ", scanner.Text())          // enviamos ack al cliente
			}

			conn.Close() // cerramos al finalizar el cliente (EOF se envía con ctrl+d o ctrl+z según el sistema)
			fmt.Println("cierre[", port, "]")
		}()
	}
}

func client() {
	conn, err := net.Dial("tcp", "localhost:1337") // llamamos al servidor
	chk(err)
	defer conn.Close() // es importante cerrar la conexión al finalizar

	fmt.Println("conectado a ", conn.RemoteAddr())

	keyscan := bufio.NewScanner(os.Stdin) // scanner para la entrada estándar (teclado)
	netscan := bufio.NewScanner(conn)     // scanner para la conexión (datos desde el servidor)

	for keyscan.Scan() { // escaneamos la entrada
		fmt.Fprintln(conn, keyscan.Text())         // enviamos la entrada al servidor
		netscan.Scan()                             // escaneamos la conexión
		fmt.Println("servidor: " + netscan.Text()) // mostramos mensaje desde el servidor
	}

}
