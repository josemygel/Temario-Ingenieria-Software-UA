
# Práctica 3: Funciones recursivas sobre listas

## Entrega de la práctica

Para entregar la práctica debes subir a Moodle el fichero
`practica03.rkt` con una cabecera inicial con tu nombre y apellidos, y
las soluciones de cada ejercicio separadas por comentarios. Cada
solución debe incluir:

- La **definición de las funciones** que resuelven el ejercicio.
- Un conjunto de **pruebas** que comprueben su funcionamiento
  utilizando la librería `schemeunit`. 

## Ejercicios


### Ejercicio 1 ###

Implementa la **función recursiva** `(multiplo-de n lista-nums)` que
recibe un número `n` y un lista de números y devuelve una lista con
los booleanos resultantes de comprobar si cada número de la lista es
múltiplo de `n`.

Ejemplo:

```scheme
(multiplo-de 10 '(100 23 10 300  48 7)) ; ⇒ (#t #f #t #t #f #f)
```

### Ejercicio 2 ###

Define la **función recursiva** `(calcular-lista lista)` que recibe una lista que
contiene símbolos (operadores +,-,*,/) y parejas de números
(operandos), representando operaciones matemáticas. Se considera que
la lista siempre está bien formada, con sus elementos en este orden:
`(símbolo pareja símbolo pareja...)`. Tiene que devolver una lista con
los resultados de las operaciones.

Ejemplos:

```scheme
(calcular-lista '(+ (2 . 3) * (4 . 5))) ; ⇒ {5 20}
(calcular-lista '(/ (-6 . 2) - (4.5 . 0.5) + (2 . 3))) ; ⇒ {-3 4.0 5}
```

### Ejercicio 3 ###

Escribe la **función recursiva** `(expande lista-parejas)` que reciba una
lista de parejas que contienen un dato y un número y devuelva una
lista donde se hayan "expandido" las parejas, añadiendo tantos
elementos como el número que indique cada pareja.

**Pista**: Puedes definir una función auxiliar `(expande-pareja pareja)` que
recibe una pareja y devuelve la lista expandida resultante de expandir
sólo esa pareja.

Ejemplo:

```scheme
(expande '((#t . 3) ("LPP" . 2) (b . 4)))
⇒ {#t #t #t "LPP" "LPP" b b b b}
```

### Ejercicio 4 ###

a) Implementa la **función recursiva** `(resultados‐quiniela lista‐parejas)`
que devuelve una lista de 1, X, 2 a partir de una lista de parejas que
representa resultados de partidos de fútbol. El resultado es 1 cuando
el número izquierdo de la pareja es mayor que el derecho, un 2 cuando
es al revés, y una X cuando los dos números son iguales.

**Pista**: Puedes definir una función `(resultado-partido partido)`
  que reciba una pareja y devuelva 1, X o 2.

```scheme
(resultados-quiniela '((1 . 2) (4 . 4) (3 . 5) (6 . 2) (9 . 9)))  ; ⇒ {2 X 2 1 X}
(resultados-quiniela '((2 . 2))) ; ⇒ {X}
(resultados-quiniela '((3 . 2) (4 . 3) (3 . 5)))  ; ⇒ {1 1 2}
```

b) Implementa la **función recursiva** `(cuenta-resultados resultado lista-resultados)`
donde `resultado` puede ser un uno, una equis o un dos y cuenta el
número de ese resultado en la lista de partidos.

Ejemplo:

```scheme
(cuenta-resultados 'X '((1 . 2) (4 . 4) (3 . 5) (6 . 2) (9 . 9))) ; ⇒ 2
(cuenta-resultados 1 '((1 . 2) (4 . 4) (3 . 5) (6 . 2) (9 . 9))) ; ⇒ 1
(cuenta-resultados 2 '((1 . 2) (4 . 4) (3 . 5) (6 . 2) (9 . 9))) ; ⇒ 2
```

c) Implementa la **función recursiva** `(cuenta-resultados-lista
lista-resultados)` que devuelva una lista de tres elementos que
representan el número de 1s, de Xs y de 2s existentes en la lista de
resultados original.

**Pista**: Puedes definir una función auxiliar `(inc-pos n lista)` que
incremente el número de la lista situado en la posición `n`.

```scheme
(cuenta-resultados-lista '((1 . 2) (4 . 4) (3 . 5) (6 . 2) (9 . 9))) ; ⇒ {1 2 2}
(cuenta-resultados-lista '((2 . 2))) ; ⇒ {0 1 0}
(cuenta-resultados-lista '((3 . 2) (4 . 3) (3 . 5)))  ; ⇒ {2 0 1}
```


### Ejercicio 5 ###

Implementa una **función recursiva** `(filtra-simbolos lista-simbolos
lista-num)` que recibe una lista de símbolos y una lista de números
enteros (ambas de la misma longitud) y devuelve una lista de
parejas. Cada pareja está formada por el símbolo de la i-ésima
posición de `lista-simbolos` y el número entero situado en esa
posición de `lista-num`, siempre y cuando dicho número se corresponda
con la longitud de la cadena correspondiente al símbolo. Puedes
utilizar las funciones predefinidas `string-length` y
`symbol->string`.

Ejemplo:

```scheme
(filtra-simbolos '(este es un ejercicio de examen) '(2 1 2 9 1 6))
; ⇒ {{un . 2} {ejercicio . 9} {examen . 6}}
```




----

Lenguajes y Paradigmas de Programación, curso 2017-18  
© Departamento Ciencia de la Computación e Inteligencia Artificial, Universidad de Alicante  
Domingo Gallardo, Cristina Pomares, Antonio Botía, Francisco Martínez
