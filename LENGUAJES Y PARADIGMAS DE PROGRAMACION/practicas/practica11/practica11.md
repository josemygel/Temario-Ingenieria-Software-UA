## Práctica 11: Programación Orientada a Objetos en Swift (2)

Para entregar la práctica debes subir a Moodle el fichero
`practica11.swift` con una cabecera inicial con tu nombre y apellidos,
y las soluciones de cada ejercicio separadas por comentarios.


### Ejercicio 1 ###

a) El siguiente código tiene errores. Intenta descubrir cuáles son sin
utilizar el compilador. Prueba distintas formas de arreglar el código
cambiando lo mínimo posible de lo ya definido (por ejemplo, no debes
añadir nuevas propiedades en `MiStruct`). Compruébalo con el compilador.

```swift
protocol A {
    var a: String {get set}
    func foo(a: Int, b: Int) -> Int?
}

protocol B {
    mutating func bar()
}

struct MiStruct: A, B {
    let a = 10
    func foo(valor1 a: Int, valor2 b: Int) -> Int {
        let res = a > 10 ? a: b
        return res
    }
}
```

b) Repasa el protocolo `GeneradorNumerosAleatorios` visto en
teoría. Define otra implementación del protocol llamada
`GeneradorFake` en la que siempre se devuelva el número `0.1`. Cambia
el ejemplo del dado para que utilice este generador y comprueba que
siempre devuelve el número 1.

c) Supongamos la estructura `Equipo` que aparece a continuación que
representa un equipo en una competición deportiva: 

```swift
struct Equipo {
    let puntos: Int
    let nombre: String
}
```

Debes hacer que la estructura se ajuste al protocolo `Comparable` para
poder comparar dos equipos . Consulta el protocolo en [documentación
de
Swift](https://developer.apple.com/documentation/swift/comparable). Un
equipo será menor que otro cuando tenga menos puntos. En el caso en
que ambos tengan los mismos puntos, será menor el que tenga menor
nombre en orden alfabético.

Una vez definidos los operadores necesarios comprueba que funcionan
correctamente creando varios equipos, insertándolos en un array y
llamando al método `sorted`.


## Ejercicio 2 ##


a) Completa el bucle con el código que comprueba el tipo de la variable
`i` e imprime su propiedad `x` y su propiedad `b` o `c`, dependiendo
de su tipo.

```swift
protocol X {
   var x: Int { get }
}
class Xb: A {
   var x = 0
   var b = 0
}
class Xc: A {
   var x = 1
   var c = 0
}

var array: [X] = [Xb(), Xc()]
for i in array {
   //
   // Código a completar
   //
}

// debe imprimir:
// x: 0, b: 0
// x: 1, c: 0
```

b) Completa el código que hay a continuación para que compile
correctamente y aparezca en pantalla el resultado que se muestra.

Resultado:

```swift
0.0
300.0
```

Código:

```swift

protocol TieneVelocidad {
    func velocidadActual () -> Double
}

class Vehiculo {
    var velocidad = 0.0
    func velocidadActual() -> Double {
        return velocidad
    }
}

class Tren {
    static let velocidadEnMarcha = 300.0
    var pasajeros = 0
    var enMarcha = false
}

//
// Código a completar
//

var vehiculo1 = Vehiculo()
var tren1 = Tren()
tren1.enMarcha = true

let transportes: [TieneVelocidad] = [vehiculo1, tren1]

for i in transportes {
    print(i.velocidadActual())
}
```

c) Define una extensión del enum `Arbol` del ejercicio 2 de la
práctica 9 en la que se implemente el método `min` que devuelva el
menor elemento del árbol. En la extensión debes especificar que el
tipo de los elementos del árbol deben cumplir el protocolo
`Comparable`, para poder realizar la comparación y obtener el mínimo.

Haz una prueba con un árbol de equipos, objetos de la estructura `Equipo`
definida en el apartado anterior.


### Ejercicio 3

Continuamos con el ejercicio de las figuras geométricas de la
práctica anterior.

Comienza incluyendo en la práctica el código de todas las definiciones
de las estructuras y clases geométricas: `Punto`, `Tamaño`,
`Rectangulo` y `Circulo`.

Una vez incluido debes realizar lo siguiente.


#### Protocolo figura

Define el protocolo `Figura` que contiene:

- Propiedad de lectura y escritura `centro` (`Punto`), que define el
  centro de la figura.
- Propiedades de sólo lectura `area` (`Double`) y `tamaño` (`Tamaño`)
  que devuelven el tamaño (alto y ancho) de la figura.

Consideraremos el tamaño de un triángulo calculando las diferencias
entre las coordenadas mínimas y máximas en x e y.


#### Extensiones

- Define las extensiones necesarias para que las clases `Rectangulo`,
y `Circulo` se ajusten al protocolo `Figura`, añadiendo el
código de implementación necesario.

- Añade mediante una extensión al protocolo `Figura` la propiedad
  calculada `descripcion` que devuelva un `String` con el centro y el
  área de la figura.

#### Clase `AlmacenFiguras`

- Reescribe la clase `AlmacenFiguras` y define en ella **una única
propiedad** `figuras` que contenga todas las figuras que se vayan
creando. Reescribe también la implementación de `areaTotal`. Modifica
los inicializadores en las clases geométricas para que se incluya la
figura recién creada en esta propiedad.

- Escribe el método de clase `cuentaTipos(figuras: [Figura]) -> (Int, Int, Int)`
que recibe un array de figuras y devuelve una tupla con tres enteros:
número de rectángulos, número de círculos y número de triángulos. La
función debe imprimir por cada figura del array, su descripción
por defecto proporcionada por el protocolo y el tipo de figura y sus
características específicas.

Por ejemplo:

```
- Descripción de la figura: Una figura con centro Punto(x: 7.0, y: 3.5) y área 50.0
  Rectangulo con origen Punto(x: 2.0, y: 1.0) y tamaño Tamaño(ancho: 10.0, alto: 5.0)
- Descripción de la figura: Una figura con centro Punto(x: 0.0, y: 2.0) y área 78.5398163397448
  Circulo con centro Punto(x: 0.0, y: 2.0) y radio 5.0
```

#### Cumplimiento de `Equatable`

Implementa las extensiones necesarias para que las clases geométricas
definidas en el ejercicio cumplan el protocolo `Equatable`.



----

Lenguajes y Paradigmas de Programación, curso 2017-18  
© Departamento Ciencia de la Computación e Inteligencia Artificial, Universidad de Alicante  
Domingo Gallardo, Cristina Pomares, Antonio Botía, Francisco Martínez

