## Práctica 9: Programación funcional en Swift: clausuras y funciones de orden superior

Para entregar la práctica debes subir a Moodle el fichero
`practica09.swift` con una cabecera inicial con tu nombre y apellidos,
y las soluciones de cada ejercicio separadas por comentarios. Cada
solución debe incluir:

- La **definición de las funciones** que resuelven el ejercicio.
- Una visualización por pantalla de todos los ejemplos incluidos en el
  enunciado que **demuestren qué hace la función**.

## Ejercicios


### Ejercicio 1 ###

a) Indica qué devuelven las siguientes expresiones:

a.1)
```swift
let nums = [1,2,3,4,5,6,7,8,9,10]
nums.filter{$0 % 3 == 0}.count
```

a.2)
```swift
let nums2 = [1,2,3,4,5,6,7,8,9,10]
nums2.map{$0+100}.filter{$0 % 5 == 0}.reduce(0,+)
```

a.3)
```swift
let cadenas = ["En", "un", "lugar", "de", "La", "Mancha"]
cadenas.sorted{$0.count < $1.count}.map{$0.count}
```


a.4)
```swift
let cadenas2 = ["En", "un", "lugar", "de", "La", "Mancha"]
cadenas2.reduce([]) {(res: [(String, Int)], c: String) -> [(String, Int)] in
                       res + [(c, c.count)]}.sorted(by: {$0.1 < $1.1})
```


b) Explica qué hacen las siguientes funciones y pon un ejemplo de su funcionamiento:

b.1)
```swift
func f(nums: [Int], n: Int) -> Int {
    return nums.filter{$0 == n}.count
}
```


b.2)
```swift
func g(nums: [Int]) -> [Int] {
    return nums.reduce([], {(res: [Int], n: Int) -> [Int] in
                        if !res.contains(n) {
                            return res + [n]
                        } else {
                            return res
                        }
                    })
}
```


b.3)
```swift
func h(nums: [Int], n: Int) -> ([Int], [Int]) {
   return nums.reduce(([],[]), {(res: ([Int],[Int]), num: Int ) -> ([Int],[Int]) in
                            if (num >= n) {
                                return (res.0, res.1 + [num])
                            } else {
                                return ((res.0 + [num], res.1))
                            }
                        })
}
```

c) Implementa las siguientes funciones con funciones de orden superior.

c.1) Función `suma(palabras:contienen:)`:

```swift
suma(palabras: [String], contienen: Character) -> Int
```

que recibe una array de cadenas y devuelve la suma de las longitudes
de las cadenas que contiene el carácter que se pasa como parámetro.


c.2) Función `sumaMenoresMayores(nums:pivote:)`:

```swift
sumaMenoresMayores(nums: [Int], pivote: Int) -> (Int, Int)
```

que recibe un array de números y un número pivote y
devuelve una tupla con la suma de los números menores y mayores o
iguales que el pivote.


### Ejercicio 2


Define un tipo enumerado con un árbol genérico, tal y como hicimos en
el último ejercicio de la práctica anterior, que tenga como genérico
el tipo de dato que contiene.

En el siguiente ejemplo vemos cómo debería poderse definir con el
mismo tipo genérico un árbol de enteros y un árbol de cadenas:

```swift
let arbolInt: Arbol = .nodo(53, [.nodo(13, []), .nodo(32, []), .nodo(41, [.nodo(36, []), .nodo(39, [])])])
let arbolString: Arbol = .nodo("Zamora", [.nodo("Buendía", [.nodo("Albeza", []), .nodo("Berenguer", []), .nodo("Bolardo", [])]), 
                                          .nodo("Galván", [])])
```


Define las funciones genéricas `toArray` y `toArrayFOS` que devuelvan un array con todos
los componentes del árbol usando un recorrido _preorden_ (primero la
raíz y después los hijos). La primera la debes implementar con
recursión mutua y la segunda usando funciones de orden superior.

Ejemplo:

```swift
print(toArray(arbol: arbolInt))
// Imprime: [53, 13, 32, 41, 36, 39]
print(toArrayFOS(arbol: arbolString))
// Imprime: ["Zamora", "Buendía", "Albeza", "Berenguer", "Bolardo", "Galván"]
```


### Ejercicio 3

Implementa en Swift la función `imprimirListadosNotas(alumnos:)` que
recibe un array de tuplas, en donde cada tupla contiene información de
la evaluación de un alumno de LPP (nombreAlumno, notaParcial1,
notaParcial2, notaCuestionarios, añosMatriculacion) y que debe imprimir por pantalla los
siguientes listados: 

