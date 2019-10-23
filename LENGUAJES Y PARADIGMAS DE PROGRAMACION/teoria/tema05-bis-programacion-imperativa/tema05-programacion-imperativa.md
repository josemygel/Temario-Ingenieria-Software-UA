
# Tema 5: Programación imperativa

## Contenidos

- [1. Historia y características de la programación imperativa](#1)
    - [1.1. Historia de la programación imperativa](#1-1)
	- [1.2. Características principales de la programación imperativa](#1-2)
- [2. Programación imperativa en Scheme](#2)
    - [2.1. Pasos de ejecución](#2-1)
	- [2.2. Mutación con formas especiales *set!*](#2-2)
	- [2.3. Igualdad de referencia y de valor](#2-3)
- [3. Estructuras de datos mutables](#3)
    - [3.1. Mutación de elementos](#3-1)
	- [3.2. Funciones mutadoras: `make-ciclo!`, `append!` y `intercambia!`](#3-2)
    - [3.3. Lista ordenada mutable](#3-3)
	- [3.4. Diccionario mutable](#3-4)
	- [3.5. Ejemplo de mutación con listas de asociación](#3-5)
- [4. Ámbitos de variables y clausuras](#4)
    - [4.1. Variables de ámbito global](#4-1)
    - [4.2. Variables de ámbito local](#4-2)
    - [4.3. Clausuras y estado local](#4-3)
- [5. Clausuras con mutación = estado local mutable](#5)
    - [5.1. Estado local mutable](#5-1)
	- [5.2. Paso de mensajes](#4-2)

## Bibliografía

En este tema explicamos conceptos de los siguientes capítulos del libro *Structure and Intepretation of Computer Programs*:

- [3.3.1 Mutable List Structure](https://mitpress.mit.edu/sicp/full-text/book/book-Z-H-22.html#%_sec_3.3.1)
- [3.3.2 Representing Queues](https://mitpress.mit.edu/sicp/full-text/book/book-Z-H-22.html#%_sec_3.3.2)
- [3.3.3 Representing tables](https://mitpress.mit.edu/sicp/full-text/book/book-Z-H-22.html#%_sec_3.3.3)
- [3.1.1 Local State Variables](https://mitpress.mit.edu/sicp/full-text/book/book-Z-H-20.html#%_sec_3.1.1)
- [3.1.3 The Cost of Introducing Assignment](https://mitpress.mit.edu/sicp/full-text/book/book-Z-H-20.html#%_sec_3.1.3)

## <a name="1"></a> 1. Historia y características de la programación imperativa

### <a name="1-1"></a>1.1. Historia de la programación imperativa

#### 1.1.1. Orígenes de la programación imperativa

- La programación imperativa es la forma natural de programar un
  computador, es el estilo de programación que se utiliza en el
  ensamblador, el estilo más cercano a la arquitectura del computador
- Características de la arquitectura
  [arquitectura clásica de Von Newmann](http://en.wikipedia.org/wiki/Von_Neumann_architecture):
	- memoria donde se almacenan los datos (referenciables por su
      dirección de memoria) y el programa
	- unidad de control que ejecuta las instrucciones del programa
      (contador del programa)
	- Los primeros lenguajes de programación (como el Fortran) son
      abstracciones del ensamblador y de esta arquitectura. Lenguajes
      más modernos como el BASIC o el C han continuado con esta idea.

#### 1.1.2. Programación procedural

- Uso de procedimientos y subrutinas
- Los cambios de estado se localizan en estos procedimientos
- Los procedimientos especifican parámetros y valores devueltos (un
  primer paso hacia la abstracción y los modelos funcionales y
  declarativos)
- Primer lenguaje con estas ideas: ALGOL

#### 1.1.3. Programación estructurada

- Artículo a finales de los 60 de Edsger W. Dijkstra:
  [GOTO statement considered harmful](http://www.cs.utexas.edu/users/EWD/ewd02xx/EWD215.PDF)
  en el que se arremete contra la sentencia GOTO de muchos lenguajes
  de programación de la época
- La programación estructurada mantiene la programación imperativa,
  pero haciendo énfasis en la necesidad de que los programas sean
  correctos (debe ser posible de comprobar formalmente los programas),
  modulares y mantenibles.
- Lenguajes: Pascal, ALGOL 68, Ada

#### 1.1.4. Programación Orientada a Objetos

- La POO también utiliza la programación imperativa, aunque extiende
  los conceptos de modularidad, mantenibilidad y estado local
- Se populariza a finales de los 70 y principios de los 80

### <a name="1-2"></a> 1.2. Características principales de la programación imperativa

- Idea principal de la programación imperativa: la computación se
  realiza cambiando el estado del programa por medio de sentencias que
  definen pasos de ejecución del computador
- Estado del programa modificable
- Sentencias de control que definen pasos de ejecución

Vamos a ver unos ejemplos de estas características usando el lenguaje
de programación Java.

#### 1.2.1. Modificación de datos

- Uno de los elementos de la arquitectura de Von Newmann es la
  existencia de celdas de memoria referenciables y modificables

```java
int x = 0;
x = x + 1;
```

- Otro ejemplo típico de este concepto en los lenguajes de
  programación es el array: una estructura de datos que se almacena
  directamente en memoria y que puede ser accedido y modificado.
- En Java los arrays son tipeados, mutables y de tamaño fijo

```java
String[] unoDosTres = {"uno","dos","tres"};
unoDosTres[0] = unoDosTres[2];
```
	
- Un ejemplo de un método que recibe un parámetro de tipo array en
  Java. El parámetro se pasa por referencia:

```java
public void llenaCadenas(String[] cadenas, String cadena) {
    for (int i = 0; i < cadenas.length; i++) {
        cadenas[i] = cadena;
    }
}
```

- Todos los ejemplos anteriores en un programa `main` Java:

```java
public class Main {

    public static void main(String[] args) {
        int x = 0;
        x = x + 1;
        System.out.println("x: " + x);
        String[] unoDosTres = {"uno","dos","tres"};
        unoDosTres[0] = unoDosTres[2];
        for (String valor : unoDosTres) {
            System.out.println(valor);
        }
        llenaCadenas(unoDosTres, "uno");
        for (String valor : unoDosTres) {
            System.out.println(valor);
        }
    }

    public static void llenaCadenas(String[] cadenas, String cadena) {
        for (int i = 0; i < cadenas.length; i++) {
            cadenas[i] = cadena;
        }
    }
}
```

#### 1.2.2. Almacenamiento de datos en variables

- Todos los lenguajes de programación definen variables que contienen
  datos
- Las variables pueden mantener valores (tipos de valor o value types)
  o referencias (tipos de referencia o reference types)
- En C, C++ o Java, los datos primitivos como int o char son de tipo
  valor y los objetos y datos compuestos son de tipo referencia
- La asignación de un valor a una variable tiene implicaciones
  distintas si el tipo es de valor (se copia el valor) o de referencia
  (se copia la referencia)

Copia de valor (datos primitivos en Java):

```java
int x = 10;
int y = x;
x = 20;
System.out.println(y); // Sigue siendo 10
```

Copia de referencia (objetos en Java):

```java
// import java.awt.geom.Point2D;
Point2D p1 = new Point2D.Double(2.0, 3.0);
Point2D p2 = p1;
p1.setLocation(12.0, 13.0);
System.out.println("p2.x = " + p2.getX()); // 12.0
System.out.println("p2.y = " + p2.getY()); // 13.0
```

- El uso de las referencias para los objetos de clases y para los
  tipos compuestos está generalizado en la mayoría de lenguajes de
  programación
- Tiene efectos laterales pero permite obtener estructuras de datos
  eficientes

#### 1.2.3. Igualdad de valor y de referencia

- Todos los lenguajes de programación imperativos que permite la
  distinción entre valores y referencias implementan dos tipos de
  igualdad entre variables
- Igualdad de valor (el contenido de los datos de las variables es el
  mismo)
- Igualdad de referencia (las variables tienen la misma referencia)
- Igualdad de referencia => Igualdad de valor (pero al revés no)

- En Java la igualdad de referencia se define con `==` y la de valor
  con el método `equals`:

```java
Point2D p1 = new Point2D.Double(2.0, 3.0);
Point2D p2 = p1;
Point2D p3 = new Point2D.Double(2.0, 3.0);
System.out.println(p1==p2);           // true
System.out.println(p1==p3);           // false
System.out.println(p1.equals(p3));    // true
```

#### 1.2.4. Sentencias de control

- También tiene su origen en la arquitectura de Von Newmann
- Sentencia que modifica el contador de programa y determina cuál será
  la siguiente instrucción a ejecutar
- Tipos de sentencias de control en programación estructurada:
	- Las sentencias de secuencia definen instrucciones que son ejecutados una detrás de otra de 
	forma síncrona. Una instrucción no comienza hasta que la anterior ha terminado.
	- Las sentencias de selección definen una o más condiciones que determinan las instrucciones 
	que se deberán ejecutar.
	- Las sentencias de iteración definen instrucciones que se ejecutan de forma repetitiva hasta 
	que se cumple una determinada condición.

**Bucles y variables**

Ejemplo imperativo que imprime en Java una tabla con los productos de
los números del 1 al 9:

```java
for (int i = 1; i <= 9 ; i++) {
    System.out.println("Tabla del " + i);
    System.out.println("-----------");
    for (int j = 1; j <= 9; j++) {
        System.out.println(i + " * " + j + " = " + i * j);
    }
    System.out.println();
}
```	

## <a name="2"></a> 2. Programación imperativa en Scheme

- Al igual que LISP, Scheme tiene características imperativas
- Vamos a ver algunas de ellas
	- Pasos de ejecución
	- Asignación con la forma especial `set!`
	- Datos mutables con las formas especiales `set-car!` y `set-cdr!`
- Una nota importante: todos los ejemplos que hay a continuación
  necesitan importar la librería `mutable-pairs`:

```scheme
#lang r6rs
(import (rnrs)
      (rnrs mutable-pairs))
```


### <a name="2-1"></a> 2.1. Pasos de ejecución

- Es posible definir pasos de ejecución con la forma especial `begin`
- Todas las sentencias de la forma especial se ejecutan de forma
  secuencial, una tras otra
- Tanto en la definición de funciones como en `lambda` es
  posible definir cuerpos de función con múltiples sentencias que se
  ejecutan también de forma secuencial. Hasta ahora no hemos usado
  esta característica porque hemos utilizado Scheme de forma
  funcional.

Ejemplo `begin`:

```scheme
(begin
    (display "Escribe un número: ")
    (define x (read))
    (display "Escribe otro: ")
    (define y (read))
    (define maximo (max x y))
    (display (string-append "El máximo de "
                        (number->string x)
                        " y "
                        (number->string y)
                        " es "
                       (number->string maximo))))
```

Ejemplo de pasos de ejecución en la definición de una función:

```scheme
(define (display-tres-valores a b c)
    (display a)
    (newline)
    (display b)
    (newline)
    (display c)
    (newline))
```

### <a name="2-2"></a> 2.2. Mutación con formas especiales *set!*

#### 2.2.1. Forma especial `set!`

- La forma especial `set!` permite asignar un nuevo valor a una
  variable
- La variable debe haber sido previamente creada con `define`
- La forma especial no devuelve ningún valor, modifica el valor de la
  variable usada

Sintaxis:

```text
(set! <variable> <nuevo-valor>)
```

Por ejemplo, la típica asignación de los lenguajes imperativos se
puede realizar de esta forma en Scheme:

```scheme
(define a 10)
(set! a (+ a 1))
a  ; ⇒ 11
```

La forma especial `set!` funciona con cualquier tipo de datos. Por
ejemplo, utilizando la característica de que Scheme es débilmente
tipeado, puede incluso asignar un nuevo tipo de valor a una variable:

```scheme
(define a "Hola")
a ; ⇒ "Hola"
(set! a (cons 1 2))
a ; ⇒ {1 . 2}
```
	
#### 2.2.2. Datos mutables

- En Scheme se definen las formas especiales `set-car!` y `set-cdr!`
  que permite modificar (mutar) la parte izquierda o derecha de una
  pareja una vez creada
- Al igual que `set!`, no devuelven ningún valor

Sintaxis:

```text
(set-car! <pareja> <nuevo-valor>)
(set-cdr! <pareja> <nuevo-valor>)
```

Ejemplo

```scheme
(define p (cons 1 2))
(set-car! p 10)
(set-cdr! p 20)
p	; ⇒ (10 . 20)
```

#### 2.2.3. Efectos laterales

- La introducción de la asignación y los datos mutables hace posible
  que Scheme se comporte como un lenguaje imperativo en el que más de
  una variable apunta a un mismo valor y se producen efectos laterales
- Un efecto lateral se produce cuando el valor de una variable cambia
  debido a una sentencia en la que no aparece la variable
- Podemos comprobar ahora que las parejas son datos que se copian por
  referencia

Ejemplo:

```scheme
(define p1 (cons 1 2))
(define p2 p1)
p1 ; ⇒ (1 . 2)
p2 ; ⇒ (1 . 2)
(set-car! p1 20)
p1 ; ⇒ (20 . 2)
p2 ; ⇒ (20 . 2)
```

### <a name="2-3"></a> 2.3. Igualdad de referencia y de valor

- La utilización de referencias, la mutación y los efectos laterales
  hace también necesario definir dos tipos de igualdades: igualdad de
  referencia e igualdad de valor.
- Igualdad de referencia: dos variables son iguales cuando apuntan al
  mismo valor
- Igualdad de valor: dos variables son iguales cuando contienen el
  mismo valor
- En Scheme la función `eq?` comprueba la igualdad de referencia y
  `equal?` la igualdad de valor
- Igualdad de referencia implica igualdad de valor, pero no al revés

Ejemplo:

```scheme
(define p1 (cons 10 20))
(define p2 p1)
(define p3 (cons 10 20))
(equal? p3 p1)  ; ⇒ #t
(eq? p3 p1) ; ⇒ #f
(eq? p2 p1) ; ⇒ #t
(set! p3 p1) ;; La asignación copia referencias
(eq? p3 p1) ; ⇒ #t
```

## <a name="3"></a> 3. Estructuras de datos mutables

- La utilización de las formas especiales `set-car!` y `set-cdr!`
  permite un estilo nuevo de manejo de las estructuras de datos ya
  vistas (listas o árboles)
- Es posible implementar funciones más eficientes que actualizan la
  estructura modificando directamente las referencias de unas celdas a
  otras
- Las operaciones no construyen estructuras nuevas, sino que modifican
  la ya existente

### <a name="3-1"></a> 3.1. Mutación de elementos

**Ejemplo 1**

Vamos a empezar con un ejemplo sencillo en el que vamos a mutar un
elemento de una estructura de datos formada por parejas. Supongamos la
siguiente estructura:

```scheme
(define datos (cons 2
                    (cons (cons 5
                                (cons 8 9))
                          (cons 3 4))))
```

El diagrama *box-and-pointer* es el siguiente. Fíjate que hay
elementos de las parejas que son datos atómicos y otros que son
referencias a otras parejas.

<img src="imagenes/datos.png" width="250px">

Vamos ahora a *mutar* la estructura utilizando las sentencias
`set-car!` y `set-cdr!`. Para mutar una pareja acceder a la pareja y
modificar su parte derecha o su parte izquierda con las sentencias
anteriores.

Por ejemplo, ¿cómo cambiaríamos el `8` por un `18`? Deberíamos obtener
la pareja `(8 . 9)` que está al final de la estructura y modificar su
parte izquierda:

```scheme
(set-car! (cdr (car (cdr datos))) 18)
```

La expresión `(cdr (car (cdr datos)))` devuelve la pareja que queremos
modificar y la sentencia `set-car!` modifica su parte izquierda.

Si ahora vemos qué hay en `datos` veremos que se ha modificado la
estructura:

```scheme
(print-pareja datos)
; ⇒ (2 . ((5 . (18 . 9)) . (3 . 4)))
```

Recuerda que `print-pareja` es una función que vimos anteriormente.

Hemos mutado un dato por otro. También podemos mutar las referencias a
las parejas. Por ejemplo, podríamos modificar la parte derecha de la
pareja que contiene el 5 para que apunte a la pareja `(3 . 4)`:

<img src="imagenes/datos-mutados.png" width="250px">

Para ello habría que obtener la pareja que contiene el 5 con la
expresión `(car (cdr datos))` y mutar su parte derecha (`set-cdr!`)
con la referencia a la pareja `(3 . 4)` que se obtiene con la
expresión `(cdr (cdr datos))`:

```scheme
(set-cdr! (car (cdr datos)) (cdr (cdr datos)))
```

**Ejemplo 2**

Veamos un segundo ejemplo. 

Supongamos las siguientes sentencias. ¿Cuál sería el _box-and-pointer_
resultante? 

```scheme
(define x '((a b) c d))
(define y '(e f))
(set-car! x y)
(define z (cons y (cdr x)))
(set-cdr! x y)
(set-car! z (caar x))
```

Debemos ir paso a paso ejecutando cada instrucción y dibujando cómo
cambia el diagrama. El diagrama representa el **estado del programa**,
y es un conjunto de parejas que contienen valores y referencias entre
ellas. 

Es muy importante considerar cuándo la sentencia copia un valor y cuando
copia una referencia. Las parejas siempre tienen semántica de
referencia.

El diagrama resultante después de las dos primeras sentencias es:

<img src="imagenes/x-y-z-1.png" width="400px"/>

Y el diagrama después de todas las sentencias es:

<img src="imagenes/x-y-z-2.png" width="400px"/>

Los valores de las variables después de todas las sentencias son los siguientes:

```scheme
x ; ⇒ {{e f} e f}
y ; ⇒ {e f}
z ; ⇒ {e c d}
```

**Ejemplo 3**

En este ejemplo vamos a usar una nueva forma especial de Scheme que
permite crear variables locales y ejecutar expresiones en las que se
utilicen esas variables. 

Se trata de la forma especial `let`. Veremos algo más de esta forma
especial más adelante, cuando hablemos de ámbitos de variables. Por
ahora es suficiente saber que permite crear variables locales y
ejecutar un cuerpo (que puede tener más de una sentencia) con esas
variables.

```scheme
(let ((x (+ 2 3))
      (y (* 2 10))
    (+ x y))
; ⇒ 25
```

En este caso `let` permite crear dos variables locales `x` e `y` y
asignarles los valores `5` y `20` respectivamente. Las sentencias del
cuerpo del `let` (en este caso la sentencia `(+ x y)`) se evalúan con
estos valores recién creados.

Vamos a usar esta forma especial en ocasiones para guardar valores o
referencias que después necesitamos asignar.

Por ejemplo, ¿cómo podríamos definir una función que mute **las
referencias** de una lista para intercambiar sus posiciones segunda y
tercera (sin crear nuevas parejas)?

Si llamamos a la función `intercambia!` debería hacer lo siguiente:

```scheme
(define lista '(1 2 3 4 5 6))
(intercambia! lista)
lista ; ⇒ {1 3 2 4 5 6}
```

Dibujando el diagrama _box-and-pointer_ podemos ver las mutaciones que
tenemos que hacer:

<img src="imagenes/intercambia.png" width="600px"/>

La función es la siguiente:

```scheme
(define (intercambia! lista)
    (let ((segundo (cdr lista))
          (tercero (cddr lista)))
        (set-cdr! lista tercero)
        (set-cdr! segundo (cdr tercero))
        (set-cdr! tercero segundo)))
```



### <a name="3-2"></a> 3.2. Funciones mutadoras: `make-ciclo!`, `append!` e `intercambia-lista!`

Veamos ahora varios ejemplos de funciones recursivas que recorren
listas y realizan mutaciones en ellas.

Normalmente las funciones mutadoras no devuelven una estructura, sino
que modifican la que se pasa como parámetro.

Por convenio, indicaremos que una función es mutadora terminando su
nombre con un signo de admiración.

**make-ciclo!**

Empecemos con una función muta una lista, haciendo que la parte
derecha de la última pareja apunte a la pareja inicial de la misma.

<img src="imagenes/ciclo.png" width="600px"/>

Para conseguirlo, necesitamos guardar la referencia a la pareja
inicial de la lista en el primer parámetro de la función. Y el segundo
parámetro irá avanzando hasta encontrar el final de la lista:

```scheme
(define (make-ciclo! lista ref)
    (if (null? (cdr ref))
        (set-cdr! ref lista)
        (make-ciclo! lista (cdr ref))))
```

Si probamos la función con una lista, podemos comprobar que DrRacket
detecta el ciclo que se produce al final de la misma:

```scheme
(define lista '(1 2 3 4 5 6))
(make-ciclo! lista lista)
lista ; ⇒ #0={1 2 3 4 5 6 . #0#}
```

Una forma alternativa de implementar esta función es mediante una
función que devuelva la referencia de la última pareja de la lista:

```scheme
(define (ultima-pareja lista)
    (if (null? (cdr lista))
        lista
        (ultima-pareja (cdr lista))))

(define (make-ciclo2! lista)
    (let ((ultima-pareja (ultima-pareja lista)))
        (set-cdr! ultima-pareja lista)))
```

**append!**

Y vemos a continuación una versión mutadora de `append` que llamamos
`append!` y que mejora la eficiencia de la original, copiando al final
de la primera lista una referencia a la segunda:


<img src="imagenes/append.png" width="400px">

La implementación es la siguiente:

```scheme
(define (append! l1 l2)
    (if (null? (cdr l1))
        (set-cdr! l1 l2)
        (append! (cdr l1) l2)))
```

Ejemplo:
	      
```scheme
(define a '(1 2 3 4))
(define b '(5 6 7))
(append! a b)
a ; ⇒ {1 2 3 4 5 6 7}
```

Algunas puntualizaciones:

- Al igual que `set!`, `set-car!` o `set-cdr!`, la función `append!`
  no devuelve ningún valor, sino que modifica directamente la lista
  que se pasa como primer parámetro.
- Al modificarse la lista, todas las referencias que apuntan a ellas
  quedan también modificadas.
- La función daría un error en el caso en que la llamáramos con una
  lista vacía como primer argumento.

**intercambia-lista!**

Un último ejemplo de función recursiva que muta una lista. Queremos
definir una función que intercambie todas las parejas excepto la
primera de una lista con un número de datos impar, pero **sin usar
set-car!**.

Llamamos a la función `(intercambia-lista! lista)` y debería hacer lo
siguiente:

```scheme
(define lista '(1 2 3 4 5 6 7))
(intercambia-lista! lista)
lista ; ⇒ {1 3 2 5 4 7 6}
```

La forma de implementar esta función va a ser llamando a la función
`intercambia!` (la que hemos definido anteriormente que intercambia el
segundo y el tercer elemento) hasta que lleguemos al final de la
lista. Lo hacemos con una recursión:

```scheme
(define (intercambia-lista! lista)
  (if (not (null? (cdr lista)))
           (begin
             (intercambia! lista)
             (intercambia-lista! (cddr lista)))))
```

La función termina cuando llegamos a la pareja final de la lista, la
que tiene la lista vacía en su parte derecha. 

Si no estamos en el final de la lista llamamos a la función
`intercambia!` para intercambiar la siguiente pareja con su siguiente
y llamamos a la recursión con la variable `lista` apuntando a la
tercera pareja de la lista.


### <a name="3-3"></a> 3.3. Lista ordenada mutable

Veamos ahora una lista ordenada mutable de números, en la que insertaremos
elementos de forma ordenada.

Definiremos las siguientes funciones:

- `(make-olist)`: construye una lista ordenada vacía
- `(borra-primero-olist! olist)`: función mutadora que elimina (con
  mutación) el primer elemento de la lista
- `(inserta-olist! olist n)`: función mutadora que inserta (con
  mutación) de forma ordenada un número en la lista

Implementamos el tipo de datos con una lista normal con una pareja
adicional en cabeza. Esta pareja adicional funcionará de **cabecera de
la lista** y será la referencia inmutable a la que apuntará cualquier
variable que apunte a la lista. De esta forma podremos insertar
elementos en primera posición de la lista. En la cabecera usaremos por
convenio el símbolo `'*olist*'`.

```scheme
(define lista '(*olist* 10 20 30))
```
<img src="imagenes/olist.png" width="600px">


**Constructor**

El constructor `(make-olist)` devuelve una lista sólo con la cabecera.

```scheme
(define (make-olist)
    (list '*olist*))
```

**Mutadores**

Las funciones mutadoras modifican la estructura de datos.

Comenzamos con la función `(borra-primero-olist! olist)` que elimina
con mutación el primer elemento de la lista ordenada:

<img src="imagenes/olist2.png" width="600px">

```scheme
(define (borra-primero-olist! olist)
    (set-cdr! olist (cddr olist)))
```

Definimos la función mutadora `inserta-olist!` que modifica la lista
ordenada añadiendo una nueva pareja, insertándola en la posición
correcta modificando las referencias.

La función `(add-item! item ref)` es la función clave que crea una
nueva pareja con el `item` y la añade en el `cdr` de la pareja a la
que apunta `ref`.

```scheme
(define (add-item! ref item)
    (set-cdr! ref (cons item (cdr ref))))

(define (inserta-olist! olist n)
    (cond 
        ((null? (cdr olist)) (add-item! olist n))
        ((< n (cadr olist)) (add-item! olist n))
        ((= n (cadr olist)) #f)   ; el valor devuelto no importa
        (else (inserta-olist! (cdr olist) n))))
```

<img src="imagenes/olist3.png" width="600px">

Ejemplo de uso:

```scheme
(define c (make-olist))
(inserta-olist! c 5)
(inserta-olist! c 10)
(inserta-olist! c -10)
c ; ⇒ {*olist* -10 5 10}
```

### <a name="3-4"></a> 3.4. Diccionario mutable

- Veamos ahora un ejemplo más: un _diccionario_ mutable definido
  mediante una lista de asociación formada por parejas de clave y
  valor

```scheme
(define l-assoc (list (cons 'a 1) (cons 'b 2) (cons 'c 3)))
```
	
- La función de Scheme `assq` recorre la lista de asociación y
  devuelve la tupla que contiene el dato que se pasa como parámetro
  como clave

```scheme
(assq 'a l-assoc) ; ⇒ (a.1)
(assq 'b l-assoc) ; ⇒ (b.2)
(assq 'c l-assoc) ; ⇒ (c.3)
(assq 'd l-assoc) ; ⇒  #f
(cdr (assq 'c l-assoc)) ; ⇒ 3
```

- La función `assq` busca en la lista de asociación usando la igualdad
  de referencia `eq?`

```scheme
(define p (cons 1 2))
(define l-assoc (list (cons 'a 1) (cons p 2) (cons 'c 3)))
(assq (cons 1 2) l-assoc) ; ⇒ #f
(assq p l-assoc) ; ⇒ {{1 . 2} . 2}
```

Las funciones que vamos a implementar del diccionario son las siguientes:

- `(make-dic)`: construye un diccionario vacío
- `(put-dic! dic clave valor)`: inserta en el diccionario un nuevo
  valor asociado a una clave
- `(get-dic dic clave)`: devuelve el valor asociado a una clave en un
  diccionario

Al igual que la lista ordenada mutable, el diccionario necesita una
cabecera:
  
```scheme
(define (make-dic)
  (list '*dic*))
```

La función `(get-dic dic clave)` usa la función `assq` para buscar la pareja que
contiene una clave determinada en la lista de asociación (el `cdr` de
dic). Y después devuelve el `cdr` de esa pareja (el valor guardado
junto con la clave:

```scheme
(define (get-dic dic clave)
    (let (pareja (assq clave (cdr dic)))
        (if (not pareja)
            #f
            (cdr pareja))))
```

La función `(put-dic! dic clave valor)` usa igual que antes la función
`assq` para comprobar si la clave ya está en la lista de
asociación. Si existe, sustituye el valor por el nuevo. Si no existe,
crea una pareja con la clave y el valor y la inserta al comienzo del
diccionario (después de la cabecera):

```scheme
(define (put-dic! dic clave valor)
    (let (pareja (assq clave (cdr dic)))
        (if (not pareja)
            (set-cdr! dic
                  (cons (cons clave valor)
                        (cdr dic)))
        (set-cdr! pareja valor)))
    'ok)
```

En las funciones anteriores volvemos a usar la forma especial `let`.

Ejemplos de uso:
  
```scheme
(define dic (make-dic))
(put-dic! dic 'a 10) ; ⇒ ok
(get-dic dic 'a) ; ⇒ 10
(put-dic! dic 'b '(a b c)) ; ⇒ ok
(get-dic dic 'b) ; ⇒ {a b c}
(put-dic! dic 'a 'ardilla) ; ⇒ ok
(get-dic dic 'a) ; ⇒ ardilla
```

### <a name="3-5"></a> 3.5. Ejemplo de mutación con listas de asociación

Una vez introducidos distintas estructuras de datos mutables,
incluyendo listas de asociación, vamos a terminar estos ejemplos con
un ejemplo práctico en el que intervienen las listas y las listas de
asociación. Se trata de escribir un procedimiento `regular->assoc!`
que transforme una lista regular en una lista de asociación sin crear
nuevas parejas. La lista regular `(k1 v1 k2 v2 k3 v3 ...)` deberá
convertirse en la lista de asociación `((k1 . v1) (k2 . v2) (k3 . v3)
...)`.

Ejemplo:

```scheme
(define my-list (list 'a 1 'b 2 'c 3))
lista ; ⇒ (a 1 b 2 c 3)
(regular->assoc! my-list)
lista ; ⇒ ((a . 1) (b . 2) (c . 3))
```

Una posible solución a este problema sería la siguiente (no es la
única solución):

Cuando trabajamos con este tipo de problemas, es muy útil ayudarse con
los diagramas caja y puntero. Vamos a crear los diagramas caja y
puntero para el ejemplo anterior:

Antes de llamar a regular->assoc!:

<img src="imagenes/mutable1.png" width="750px">

Después de llamar a regular->assoc!:

<img src="imagenes/mutable2.png" width="400px">

Queremos hacer la transformación sin crear nuevas parejas, por lo que
cada pareja en el primer diagrama corresponde a una pareja particular
en el segundo diagrama. No podemos modificar la primera pareja de la
lista (porque perderíamos la ligadura de la variable `lista`), por
lo que es primera pareja tiene que permanecer en el primer lugar. Por
otra parte, `a` y `1` van a formar parte del mismo par en la lista de
asociación, por lo que vamos a considerar el siguiente cambio de las
dos primeras parejas.

Diagrama inicial:

<img src="imagenes/mutable3.png" width="750px">

Después del cambio de las dos primeras parejas:

<img src="imagenes/mutable4.png" width="650px">

La siguiente función `manejar-dos-parejas!` es la encargada de hacer
esta mutación:

```scheme
(define (manejar-dos-parejas! p)
    (let ((key (car p)))            ;; 1
        (set-car! p (cdr p))        ;; 2
        (set-cdr! p (cdar p))       ;; 3
        (set-cdr! (car p) (caar p)) ;; 4
        (set-car! (car p) key)))    ;; 5
```

Se han numerado las líneas para una mejor explicación. En la línea 1,
`(define key (car p))`, creamos una variable local `key` que guarda el
valor actual del `(car p)`, ese valor será la clave de la primera
pareja de la lista de asociación; necesitamos almacenarlo para no
perderlo.

En la línea 2, `(set-car! p (cdr p))`, cambiamos el `car` de `p` para
que apunte a la siguiente pareja (la azul):

<img src="imagenes/mutable6.png" width="300px">

En la línea 3, `(set-cdr! p (cdar p))`, copiamos en el `cdr` de `p`
el `cdr` de la pareja en azul para que ambos apunten a la siguiente
pareja:

<img src="imagenes/mutable7.png" width="300px">

En la línea 4, `(set-cdr! (car p) (caar p))`, cambiamos el `cdr`
de la pareja en azul al `car` de la misma pareja. Hacemos ésto porque
en una lista de asociación, los valores se guardan en los `cdrs` y las
claves en los `cars`:

<img src="imagenes/mutable8.png" width="300px">

Por último, en la línea 5 `(set-car! (car p) key)`, completamos el
problema poniendo la clave que habíamos guardado, en el `car` de la
pareja azul:

<img src="imagenes/mutable9.png" width="300px">

Reordenamos el diagrama para verlo más claro:

<img src="imagenes/mutable10.png" width="220px">

Hemos definido un procedimiento que maneja un subproblema (dos
parejas) del problema. Ahora sólo nos queda definir la función que
maneja toda la lista:

```scheme
(define (regular->assoc! lista)
  (if (null? lista) 
     'ok
     (begin (manejar-dos-parejas! lista)
            (regular->assoc! (cdr lista)))))
```

## <a name="4"></a> 4. Ámbitos de variables y clausuras

El concepto del **ámbito** de vida de las variables es un concepto
fundamental en los lenguajes de programación. Utilizaremos las
palabras _ámbito_ y _entorno_ como sinónimos. En inglés se utiliza el
término
*[scope](https://en.wikipedia.org/wiki/Scope_(computer_science))*.

Cuando se define una variable, asociándole un valor, esta asociación
tiene una extensión determinada, ya sea en términos de tiempo de
compilación (**ámbito léxico**) como en términos de tiempo de
ejecución (**ámbito dinámico**). El ámbito de una variable determina
cuándo podemos referirnos a ella para recuperar el valor asociado.

Al conjunto de variables disponibles en una parte del programa o en
una parte de su ejecución se denomina **contexto** o **entorno**
(*context* o *environment*).

### <a name="4-1"></a> 4.1. Variables de ámbito global

Una variable definida en el programa con la instrucción `define` tiene
un ámbito global.

```scheme
(define a "hola")
(define b (string-append "adios" a))
(define cuadrado (lambda (x) (* x x)))
```

Todas las variables definidas fuera de funciones forman parte del
**entorno global** del programa.

### <a name="4-2"></a> 4.2. Variables de ámbito local


En Scheme existen dos formas de definir variables de ámbito local: la
forma especial `define` dentro de una función y la forma especial
`let`.

#### 4.2.1 Variables locales con `define`

Como en la mayoría de lenguajes de programación, en Scheme se crea un
**entorno o ámbito local** (memoria local de la invocación de la
función) cada vez que se invoca a una función.

En este entorno local toman valor los parámetros y las variables
locales de la función. Es posible definir variables locales en una
función utilizando la forma especial `define` dentro de la propia
función. Esto no lo hacíamos dentro del paradigma funcional, para
evitar realizar pasos de ejecución. Pero ahora que estamos en el
paradigma imperativo podemos utilizarlo.

```scheme
(define (distancia x1 y1 x2 y2)
   (define distancia-x (- x2 x1))
   (define distancia-y (- y2 y1))
   (sqrt (+ (cuadrado distancia-x)
            (cuadrado distancia-y))))
```

Podemos usar en un entorno local una variable con el mismo nombre que
en el entorno global. Cuando se ejecute el código de la función se
utilizará el valor local.

Por ejemplo, supongamos las siguientes expresiones:

```scheme
(define x 5)
(define (suma-10 y)
  (define x 10)
  (+ x y))

(suma-10 3) ; ⇒ 13
```

La primera sentencia de la función `(suma y)` define una variable
local `x` a la que se asigna un valor inicial de 10. 

Cuando se ejecuta la expresión `(+ x y)`, en la invocación a `(suma
3)` el valor de `x` es entonces 10 (el valor local que hemos definido
en la sentencia anterior), no es 5, devolviéndose 15.

Sucede igual si un parámetro tiene el mismo nombre que una variable global:

```scheme
(define x 5)
(define (suma-3 x)
   (+ x 3))

(suma-3 12) ; ⇒ 15
```

Cuando se ejecuta la expresión `(+ x 3)` en la invocación a `(suma-3
12)` el valor de `x` es 12, no es 5, devolviéndose 15.

En el entorno local también se pueden utilizar variables definidas en
el entorno global. Por ejemplo:

```scheme
(define z 12)
(define (foo y)
   (define x 10)
   (+ x y z))

(foo 5) ; ⇒ 27
```

La expresión `(+ x y z)` se evalúa en el entorno local en el que `x`
vale 10 e `y` vale 5. Al no estar definida la variable `z` en este
entorno local, se usa su definición de ámbito global.

Una vez realizada la invocación, desparece el entorno local junto con
las variables locales definidas en él, y se recupera el contexto
global. Por ejemplo, en la siguiente expresión, una vez realizada la
invocación a `(suma-10 2)` se devuelve el número 12 y se evalúa en el
entorno global la expresión `(+ 12 x)`. En este contexto la variable
`x` vale 5 por lo que la expresión devuelve 17.

```scheme
(define x 5)
(define (suma-10 y)
   (define x 10)
   (+ x y))

(+ (suma-10 2) x) ; ⇒ 17
```

Otro ejemplo:

```scheme
(define x 10)
(define (foo2 y)
   (define z 5)
   (+ x y z))

(foo2 2) ; ⇒ 17
x ; ⇒ 10
z ; ⇒ error, no definida
y ; ⇒ error, no definida
```

**Define en el cuerpo de una función**

La forma especial `define` para crear variables locales sólo puede
utilizarse al comienzo de la definición de una función. Si intentamos
utilizarla a mitad del código de la función tendremos un error:

```scheme
(define (suma-10-si-mayor-que-0 x)
   (if (> x 0)
      (begin 
         ;; ERROR:
         ;; no es posible usar define a mitad del código
         ;; de una función
         (define y 10)
         (+ x y))
     x))
```


#### 4.2.1 Variables locales con `let`

La otra forma de definir variables locales en Scheme es con la forma
especial `let`.

La forma especial `let` permite crear un ámbito local en el que se da
valor a variables y se evalúan expresiones.

Sintaxis:

```scheme
(let ((<var1> <exp-1>)
        ...
      (<varn> <exp-n>))
    <cuerpo>)
```

Las variables `var1`, … `varn` toman los valores devueltos por las
expresiones `exp1`, … `expn` y el cuerpo se evalúa con esos
valores. Esas variables sólo tienen valor en el ámbito de la forma
especial.

Por ejemplo:

```scheme
(define x 10)
(let ((x (+ 1 2))
      (y (* 10 2))
    (+ x y)) ; ⇒ 23
x ; ⇒ 10
y ; ⇒ error, no definida
```

Cuando ha terminado la evaluación del `let` el ámbito local desaparece
y quedan los valores definidos en el ámbito anterior.


Las expresiones que dan valor a las variables del let se evalúan antes
de crear el ámbito local. Por ejemplo, en el siguiente código, las
expresiones `(+ x 3)` y `(+ y 2)` devuelven 4 y 7 respectivamente:

```scheme
(define x 1)
(define y 5)
(let ((w (+ x 3))
      (z (+ y 2)))
    (+ w z)) ; ⇒ 11
```

En el cuerpo del `let` puede haber más de una expresión:

```scheme
(let ((x 10)
      (y 20))
    (display "x: ")
    (display x)
    (display "\ny: ")
    (display y))
```

**Let en el cuerpo de una función**

A diferencia de `define` sí que es posible usar `let` en cualquier
expresión de Scheme, por ejemplo, en un `if`:

```scheme
(define (suma-10-si-mayor-que-0 x)
   (if (> x 0)
      (let ((y 10))
         (+ x y))
      x))
```


### <a name="4-3"></a> 4.3. Clausuras y estado local

Recordemos que la forma especial `lambda` permite crear funciones
anónimas en tiempo de ejecución. Si ejecutamos una forma especial
lambda como último paso de una función, se devolverá la
función recién creada.

¿Qué sucede si utilizamos en el cuerpo de esta función anónima una
variable local definida en la función principal?

Vamos a verlo:

```scheme
(define (make-sumador)
   (define z 10)
   (lambda (x) (+ x z)))
```

La función `make-sumador` define una variable local `z` con el valor
de 10. Y después construye una función con la expresión `lambda`. Al
ser la última expresión de la función `make-sumador`, esta función
construida en tiempo de ejecución es lo que se devuelve. 

Si invocamos a `make-sumador` vemos que devuelve un procedimiento:

```scheme
(make-sumador) ; ⇒ #<procedure>
```
Si guardamos el procedimiento en una variable y después lo invocamos,
tendremos que pasarle un parámetro (correspondiente al argumento `x`
de la expresión `lambda`). ¿Qué devolverá esa invocación?


```scheme
(define f (make-sumador))
(f 5) ; ⇒ ???
```

Al invocar a `f` se ejecutará el cuerpo del procedimiento, esto es, la
expresión `(+ x z)`. La variable `x` valdrá 5 (el valor del
parámetro). Pero, ¿cuál es el valor de la variable z?. Podemos
comprobar que es 10, porque la invocación devuelve devuelve 15:

```scheme
(f 5) ; ⇒ 15
```

¿Por qué? Lo que está pasando es que la función anónima que crea la
expresión lambda **captura las variables locales** definidas en el
ámbito en el que se crea. Por eso recibe el nombre de **clausura**,
porque encierra los valores que tienen esas variables en el momento de
su creación y esos valores son los que usa en las variables libres
cuando posteriormente la invoquemos.

En el ejemplo anterior se está capturando la variable `z` con su
valor 10. Cuando después invocamos a `(f 5)` se evalúa el cuerpo `(+ x
z)` con `x` valiendo `5` y `z` valiendo el valor capturado (10).

También podemos utilizar como estado local el valor de un
parámetro. Por ejemplo:

```scheme
(define (make-sumador k)
    (lambda (x) (+ x k)))

(define f (make-sumador 10))
(f 2)
; ⇒ 12
```

En la función `(make-sumador k)` se llama a la forma especial lambda
para crear una clausura. La clausura captura la variable local `k` (el
parámetro de `make-sumador`) y usará su valor cuando posteriormente se
evalúe. En este caso, la clausura se ha creado con `k` valiendo 10,
y captura este valor. 

Cuando después se invoca a `(f 2)` se ejecuta la clausura en un nuevo
ámbito local con las siguientes variables y valores:

```
x: 2 (variable local de la clausura)
k: 10 (valor capturado del entorno local en el que se creó la clausura
```

En este ámbito se ejecuta la expresión `(+ x k)`, devolviéndose el
valor 12.

Por último, también es posible crear la clausura dentro de un estado
local definido con un `let` y usar en el cuerpo del `lambda` variables
locales definidas en el `let`:

```scheme
(define (make-sumador-cuadrado k)
    (if (> k 0)
        (let ((y (cuadrado k)))
          (lambda (x)
            (+ x y)))
        (lambda (x)
          x)))
```

La función anterior tiene un condicional en el que se comprueba si `k`
es mayor que 0. Si lo es, se devuelve una clausura creada en el ámbito
del `let` con la variable `y` capturada con el valor del cuadrado de
`k`. Esta clausura suma a su entrada el valor de `y`.

Por ejemplo:

```scheme
(define f (make-sumador-cuadrado 4))
(f 3) ; ⇒ 19
```

En el caso en que `k` menor o igual que 0, se devuelve una clausura
que devuelve siempre el valor que recibe:

```scheme
(define f (make-sumador-cuadrado -8))
(f 3) ; ⇒ 3
```

La definición de clausuras con un estado local inicializado a un valor
creado en tiempo de ejecución es una característica muy
potente. Permite no sólo crear funciones en tiempo de ejecución para
utilizarlas posteriormente, sino configurarlas con el estado local que
nos interese.

Veremos en el apartado siguiente que la combinación de clausuras y
mutación permite crear funciones con estado local mutable,
algo equivalente a objetos que encapsulan código y estado.


## <a name="5"></a> 5. Clausuras con mutación = estado local mutable

### <a name="5-1"></a> 5.1. Estado local mutable

Si combinamos una clausura con la posibilidad de mutar las variables
capturadas por la clausura obtenemos un estado local mutable asociado
a funciones creadas en tiempo de ejecución.

Veamos, por ejemplo, la siguiente función `(make-contador i)`:

```scheme
(define (make-contador i)
  (define x i)
  (lambda ()
     (set! x (+ x 1))
     x)))
```

La función `make-contador` define una clausura que captura la variable
local `x` inicializada a `i`. En cada invocación a la clausura se
ejecuta una sentencia `set` que modifica el valor de la variable
capturada. Y después se devuelve el nuevo valor de la
variable. Estamos implementando un contador asociado a la clausura.


```scheme
(define f (make-contador 10))
(f) ⇒ 11
(f) ⇒ 12
```

El estado (el valor del contador) se mantiene y se modifica entre
distintas invocaciones a `f`. A diferencia del paradigma funcional
podemos comprobar que distintas invocaciones a la misma función
devuelven valores distintos (dependiente del estado de la clausura).

Podemos crear distintas clausuras, cada una con su variable capturada,
en distintas invocaciones a `make-contador`:

```scheme
(define h (make-contador 10))
(define g (make-contador 100))
(h) ⇒ 11
(h) ⇒ 12
(g) ⇒ 101
(g) ⇒ 102
```

Una forma alternativa de crear la clausura, sin usar la forma especial
`lambda` es definiéndola con un `define` en el cuerpo de
`make-contador`. Después de los dos `define` la última sentencia
devuelve la función `incrementa` (la clausura).

```scheme
(define (make-contador i)
  (define x i)
  (define (incrementa)
    (set! x (+ x 1))
    x)
  incrementa)
```

### <a name="5-2"></a> 5.2. Paso de mensajes

En el ejemplo anterior creamos una clausura que siempre incrementa el
valor del contador. ¿Cómo podríamos crear una clausura que permitiera
hacer distintas cosas con el valor capturado?

Una forma de hacerlo es definir en la clausura un parámetro adicional
(un símbolo que llamamos _mensaje_) y devolver distintas clausuras en
función del valor de ese parámetro adicional:

```scheme
(define (make-contador i)
  (define x i)
  (define (get)
    x)
  (define (inc-1)
    (set! x (+ x 1))
    x)
  (define (inc y)
    (set! x (+ x y))
    x)

  (define (dispatcher mensaje)
    (cond
      ((equal? mensaje 'get) get)
      ((equal? mensaje 'inc-1) inc-1)
      ((equal? mensaje 'inc) inc)
      (else "Error: mensaje desconocido")))
  dispatcher)
```

La función `make-contador` devuelve la clausura `dispatcher` que se
encarga de procesar el mensaje y devolver la clausura asociada a ese
mensaje. Cuando invoquemos al _dispatcher_ con un símbolo, devuelve
otro procedimiento que hay que volver a invocar con los parámetros
adecuados (sin parámetros en los dos primeros casos y con el valor a
incrementar en el último caso):

- Si recibe el símbolo `'get` devuelve la clausura que devuelve el valor del contador
- Si recibe el símbolo `'inc-1` devuelve la clausura que incrementa el valor del contador en 1
- Si recibe el símbolo `'inc` devuelve la clausura que incrementa el valor del contador una cantidad determinada

Por ejemplo:

```scheme
(define c (make-contador 100))
((c 'get)) ⇒ 100
((c 'inc-1)) ⇒ 101
((c 'inc) 10) ⇒ 111
```

----

Lenguajes y Paradigmas de Programación, curso 2016-17  
© Departamento Ciencia de la Computación e Inteligencia Artificial, Universidad de Alicante  
Domingo Gallardo, Cristina Pomares
