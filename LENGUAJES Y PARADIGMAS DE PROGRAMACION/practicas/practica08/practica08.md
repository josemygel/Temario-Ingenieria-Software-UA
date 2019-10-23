## Práctica 8: Programación funcional en Swift (1)

Para entregar la práctica debes subir a Moodle el fichero
`practica08.swift` con una cabecera inicial con tu nombre y apellidos,
y las soluciones de cada ejercicio separadas por comentarios. Cada
solución debe incluir:

- La **definición de las funciones** que resuelven el ejercicio.
- Una visualización por pantalla de todos los ejemplos incluidos en el
  enunciado que **demuestren qué hace la función**.

### Bibliografía recomendada para la práctica ###

Antes de proceder a realizar los ejercicios de la práctica, debes
completar el
[seminario de Swift](https://github.com/domingogallardo/apuntes-lpp/blob/master/seminarios/seminario2-swift/seminario2-swift.md)
hasta el apartado de **Enumeraciones**. Y, evidentemente, los [apuntes
de teoría](https://github.com/domingogallardo/apuntes-lpp/blob/master/teoria/tema05-programacion-funcional-swift/tema05-programacion-funcional-swift.md) de la semana pasada.

En la práctica hay más de un ejercicio en el que tienes que trabajar
con Arrays. Es conveniente que consultes el apartado sobre Arrays en
la página [Collection
Types](https://developer.apple.com/library/content/documentation/Swift/Conceptual/Swift_Programming_Language/CollectionTypes.html#//apple_ref/doc/uid/TP40014097-CH8-ID105)
de la guía de Swift.


### Ejercicio 1 ###

Escribe una función en Swift `obtenerFrencuencias` que permita calcular las
frecuencias de un conjunto de respuestas, números entre 0 y 9, que se
guardan en un array de `Int`. Y otra `imprimir` que permita representar las
frecuencias calculadas en forma de histograma.

Como ejemplo de uso de las funciones puedes ver el siguiente
código. El parámetro `maxAsteriscos` define el número de asteriscos
con el que representaremos la frecuencia máxima en la gráfica de
histogramas. Todos los demás asteriscos están escalados
proporcionalmente a este número máximo.


```swift
let respuestas = [0,0,1,1,2,1,2,3,5,1,2,2,2,6]
print("Valores: \(respuestas)" )
let frec = obtenerFrecuencias(respuestas: respuestas)
print("Frecuencias: \(frec)")
print("\nHistograma")
print("----------")
imprimir(frecuencias: frec, maxAsteriscos: 10)
```

Sacaría por pantalla lo siguiente:


```
Valores: [0, 0, 1, 1, 2, 1, 2, 3, 5, 1, 2, 2, 2, 6]
Frecuencias: [2, 4, 5, 1, 0, 1, 1, 0, 0, 0]

Histograma
----------
0: ****
1: ********
2: **********
3: **
4:
5: **
6: **
7:
8:
9:
```



### Ejercicio 2 ###

Implementa en Swift la **función recursiva**
`compruebaParejas(_:funcion:)` con el siguiente perfil:

```
([Int], (Int) -> Int) -> [(Int, Int)]
```

La función recibe dos parámetros: un `Array` de enteros y una función
que recibe un entero y devuelve un entero. La función devolverá un
array de tuplas que contiene las tuplas formadas por aquellos números
contiguos del primer array que cumplan que el número es el resultado
de aplicar la función al número situado en la posición anterior.

Ejemplo:

```swift
func cuadrado(x: Int) -> Int {
   return x * x
}
print(compruebaParejas([2, 4, 16, 5, 10, 100, 105], funcion: cuadrado))
// Imprime [(2,4), (4,16), (10,100)]
```


### Ejercicio 3 ###

Implementa en Swift un tipo enumerado recursivo que permita construir
árboles binarios de enteros. El enumerado debe tener 

- un caso en el que guardar tres valores: un `Int` y dos árboles
binarios (el hijo izquierdo y el hijo derecho)
- otro caso constante: un árbol binario vacío 

Llamaremos al tipo `ArbolBinario` y a los casos `nodo` y `vacio`.

Impleméntalo de forma que el siguiente ejemplo funcione correctamente:

```swift
let arbol: ArbolBinario = .nodo(8, .nodo(2, .vacio, .vacio), .nodo(12, .vacio, .vacio))
```

Implementa también la función `suma(arbolb:)` que reciba una instancia de
árbol binario y devuelva la suma de todos sus nodos:

```swift
print(suma(arbolb: arbol))
// Imprime: 22
```

### Ejercicio 4 ###

Implementa en Swift la **función recursiva**
`coinciden(parejas: [(Int,Int)], funcion: (Int)->Int)` que devuelve
un array de booleanos que indica si el resultado de aplicar la función
al primer número de cada pareja coincide con el segundo.

```swift
let array = [(2,4), (4,14), (4,16), (5,25), (10,100)]
func cuadrado(x: Int) -> Int {
   return x * x
}
print("Resultado coinciden:  \(coinciden(parejas: array, funcion: cuadrado))\n")
// Imprime: Resultado coinciden:  [true, false, true, true, true]
```



### Ejercicio 5 ###

Supongamos que estamos escribiendo un programa que debe tratar
movimientos de cuentas bancarias. Define un enumerado `Movimiento `
con valores asociados con el que podamos representar:

- Depósito (valor asociado: `(Double)`)
- Cargo de un recibo (valor asociado: `(String, Double)`)
- Cajero (valor asociado: `(Double)`)

Y define la función `aplica(movimientos:[Movimiento])` que reciba un
array de movimientos y devuelva una pareja con el dinero resultante de acumular todos
los movimientos y un array de Strings con todos los cargos realizados.

Ejemplo:


```swift
let movimientos: [Movimiento] = [.deposito(830.0), .cargoRecibo("Gimnasio", 45.0), .deposito(400.0), .cajero(100.0), .cargoRecibo("Fnac", 38.70)]
print(aplica(movimientos: movimientos))
//Imprime (1046.3, ["Gimnasio", "Fnac"])
```


### Ejercicio 6 ###

Implementa en Swift un tipo enumerado recursivo que permita construir
árboles genéricos de enteros usando el mismo enfoque que en Scheme: un
nodo está formado por un dato (un `Int`) y una colección de árboles
hijos. Llamaremos al tipo `Arbol`.

Impleméntalo de forma que el siguiente ejemplo funcione correctamente:

```swift

/*
Definimos el árbol

    10
   / | \
  3  5  8
  |
  1

*/

let arbol1 = Arbol.nodo(10, [])
let arbol3 = Arbol.nodo(3, [arbol1])
let arbol5 = Arbol.nodo(5, [])
let arbol8 = Arbol.nodo(8, [])
let arbol10 = Arbol.nodo(10, [arbol3, arbol5, arbol8])
```

Implementa también la función `suma(arbol:cumplen:)` que reciba una instancia de
árbol genérico y una función `(Int) -> Bool` que comprueba una
condición sobre el nodo. La función debe devolver la suma de todos los
nodos del árbol que cumplan la condición. 

Implementa la función usando la misma estrategia que ya utilizamos en
Scheme de definir una función auxiliar `suma(bosque:cumplen:)` y una recursión
mutua.


```swift

func esPar(x: Int) -> Bool {
    return x % 2 == 0
}

print("La suma del árbol genérico es: \(suma(arbol: arbol10, cumplen: esPar))")
// Imprime: La suma del árbol genérico es: 28
```


----

Lenguajes y Paradigmas de Programación, curso 2017-18  
© Departamento Ciencia de la Computación e Inteligencia Artificial, Universidad de Alicante  
Domingo Gallardo, Cristina Pomares, Antonio Botía, Francisco Martínez





