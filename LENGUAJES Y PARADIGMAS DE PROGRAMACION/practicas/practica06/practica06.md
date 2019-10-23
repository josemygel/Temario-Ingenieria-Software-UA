# Práctica 6: Listas estructuradas

## Entrega de la práctica

Para entregar la práctica debes subir a Moodle el fichero
`practica06.rkt` con una cabecera inicial con tu nombre y apellidos, y
las soluciones de cada ejercicio separadas por comentarios. Cada
solución debe incluir:

- La **definición de las funciones** que resuelven el ejercicio.
- Un conjunto de **pruebas** que comprueben su funcionamiento
  utilizando la librería `schemeunit`.

## Ejercicios


### Ejercicio 1 ###

a) Escribe la lista estructurada correspondiente a la siguiente
representación gráfica por niveles, y escribe una única expresión que
devuelva el elemento 'h

```text
       *
     / |  \
    |  |    \
    *  d      *
   / \    / /  | \
  a  b   c *   *  h
           |  / \
           e f  g
```

```scheme
(define lista-a '(________))
(check-equal? _________ 'h)
```


b) Escribe la lista estructurada correspondiente a la siguiente
representación gráfica por niveles, y escribe una única expresión que
devuelva el elemento 10

```text
      *
  /  / \   \
 1  *   *   8  
  / | \   \  
6   *  10  2
    |    
    3   
```

```scheme
(define lista-b '(________))
(check-equal? _______ 10)
```


### Ejercicio 2  ###

a) Define la función recursiva `(cuenta-pares lista)` que recibe una
lista estructurada y cuenta la cantidad de números pares que contiene

Ejemplos:

```scheme
(cuenta-pares '(1 (2 3) 4 (5 6))) ; ⇒ 3
(cuenta-pares '(((1 2) 3 (4) 5) ((((6)))))) ; ⇒ 3
```

b) Define la función del apartado anterior utilizando funciones de
orden superior.

### Ejercicio 3 ###

a) Implementa la función `(cumplen-predicado pred lista)` que devuelva
una lista con todos los elementos de lista estructurada que cumplen un
predicado.

Ejemplo:

```scheme
(cumplen-predicado even? '(1 (2 (3 (4))) (5 6))) ; ⇒ {2 4 6}
(cumplen-predicado pair? '(((1 . 2) 3 (4 . 3) 5) 6)) ; ⇒ {{1 . 2} {4 . 3}
```

b) Implementa la función del apartado anterior utilizando funciones de
orden superior.


### Ejercicio 4 ###

Implementa la función recursiva `(sustituye-elem lista elem-old
elem-new)` que recibe como argumentos una lista estructurada y dos
elementos, y devuelve otra lista con la misma estructura, pero en la
que se ha sustituido las ocurrencias de `elem-old` por `elem-new`.

Ejemplo:

```scheme
(sustituye-elem  '(a b (c d (e c)) c (f (c) g))  'c  'h)
⇒ {a b {h d {e h}} h {f {h} g}}
```


### Ejercicio 5 ###

Implementa la función recursiva `(diff-listas l1 l2)` que tome como
argumentos dos listas estructuradas con la misma estructura, pero con
diferentes elementos, y devuelva una lista de parejas que contenga los
elementos que son diferentes.

Ejemplos:

```scheme
(diff-listas '(a (b ((c)) d e) f) '(1 (b ((2)) 3 4) f))
⇒ {{a . 1} {c . 2} {d . 3} {e . 4}}

(diff-listas '((a b) c) '((a b) c))
⇒ ()
```


### Ejercicio 6 ###

Dos funciones sobre niveles:

a) Implementa la función `(cuenta-hojas-debajo-nivel lista n)` que recibe
una lista estructurada y un número que indica un nivel, y devuelve el
número de hojas que hay por debajo de ese nivel. Puedes hacerlo con
recursión o con funciones de orden superior.

```scheme
(cuenta-hojas-debajo-nivel '(10 2 (4 6 (9 3) (8 7) 12) 1) 2) ; ⇒ 4
(cuenta-hojas-debajo-nivel '(10 2 (4 6 (9 3) (8 7) 12) 1) 3) ; ⇒ 0
```

b) Implementa la función `(nivel-elemento lista)` que reciba una lista
estructurada y devuelva una pareja `(elem . nivel)`, donde la parte
izquierda es el elemento que se encuentra a mayor nivel y la parte
derecha el nivel en el que se encuentra. Puedes definir alguna
función auxiliar si lo necesitas. Puedes hacerlo con
recursión o con funciones de orden superior.

```scheme
(nivel-elemento '(2 (3))) ;⇒ (3 . 2)
(nivel-elemento '((2) (3 (4)((((((5))) 6)) 7)) 8)) ; ⇒ (5 . 8)
```

----

Lenguajes y Paradigmas de Programación, curso 2017-18  
© Departamento Ciencia de la Computación e Inteligencia Artificial, Universidad de Alicante  
Domingo Gallardo, Cristina Pomares, Antonio Botía, Francisco Martínez
