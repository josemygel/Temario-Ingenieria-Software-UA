## Práctica 10: Programación Orientada a Objetos en Swift 1

Para entregar la práctica debes subir a Moodle el fichero
`practica10.swift` con una cabecera inicial con tu nombre y apellidos,
y las soluciones de cada ejercicio separadas por comentarios.


### Ejercicio 1 ###

a) El siguiente código usa observadores de propiedades y una variable
del tipo (estática). 

¿Qué se imprime al final de su ejecución? Reflexiona sobre el
funcionamiento del código, compruébalo con el compilador y experimenta
haciendo cambios y comprobando el resultado.

```swift
struct Valor {
    var valor: Int = 0 {
        willSet {
            Valor.z += newValue
        }        
        didSet {
            if valor > 10 {
                valor = 10
            }
        }
    }
    static var z = 0
}

var c1 = Valor()
var c2 = Valor()
c1.valor = 20
c2.valor = 8
print(c1.valor + c2.valor + Valor.z)
```

b) El siguiente código utiliza la inicialización de una clase. 

¿Qué errores tiene? ¿Qué se imprimiría en su ejecución una vez
arreglado? Intenta descubrirlo sin utilizar el compilador. Prueba
distintas formas de arreglar el código cambiando lo mínimo posible de
lo ya definido (por ejemplo, no debes cambiar la función
`prueba`). Compruébalo con el compilador.

```swift

func prueba(x: Int) -> Int? {
    if x < 100 {
        return nil
    } else {
        return x
    }
}

class A {
    var a = prueba(x: 100)
    var b, c: Int
    init() {}
    init(b: Int) {
        self.b = c
    }
}

let a = A(b: 0)
let b: Int = a.a
print("El valor de a.a es: \(b)")
```

c) Escribe un ejemplo de código en el que definas una relación de
herencia entre una clase base y una clase derivada. Comprueba en el
código que un objeto de la clase derivada hereda las propiedades y
métodos de la clase base.

Investiga sobre lo el funcionamiento de la herencia en Swift. Escribe
ejemplos en donde compruebes este funcionamiento. Algunos ejemplos de
preguntas que puedes investigar (puedes añadir tú más preguntas):

- ¿Se puede sobreescribir el valor de una propiedad almacenada? ¿Y
calculada? 
- ¿Se puede añadir un observador a una propiedad de la clase base en
  una clase derivada?
- ¿Hereda la clase derivada propiedades y métodos estáticos de la clase base?
- ¿Cómo se puede llamar a la implementación de un método de la clase
  base en una sobreescritura de ese mismo método en la clase derivada?

### Ejercicio 2 ###

Supongamos la siguiente clase `MisPalabras`:


```swift
class MisPalabras {
    var guardadas: [String] = []
    func guarda(palabra: String) {
        guardadas = guardadas + [palabra]
    }
}

let palabras = MisPalabras()
palabras.guarda(palabra: "Hola")
palabras.guarda(palabra: "me")
palabras.guarda(palabra: "llamo")
palabras.guarda(palabra: "Yolanda")
print(palabras.guardadas)
// ["Hola", "me", "llamo", "Yolanda"]
```

Debes añadir una **propiedad calculada** `logitud` que devuelva la suma de las
longitudes de todas las palabras guardadas (usa una función de orden
superior para calcular esta suma). 

Haz también que sea una **propiedad modificable** de la siguiente forma:

- Si se intenta asignar un valor mayor o igual que la longitud de las cadenas
  guardadas, o un número negativo, no se hace nada.
- Si se asigna un valor menor que la longitud de las cadenas se deben
  dejar guardadas sólo las palabras que suman esa longitud, recortando la última
  de ellas si es necesario.

Ejemplo:

```swift

print(palabras.longitud)
// 18
palabras.longitud = 10
print(palabras.guardadas)
// ["Hola", "me", "llam"]
```

**Ayuda**: Puedes utilizar la siguiente función `recorta` para
recortar una palabra:

```swift
func recorta(_ palabra: String, hasta: Int) -> String {
    if hasta >= palabra.count {
        return palabra
    } else {
       let start = palabra.startIndex
       let end =  palabra.index(start, offsetBy: hasta)
       return String(palabra[start..<end])
    }
}
print(recorta("Hola", hasta: 2))
// Ho
print(recorta("Hola", hasta: 3))
// Hol
print(recorta("Hola", hasta: 4))
// Hola
print(recorta("Hola", hasta: 5))
// Hola
```

### Ejercicio 3

En este ejercicio vamos a trabajar con estructuras y clases
geométricas: `Punto`, `Tamaño`, `Rectangulo` y `Circulo`. Vamos a
definir propiedades almacenadas y propiedades calculadas para todas
las figuras geométricas.

- Para usar la función `sqrt` debes importar la librería `Foundation`:

```swift
import Foundation
```

- El valor de la constante matemática _pi_ lo puedes obtener con la
  propiedad `Double.pi`.

**Estructuras `Punto` y `Tamaño`**

Las debes declarar tal y como aparecen en los apuntes.

**Clase `Rectangulo`**

- Propiedades de instancia almacenadas: 
    - `origen` (`Punto`) que contiene las coordenadas de la esquina
      inferior izquierda del rectángulo.
    - `tamaño` (`Tamaño`) que contiene las dimensiones del rectángulo. 
    - Ambas propiedades se inicializan en un inicializador.
- Propiedades de instancia calculadas: 
    - `centro` (`Punto`, de lectura y escritura) que devuelve el
      centro del rectángulo. El `setter` modifica la posición del
      rectángulo manteniendo fijo su tamaño.
    - `area` (`Double`, sólo lectura ) que devuelve el área del rectángulo. 

**Clase `Circulo`**

- Propiedades de instancia almacenadas:
    - `centro` (`Punto`) que contiene las coordenadas del centro del
      círculo.
    - `radio` (`Double`) que contiene la longitud del radio.
    - Ambas propiedades se inicializan en un inicializador.
- Propiedades de instancia calculadas:
    - `area` (`Double`, de lectura y escritura) que devuelve el área
      del círculo. El `setter` modifica el tamaño del círculo (su
      radio), manteniéndolo en la misma posición.

**Estructura `AlmacenFiguras`**

- Propiedades de tipo almacenadas:
    - `rectangulos` y `circulos` que contienen respectivamente arrays
      de rectángulos y círculos inicializados a arrays vacíos.
    - Se actualizan con cada nueva figura creada. En los
      inicializadores de `Rectangulo` y `Circulo` se debe
      incluir el código que añade la instancia recién creada al almacén
      de figuras.
- Propiedades de tipo calculadas:
    - `numFiguras` (`Int`) que devuelve el número total de figuras creadas.
    - `areaTotal` (`Double`) que devuelve la suma total de las áreas
      de todas las figuras creadas.

<!-- Nota -->
<table>
<tr><td>

<p><stong>Nota</strong></p>

<p>La definición anterior del almacén de figuras no es demasiado correcta,
porque se utiliza una variable distinta para cada tipo de figura, sin
generalizar. En la práctica de la semana que viene veremos cómo
mejorarlo utilizando protocolos.</p>

</td></tr></table>
<!-- Final de la nota -->

Implementa las estructuras y clases anteriores y escribe algunos
ejemplos de código para probarlas.


### Ejercicio 4

En este ejercicio deberás implementar un conjunto de clases con las que podamos "simular" una carrera de coches.

#### Función `random`

Utilizaremos la función del sistema `random()` que devuelve un número
aleatorio. Hay que importar la librería `Glibc` (en Linux) y
`Foundation` (en iOS) para usarla.

A continuación puedes ver un ejemplo de su utilización en un método de
tipo del enumerado `MarcaCoche` para devolver una marca aleatoria de
coche:


```swift
import Glibc

func rand(n: Int) -> Int {
    return random() % n
}

enum MarcaCoche: Int {
    case Mercedes=0, Ferrari, RedBull, McLaren
    
    static func random() -> MarcaCoche {
        let maxValue = McLaren.rawValue
        
        let r = rand(maxValue+1)
        return MarcaCoche(rawValue: r)!
    }

}
```

