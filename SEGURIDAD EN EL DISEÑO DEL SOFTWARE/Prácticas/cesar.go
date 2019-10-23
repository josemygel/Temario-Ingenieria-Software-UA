package main

import (
	"fmt"
	"os"
	"strconv"
	"unicode"
)

var abc = map[rune]int{
	'A': 0, 'B': 1, 'C': 2, 'D': 3, 'E': 4, 'F': 5, 'G': 6, 'H': 7, 'I': 8, 'J': 9,
	'K': 10, 'L': 11, 'M': 12, 'N': 13, 'Ñ': 14, 'O': 15, 'P': 16, 'Q': 17, 'R': 18, 'S': 19,
	'T': 20, 'U': 21, 'V': 22, 'W': 23, 'X': 24, 'Y': 25, 'Z': 26}

func search(alf map[rune]int, c rune) rune {
	//fmt.Printf("\n\nINIT\nC=%c \n", c)
	//for CHAR, INT

	C := unicode.ToUpper(c) // pasamos a mayúsculas

	valor, ok := abc[C]
	if ok {
		for a, b := range alf {
			if b == valor {
				return a
			}
		}
	}

	//-1 == no valido
	return C
}

func crearAlfabeto(key int) map[rune]int {
	alfabeto := map[rune]int{}

	for valor, i, runa := 0, 0, rune('A'); i < len(abc); i++ {
		valor = (key + abc[runa]) % 27
		runa = rune(runa + 1)

		alfabeto[runa] = valor
	}

	return alfabeto
}

func main() {
	var fin *os.File  // fichero de entrada
	var fout *os.File // fichero de salida
	var err error     // receptor de error

	if len(os.Args) <= 2 { // no hay parámetros, usamos entrada (teclado) y salida estándar (pantalla)
		fin = os.Stdin
		fout = os.Stdout

	} else if len(os.Args) == 4 { // tenemos los nombres de los ficheros de entrada y salida en los parámetros
		fin, err = os.Open(os.Args[2]) // abrimos el primer fichero (entrada)
		if err != nil {
			panic(err)
		}
		defer fin.Close()

		fout, err = os.Create(os.Args[3]) // abrimos el segundo fichero (salida)
		if err != nil {
			panic(err)
		}
		defer fout.Close()
	} else { // error de parámetros
		fmt.Println("Número de parámetros incorrecto: se espera los ficheros de origen y destino (2 y 3, opcionales)")
		os.Exit(1)
	}

	//Obtenemos el key
	key := 0
	if len(os.Args) >= 2 {
		key, err = strconv.Atoi(os.Args[1])
		if err != nil {
			panic(err)
			os.Exit(1)
		}
	}

	// alfabeto con el que vamos a trabajar
	alfabeto := crearAlfabeto(key)

	if len(os.Args) <= 2 {
		fmt.Println("\nWrite something:")
	}

	for { // bucle infinito
		var c rune // carácter a leer

		_, err = fmt.Fscanf(fin, "%c", &c) // lectura de la entrada

		if err != nil { // si hay error (fin de fichero)
			break //salimos del bucle
		}

		C := search(alfabeto, c) // pasamos a mayúsculas

		_, ok := alfabeto[C]

		if ok { // en tal caso imprimimos en la salida
			fmt.Fprintf(fout, "%c", C)

		} else if len(os.Args) <= 2 && C == 10 {
			fmt.Println("\n\nWrite more:")

		} else {
			fmt.Println()
		}
	}
}