- listado 1: array ordenado por nombre del alumno (orden alfabético
creciente) 
- listado 2: array ordenado por la nota del parcial 1 (orden
decreciente de nota) 
- listado 3: array ordenado por la nota del parcial 2 (orden creciente
de nota) 
- listado 4: array ordenado por año de matriculación y nota del
  cuestionario (orden decreciente de año y nota) 
- listado 5: array ordenado por nota que necesita obtener en el
parcial 3 para aprobar la asignatura en la convocatoria de Junio
(orden decreciente de nota necesaria)
 
**AYUDA:**

Para que los listados se muestren formateados con espacios, puedes usar la siguiente
función (para ello también debes incluir el import que se indica)
 
```swift

import Foundation

func imprimirListadoAlumnos(_ alumnos: [(String, Double, Double, Double, Int)]) {
    print("Alumno   Parcial1   Parcial2   Cuest  Años")
    for alu in alumnos {
        alu.0.withCString {
            print(String(format:"%-10s %5.2f      %5.2f    %5.2f  %3d", $0, alu.1,alu.2,alu.3,alu.4))
        }
    }
}
```

 
Ejemplo:

```swift
let listaAlumnos = [("Pepe", 8.45, 3.75, 6.05, 1), 
                    ("Maria", 9.1, 7.5, 8.18, 1), 
                    ("Jose", 8.0, 6.65, 7.96, 1),
                    ("Carmen", 6.25, 1.2, 5.41, 2), 
                    ("Felipe", 5.65, 0.25, 3.16, 3), 
                    ("Carla", 6.25, 1.25, 4.23, 2), 
                    ("Luis", 6.75, 0.25, 4.63, 2), 
                    ("Loli", 3.0, 1.25, 2.19, 3)]
imprimirListadosNotas(listaAlumnos)
```
 
Algunos de los listados que se deben mostrar serían los siguientes:

```txt
LISTADO ORIGINAL
Alumno   Parcial1   Parcial2   Cuest  Años
Pepe        8.45       3.75     6.05    1
Maria       9.10       7.50     8.18    1
Jose        8.00       6.65     7.96    1
Carmen      6.25       1.20     5.41    2
Felipe      5.65       0.25     3.16    3
Carla       6.25       1.25     4.23    2
Luis        6.75       0.25     4.63    2
Loli        3.00       1.25     2.19    3

LISTADO ORDENADO por Parcial1
Alumno   Parcial1   Parcial2   Cuest  Años
Maria       9.10       7.50     8.18    1
Pepe        8.45       3.75     6.05    1
Jose        8.00       6.65     7.96    1
Luis        6.75       0.25     4.63    2
Carmen      6.25       1.20     5.41    2
Carla       6.25       1.25     4.23    2
Felipe      5.65       0.25     3.16    3
Loli        3.00       1.25     2.19    3

LISTADO ORDENADO por Nota para aprobar en Junio
Alumno   Parcial1   Parcial2   Cuest  Años
Maria       9.10       7.50     8.18    1
Jose        8.00       6.65     7.96    1
Pepe        8.45       3.75     6.05    1
Carmen      6.25       1.20     5.41    2
Carla       6.25       1.25     4.23    2
Luis        6.75       0.25     4.63    2
Felipe      5.65       0.25     3.16    3
Loli        3.00       1.25     2.19    3
```


### Ejercicio 4

Dado el array `listaAlumnos` del ejercicio anterior, utiliza funciones
de orden superior para obtener los datos requeridos en cada caso

A) Número de alumnos que han aprobado primer parcial y suspendido el segundo

```swift
print(listaAlumnos. ________________________________ )
// Resultado: 5
```

B) Nota que deben tener en el parcial 3 para sacar un 5 en la nota final

```swift
print(listaAlumnos._______________________________ )

// Resultado:
// [2.4500000000000011, -2.6599999999999984, -0.63666666666666671, 7.4133333333333331, 9.7133333333333347, 7.7566666666666677, 8.1233333333333348, 11.686666666666667]
```

C) Nota media de todos los alumnos en forma de tupla `(media_p1, media_p2, media_cuest)`

```swift
var tupla = listaAlumnos._____________________________________ )
tupla = (tupla.0 / Double(listaAlumnos.count), tupla.1 / Double(listaAlumnos.count), tupla.2 / Double(listaAlumnos.count))
print(tupla)
// Resultado: (6.6812499999999995, 2.7624999999999997, 5.2262500000000003)
```