#### Enumerados y clases que gestionan los vehículos

Deberás implementar los siguientes enumerados y clases, con las propiedades indicadas.

**Enumerado `MarcaCoche`** 

- Posibles valores: `Mercedes`, `Ferrari`, `RedBull` y `McLaren`
- Método del tipo `random()` que devuelva aleatoriamente uno de los
  valores (consultar el código anterior).

**Enumerado `TipoCambio`**

- Posibles valores: `Automatico` o `Manual`
- Método del tipo `random()` que devuelve uno de esos valores.

**Clase base `Coche`**

- Propiedades de instancia almacenadas: `velocidadActual` (`Double`),
  `marcha` (`Int`), `distanciaRecorrida` (`Double`) y `marca`
  (`MarcaCoche`).
- Propiedad de instancia calculada: `descripcion` (`String`), que
  devuelve la marca del coche.
- Propiedades del tipo: Constantes `velocidadMaxima` (`Double`) y
  `marchaMaxima` (`Int`) inicializadas a 150.0 y 6

**Subclase `CocheAutomatico`**

- Hereda de `Coche` y sobreescribe la descripción, añadiendo la cadena
  "Automático".

**Subclase `CocheManual`**

- Hereda de `Coche` y sobreescribe la descripción, añadiendo la cadena
  "Manual".

**Observadores de propiedades en las subclases**

La velocidad de un coche manual se modifica cambiando su propiedad
`marcha` y la de un coche automático cambiando su propiedad
`velocidadActual`. En cada caso hay que definir observadores de
propiedades que modifiquen la otra propiedad.

La velocidad se calcula a partir de la marcha según la siguiente expresión:

```swift
velocidadActual = 25.0 * marcha
```

Y la marcha se calcula a partir de la velocidad con la expresión que
puedes encontrar en los apuntes de teoría, en la definición de la
clase `CocheAutomatico`.


**Distancia recorrida e información en pantalla**

Suponemos que se cambia la velocidad del coche cada hora y que en cada
cambio de velocidad se actualiza la propiedad `distanciaRecorrida`,
que irá acumulando la distancia recorrida por el coche desde su
inicialización. Cada vez que se cambia la velocidad también se
imprimirá la velocidad actual y la marca del coche en pantalla (ver el
ejemplo al final del ejercicio). Esto se puede implementar también en
los observadores.

#### Clase Carrera

Debes implementar las clases anteriores y una clase `Carrera` con la
que simular una carrera de `n` coches que conducen durante `k` horas.

Un ejemplo de uso de la clase `Carrera`:

```swift
let carrera = Carrera(numCoches: 2, horas: 3)
print("\nDescripción de la carrera:")
carrera.descripcion()
print("\n!!! Comienza la carrera !!!")
carrera.empezar()
print("\n!!! Clasificación !!!")
carrera.clasificacion()
```

Y su correspondiente salida por pantalla:

```text
Descripción de la carrera:
2 coches con una duración de 3 horas
 McLaren Automatico
 Mercedes Manual

!!! Comienza la carrera !!!

Horas transcurridas 1
McLaren Automatico viajando a 141.0 kilómetros por hora con la marcha 6
Mercedes Manual viajando a 25.0 kilómetros por hora con la marcha 1

Horas transcurridas 2
McLaren Automatico viajando a 114.0 kilómetros por hora con la marcha 5
Mercedes Manual viajando a 25.0 kilómetros por hora con la marcha 1

Horas transcurridas 3
McLaren Automatico viajando a 105.0 kilómetros por hora con la marcha 5
Mercedes Manual viajando a 100.0 kilómetros por hora con la marcha 4

!!! Clasificación !!!
1. McLaren Automatico (360.0 kilómetros recorridos)
2. Mercedes Manual (150.0 kilómetros recorridos)
```


----

Lenguajes y Paradigmas de Programación, curso 2017-18  
© Departamento Ciencia de la Computación e Inteligencia Artificial, Universidad de Alicante  
Domingo Gallardo, Cristina Pomares, Antonio Botía, Francisco Martínez

