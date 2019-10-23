

## <a name="6"></a>6. Ámbitos de variables, let y closures

Ahora que hemos introducido la forma especial `lambda` y la idea de
que una función es un objeto de primera clase, podemos revisar el
concepto de ámbito de variables en Scheme e introducir un importante
concepto: clausura
(*[closure](https://en.wikipedia.org/wiki/Closure_(computer_programming))*
en inglés).

### <a name="6-1"></a> 6.1. Ámbitos de variables

El concepto del **ámbito** de vida de las variables es un concepto
fundamental en los lenguajes de programación. En inglés se utiliza el
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

#### Variables de ámbito global

Una variable definida en el programa con la instrucción `define` tiene
un ámbito global.

```
(define a "hola")
(define b (string-append "adios" a))
(define cuadrado (lambda (x) (* x x)))
```

Todas las variables definidas fuera de funciones forman parte del
**entorno global** del programa.

#### Variables de ámbito local

Como en la mayoría de lenguajes de programación, en Scheme se crea un
**entorno local** cada vez que se invoca a una función. En este
entorno local los argumentos de la función toman los valores de los
parámetros usados en la llamada a la función. Consideramos, por tanto,
estos parámetros como variables de ámbito local de la función.

Podemos usar en un entorno local una variable con el mismo nombre que
en el entorno global. Cuando se ejecute el código de la función se
evaluará la variable de ámbito local.

Por ejemplo, supongamos las expresiones:

```scheme
(define x 5)
(define (suma-3 x)
   (+ x 3))

(suma-3 12)
⇒ 15
```

Cuando se ejecuta la expresión `(+ x 3)` en la invocación a `(suma-3
12)` el valor de `x` es 12, no es 5, devolviéndose 15.

Una vez realizada la invocación, desparece el entorno local y las
variables locales definidas en él, recuperándose el contexto
global. Por ejemplo, en la siguiente expresión, una vez realizada la
invocación a `(suma-3 12)` se devuelve el número 15 y se evalúa en el
entorno global la expresión `(+ 15 x)`. En este contexto la variable
`x` vale 5 por lo que la expresión devuelve 20.

```scheme
(define x 5)
(define (suma-3 x)
   (+ x 3))

(+ (suma-3 12) x)
⇒ 20
```

En el entorno local también se pueden utilizar variables definidas en
el entorno global. Por ejemplo:

```
(define y 12)
(define (suma-3-bis x)
   (+ x y 3))

(suma-3-bis 5) 
⇒ 20
```

La expresión `(+ x 3 y)` se evalúa en el entorno local en el que `x`
vale 5. Al no estar definida la variable `y` en este entorno local, se
usa su definición de ámbito global.

### <a name="6-2"></a> 6.2 Forma especial let

#### Forma especial let

En Scheme se define la forma especial let que permite crear un entorno
local en el que se da valor a variables y se evalúa una expresión.

Sintaxis:

```scheme
(let ((<var1> <exp-1>)
        ...
      (<varn> <exp-n>))
    <cuerpo>)
```

Las variables `var1`, … `varn` toman los valores devueltos por las
expresiones `exp1`, … `expn` y el cuerpo se evalúa con esos valores.

Por ejemplo:

```scheme
(define x 10)
(define y 20)
(let ((x 1)
      (y 2)
      (z 3))
    (+ x y z))
⇒ 6
```

#### El ámbito de las variables definidas en el let es local

Las variables definidas en el `let` sólo tienen valores en el entorno
creado por la forma especial.

```scheme
(define x 10)
(define y 20)
(let ((x 1)
      (y 2)
      (z 3))
    (+ x y z))
⇒ 6
```

Cuando ha terminado la evaluación del `let` el ámbito local desaparece
y quedan los valores definidos en el ámbito global.

```scheme
x ⇒ 5
y ⇒ error, no definida
```

#### `Let` permite usar variables definidas en un ámbito en el que se ejecuta el `let`

Al igual que en la invocación a funciones, desde el ámbito definido
por el `let` se puede usar las variables del entorno en el que se está
ejecutando el `let`. Por ejemplo, en el siguiente código se usa la
variable z definida en el ámbito global.

```scheme
(define z 8)
(let ((x 1)
      (y 2))
   (+ x y z))
⇒ 11
```

#### Variables en las definiciones del `let`

Las expresiones que dan valor a las variables del `let` se evalúan
todas en el entorno en el que se ejecuta el `let`, antes de crear las
variables locales.  No se realiza una asignación secuencial:

```scheme
(define x 1)
(let ((w (+ x 3))
      (z (+ w 2))) ;; Error: w no está definida
    (+ w z))
```

##### Semántica del `let`

Para evaluar una expresión `let` debemos seguir las siguientes reglas:

1. Evaluar todas las expresiones de la derecha de las variables y
   guardar sus valores en variables auxiliares locales.
2. Definir un ámbito local en el que se ligan las variables del let
   con los valores de las variables auxiliares.
3. Evaluar el cuerpo del let en el ámbito local

##### Let se define utilizando lambda

La semántica anterior queda clara cuando comprobamos que let se puede
definir en función de lambda. En general, la expresión:

```scheme
(let ((<var1> <exp1>) ... (<varn> <expn>)) <cuerpo>)
```

se puede implementar con la siguiente llamada a lambda:

```scheme
((lambda (<var1> ... <varn>) <cuerpo>) <exp1> ... <expn>)
```

Para ejecutar un `let` con un `lambda` se debe crear un procedimiento
en tiempo de ejecución con tantas variables como las variables del
`let`, con el cuerpo del `let`, y se debe invocar a dicho
procedimiento con las expresiones de las variables del `let`. Esas
expresiones se evalúan antes de invocar al procedimiento y la
invocación se realiza con los resultados. La invocación crea un
entorno local con los parámetros del procedimiento (las variables del
`let`) asociados a los valores, y en este ámbito local se ejecuta el
cuerpo del procedimiento (el cuerpo del `let`).

Por ejemplo:

```
(let ((x (+ 2 3))
      (y (+ x 3)))
    (+ x y))
```

Equivale a:

```scheme
((lambda (x y) (+ x y)) (+ 2 3) (+ x 3))
```


#### Let dentro de funciones

Podemos usar `let` en el cuerpo de funciones para crear nuevas
variables locales, además de los parámetros de la función

```scheme
(define (suma-cuadrados x y)
    (let ((cuadrado-x (cuadrado x))
          (cuadrado-y (cuadrado y)))
        (+ cuadrado-x cuadrado-y)))
(suma-cuadrados 4 10)
⇒ 116
```

Cuando se invoca `(suma-cuadrados 4 10)` se crea un entorno local en
el que las variables `x` e `y` toman el valor `4` y `10` y en el que
se ejecuta la forma especial `let`. Esta forma especial crea a su vez
un entorno local en el que se definen las variables `cuadrado-x` y
`cuadrado-y` que toman los valores devueltos por las expresiones
`(cuadrado x)` y `(cuadrado y)`: `16` y `100`. En este entorno local
se evalúa la expresión `(+ cuadrado-x cuadrado-y)`.

El uso de `let` permite aumentar la legibilidad de los programas,
dando nombre a expresiones:

Por ejemplo:

```scheme
(define (distancia x1 y1 x2 y2)
   (let ((distancia-x (- x2 x1))
         (distancia-y (- y2 y1)))
      (sqrt (+ (cuadrado distancia-x)
               (cuadrado distancia-y)))))
```

Otro ejemplo:

```scheme
(define (intersecta-intervalo a1 a2 b1 b2)
    (let ((dentro-b1 (and (>= b1 a1)
                          (<= b1 a2)))
          (dentro-b2 (and (>= b2 a1)
                          (<= b2 a2))))
        (or dentro-b1 dentro-b2)))
```


### <a name="6-3"></a> 6.3. Clausuras

Vamos a terminar explicando el concepto de *clausura*. Hemos visto que
las funciones son objetos de primera clase de Scheme y que es posible
crear funciones en tiempo de ejecución con la forma especial `lambda`.

Una clausura es una función devuelta por otra función. La clausura
**captura las variables locales** de la función principal y puede
usarlas en su propio código cuando este se invoque posteriormente.

Veamos un ejemplo. Supongamos que definimos la siguiente función
`(make-sumador k)` que devuelve otra función.

```
(define (make-sumador k)
    (lambda (x) (+ x k)))

(define f (make-sumador 10))
(f 2)
⇒ 12
```

En la función `(make-sumador k)` se llama a la forma especial lambda
para crear un procedimiento. El procedimiento devuelto es lo que se
denomina **clausura**. En este caso la clausura captura la variable
local `k` (el parámetro de `make-sumador`) y usa su valor cuando
posteriormente se invoca. Cuando se invoca a `(f 2)` se ejecuta la
clausura y se crea un nuevo entorno local en el que `x` (el parámetro
de la clausura) vale `2` y en el que se usa la variable `k` capturada.

En la invocación anterior `(f 2)`, cuando se ejecuta la expresión `(+
x k)` las variables tienen los siguientes valores:

```
x: 2 (variable local de la clausura)
k: 10 (valor capturado del entorno local en el que se creó la clausura
```

#### Las variables locales creadas en un `let` también se capturan en las clausuras

Veamos el siguiente ejemplo, en el que creamos una función en un
entorno local creado por un `let`:

```scheme
(define x 10)
(define y 12)
(define (prueba x)
    (let ((y 3))
        (lambda (z) 
            (+ x y z))))
(define h (prueba 5))
(h 2)
⇒ 8
```

Sucede lo siguiente:

1. Se invoca la expresión `(prueba 5)`. Esto crea un entorno local en
   el que se le da a la variable `x` (el parámetro de `prueba`) el
   valor `5`. En este contexto se ejecuta el `let`, que crea otro
   entorno local en el que `y` vale 3. En el contexto del `let` se
   crea una clausura con la invocación de la expresión lambda `(lambda
   (z) (+ x y z))`.
2. La clausura captura las variables locales `x` e `y` con sus valores
   5 y 3 y la función `prueba` la devuelve como resultado de la
   invocación.
3. La clausura se guarda en la variable `h`.
4. Con la invocación `(h 2)` se invoca a la clausura, lo que crea un
   entorno local en el que se encuentran las siguientes variables:

    ```
    z: 2 (parámetro de la clausura)
    x: 5 (variable local de la función prueba capturada en el momento de creación de la clausura))
    y: 3 (variable local del let capturada en el momento de creación de la clausura)
    ```

5. En este contexto se ejecuta la expresión `(+ x y z)`, que devuelve 10.

----

Lenguajes y Paradigmas de Programación, curso 2015-16  
© Departamento Ciencia de la Computación e Inteligencia Artificial, Universidad de Alicante  
Domingo Gallardo, Cristina Pomares