### Ejercicio 5

Queremos escribir la función `calcular(exp: String, sobre:
[(Double, Double)])` que recibe una cadena que codifica una expresión
sobre una tupla y una lista de tuplas y devuelve el resultado de aplicar
esa expresión matemática sobre cada una de las tuplas de la lista.

Por ejemplo:

```swift

let tuplas = [(1.0, 2.5), (10.8, 3.3), (-1.0, 12.0), (-3.4, 4.0)]

print(calcular(exp: "$1 * 2.0", sobre: tuplas)!)
// [5.0, 6.5999999999999996, 24.0, 8.0]
print(calcular(exp: "$0 - 5.0", sobre: tuplas)!)
// [-4.0, 5.8000000000000007, -6.0, -8.4000000000000004]
print(calcular(exp: "$0 + $1", sobre: tuplas)!)
// [3.5, 14.100000000000001, 11.0, 0.60000000000000009]
```

- En el primer ejemplo la expresión indica que hay que multiplicar el
  segundo número de cada tupla por 2.0.
- En el segundo ejemplo la expresión indica que hay que restar al
  primer número de cada tupla 5.0.
- En el tercer ejemplo la expresión indica que hay que sumar el primer
  y el segundo elemento de cada tupla.

La expresión codificada tendrá siempre dos operandos y una
operación. Los operandos pueden ser las expresiones `$0` y `$1` para
referirse al primer y segundo elemento de cada tupla o números
decimales. La operación puede ser `+`, `-`, `*` y `/` para referirse a
las operaciones suma, resta, multiplicación y división.

Vamos a realizar el ejercicio en tres partes. Primero hay que
implementar una función que analiza la cadena y devuelve una tupla de
enumerados; después una función convertirá la tupla de enumerados en
una clausura y por último la función principal que usa las
anteriores y aplica la clausura al array de tuplas. Vamos a detallar
cada una de las partes.

a) En primer lugar debes implementar la función `parse(exp:)` que analice la cadena y
la transforme en una tupla de dos operandos y una operación, siendo
éstos los siguientes tipos enumerados:

```swift
enum Operando {
   case primero
   case segundo
   case valor(Double)
}

enum Operacion {
   case suma
   case resta
   case mult
   case div
}
```

La función `parse(exp:)` tiene el siguiente perfil:

```swift
func parse(exp: String) -> (op1: Operando, op2: Operando, op: Operacion)?
```

Tendrás que definir **funciones auxiliares** para implementar esta
función. 

**AYUDA**: 

Para construir un array con las palabras que aparecen en la frase, puedes usar la siguiente sentencia:

```swift
let arrayPalabras = frase.split(separator: " ").map(String.init)
```

Ejemplos:

```swift
parse("$0 + $1") 
// Devuelve opcional con la tupla (Operando.primero, Operando.segundo, Operacion.suma)
parse("5.0 * $1")
// Devuelve opcional con la tupla (Operando.valor(5.0), Operando.segundo, Operacion.mult)
parse("Hola")
// Devuelve nil
```

b) En segundo lugar debes implementar la función
`construyeFunc(op1:op2:op:)` que recibe los enumerados que representan
la expresión matemática y devuelve una clausura que aplica la
expresión matemática a una tupla.

La función tiene el siguiente perfil:

```swift
func construyeFunc(op1: Operando, op2: Operando, op: Operacion) -> ((Double, Double)) -> Double
```

También es conveniente que definas **funciones auxiliares**.

<!-- Nota -->
<table>
<tr><td>

<strong>Nota</strong>

<p/>

La clausura que se devuelve puede ser una clausura que captura los
parámetros `op1`, `op2` y `op` y que los usa en su cuerpo. De esta
forma podemos hacer una clausura genérica que llama a las funciones
auxiliares y evitamos tener que escribir un número enorme de clasuras
concretas con todas las posibles combinaciones de tuplas y operadores.
</tr></td>
</table>
<!-- Nota --> 

c) Por último debes implementar la función `calcular(exp:sobre:)` con
el siguiente perfil:

```swift
func calcular(exp str: String, sobre tuplas: Array<(Double, Double)>) -> [Double]? 
```

----

Lenguajes y Paradigmas de Programación, curso 2017-18  
© Departamento Ciencia de la Computación e Inteligencia Artificial, Universidad de Alicante  
Domingo Gallardo, Cristina Pomares, Antonio Botía, Francisco Martínez
