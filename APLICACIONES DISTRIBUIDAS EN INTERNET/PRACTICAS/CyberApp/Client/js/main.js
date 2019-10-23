// En Servicio_API insertaremos todo el JS de solicitudes a realizar
// handlebars se encarga de hacer un compilado de paginas
import {compile} from 'handlebars';

import {Servicio_API} from './services/API_cyberapp.js'

// En servicio_API insertamos el Servicio inicializado con el URL al que haremos
// las llamadas
var servicio_API = new Servicio_API('http://localhost:3000/')
var logout = `
<div class="contenido">
<center>
<h3>
   <label id="username"><strong>{{username}}</strong></label><hr>
   </h3>
   <button id="logout">Cerrar sesión</button><br>
</center>
</div>
`

//Para el desarrollo se uso admin en todos los campos como cuenta adminstrador
var login = `
<div class="contenido">
<center>
<h2>Sesion</h2>
<hr>
<label class="label-sesion" >Usuario: </label>
<input class="input-sesion" id="username"></input>
<label class="labelFail" id="username_fail"></label>
<br><br>
<label class="label-sesion">Email*: </label>
<input class="input-sesion" id="email"></input>
<label class="labelFail" id="email_fail"></label>
<br><br>
<label class="label-sesion">Contraseña*: </label>
<input class="input-sesion" id="password" type="password"></input>
<label class="labelFail" id="password_fail"></label>
<br>
<br>
<button class="button" id="loginButton">Iniciar sesión</button>
<button class="button" id="registerButton"> Registrarse</button>
<h2><text id="resultLogin"></text></h2>
</div>
`

var templateCreateThread = `
<div class="contenido">
<center>
<h2>Crea tu Hilo</h2>
<hr>
<label class="label-sesion" >Titulo*: </label>
<input class="input-create-thread" id="title"></input>
<label class="labelFail" id="title_fail"></label>
<br><br>
<label class="label-sesion">Content*: </label>
<input class="input-create-thread" id="content"></input>
<label class="labelFail" id="content_fail"></label>
<br>
<br>
<button class="button" id="createButton">Crear hilo</button>
<button class="button" id="cancelButton">Cancelar</button>
</div>
`

// Plantilla Handlebars para renderizar en HTML la lista de la compra
// 1. El "." significa el objeto del nivel "actual", en nuestro caso es el array
// por el que vamos a iterar con handlebars
// 2. El ${} nos permite interpolar variables (funcionalidad de ES6). Es solo
// por no andar concatenando cadenas, esto queda más elegante
var templateLista = `
 <h2>Lista de threads</h2>
 {{#.}}
   ${templateThread}
 {{/.}}
`


var threads = `
<div class="contenido">
<center>
<div>
<h2>Threads</h2><button id="createThreadButton"><Strong>Crear</Strong></button>
</div>
</center>
<hr>
<!--En este div insertaremos la lista de hilos creados por los usuarios-->
<div id="threadslist"></div>
<div id="threadselected"></div>
<br>
</div>
`
// Plantilla handlebars para renderizar en HTML un item de la lista
// Usamos backticks (funcionalidad de ES6) para delimitar la cadena para que
// pueda ser multilínea Con el "javascript:" en el href conseguimos que un
// enlace pueda llamar a código JS
var templateThread = `

<hr/>
  <div>
  <center>
      <span id="title_{{id}}">
         <strong>{{title}}</strong>
      </span>
      <br>
      <span id="content_{{id}}">
        {{content}}
      </span>
      </center>
   </div>
`



var templateDetalles = `
  <span id="detalles_{{id}}">
    {{detalles}}
  </span>
`

var templateThreads = `
    {{#.}}
    <li>
    <button class="thread_link" id="{{_id}}">
       <strong>{{title}}</strong>
    </button>
    <button class="thread_button_delete" id="delete_{{_id}}">
      <strong>Eliminar</strong>
    </button>
    </li>
    {{/.}}
`

// Compilamos las plantillas handlebars. Esto genera funciones a las que
// llamaremos luego
var tmpl_lista_compilada = compile(templateLista)
var tmpl_detalles_compilada = compile(templateDetalles)
var tmpl_threads = compile(templateThreads)
var tmpl_divThreads = compile(threads)
var tmpl_sesion = compile(login);
var tmpl_exit = compile(logout)

var createThreadSend =
    function() {
  var thread = {};
  thread.title = document.getElementById('title').value
  thread.content = document.getElementById('content').value

  servicio_API.createThread(thread).then(thread => {
    if (thread && thread._id) alert('¡Hilo creado correctamente!');
    location.reload();
  })
}

// Este método se encarga de volver a la pagina de hilos sin recargar la pagina
// (reload)
var cancelThreadSend = function() {
  servicio_API.getMe(localStorage.token).then(sesion => {
    loadThreads(sesion);
  })
}

//Metodo encargado de obtener y mostrar el hilo pulsado
var openThread = function() {
  servicio_API.getThread(this.id).then(thread => {
    // mezclamos los datos con el HTML de la plantilla para obtener el HTML
    // resultado
    var tmpl_thread_compilado = compile(templateThread)
    var threadHTML = tmpl_thread_compilado(thread)
    // insertamos el HTML en la página
    document.getElementById('threadselected').innerHTML = threadHTML;
  })
}

//Metodo que realiza la muestra de la plantilla para crear hilos
var createThread = function() {
  // mezclamos los datos con el HTML de la plantilla para obtener el HTML
  // resultado
  var tmpl_create_thread = compile(templateCreateThread)
  var threadHTML = tmpl_create_thread()
  // insertamos el HTML en la página
  document.getElementById('threads').innerHTML = threadHTML;
  document.getElementById('cancelButton')
      .addEventListener('click', cancelThreadSend);
  document.getElementById('createButton')
      .addEventListener('click', createThreadSend);
}

//Metodo encargado de comunicar con la API para eliminar un hilo concreto
//Si no se pudiera, por que por ejemplo es el hilo de otro usuario, esto
//se mostraría a traves de una simple alerta con el error
var deleteThread = function() {
  servicio_API.deleteThread(this.id.substr(this.id.indexOf('_') + 1))
      .then(result => {
        if (result && result.message) alert(JSON.stringify(result.message))
          // mezclamos los datos con el HTML de la plantilla para obtener el
          // HTML
          if (result && result.error) alert(result.error)
          // insertamos el HTML en la página
          location.reload();
      })
}

//Metodo encargado de volver a la pantalla de iniciar sesion (accion logout)
var logout = function() {
      //Borramos el token del usuario de la cache
  localStorage.username = localStorage.token = undefined;

  document.getElementById('threads').innerHTML = "";
  document.getElementById('sesion').innerHTML = tmpl_sesion();
  document.getElementById('loginButton').addEventListener('click', login);
  document.getElementById('registerButton').addEventListener('click', register);
}
/**
 * Realiza el proceso de autenticacion obteniendo los valores de los campos email y password
 * unicamente, por ello tienen asteriscos, ya que son obligatorios
 */
var login = function() {

  var cuenta = {};
  cuenta.email = document.getElementById('email').value
  cuenta.password = document.getElementById('password').value

  servicio_API.login(cuenta).then(token => {
    var mytoken = token.token;
    localStorage.token = 'Bareer ' + mytoken;
    servicio_API.getMe(localStorage.token).then(sesion => {
      localStorage.username = sesion.username; 
      document.getElementById('sesion').innerHTML = "";
      if(sesion)
        loadThreads(sesion);
      else 
        logout();
    })
  })
}

/**
 * Realiza una carga de todos los threads y los inserta al HTML
 */
var loadThreads = function(logued) {
  document.getElementById('sesion').innerHTML = tmpl_exit(logued.me);
  document.getElementById('logout').addEventListener('click', logout);
  servicio_API.getThreads().then(function(datos) {
    // mezclamos los datos con el HTML de la plantilla para
    // obtener el HTML resultado
    document.getElementById('threads').innerHTML = tmpl_divThreads();

    var listaHTML = tmpl_threads(datos)
    // insertamos el HTML en la página
    document.getElementById('threadslist').innerHTML = listaHTML;

    var threads = document.getElementsByClassName('thread_link');

    for (var i = 0; i < threads.length; i++) {
      // openThread()
      document.getElementById(threads[i].id)
          .addEventListener('click', openThread);
      document.getElementById('delete_' + threads[i].id)
          .addEventListener('click', deleteThread);
    }
    document.getElementById('createThreadButton')
        .addEventListener('click', createThread);
  })
}

/**
 * Metodo encargado de recolectar los datos introducidos por los usuarios y
 * de enviarlos con la api para mostrar el error, si el usuario ya existiera o faltase
 * el dato este error se muestra
 */
var register = function() {
  var cuenta = {};
  cuenta.username = document.getElementById('username').value
  cuenta.email = document.getElementById('email').value
  cuenta.password = document.getElementById('password').value
  servicio_API.register(cuenta).then(result => {
    if (!result._id) {
      document.getElementById('username_fail').textContent =
          result.username || ''
      document.getElementById('email_fail').textContent = result.email || ''
      document.getElementById('password_fail').textContent =
          result.password || ''
    } else {
      alert(
          'Error: ' +
          JSON.stringify(
              'Felicidades ' + result.username +
              ', ya estás registrado, por favor inicia sesión.'))
    }
  })
}

    // manejador de eventos para cuando se carga la página
    // le pedimos la lista de items al servidor y la pintamos en el HTML
    document.addEventListener(
        'DOMContentLoaded',
        function() {
          // alert("Pagina cargada!");
          servicio_API.getMe(localStorage.token).then(logued => {
            if (logued) {
              console.log('Página cargada!: ' + new Date().toLocaleString())
              console.log('token = ' + localStorage.token)
              loadThreads(logued);
            } else {
              document.getElementById('sesion').innerHTML = tmpl_sesion();
              document.getElementById('loginButton')
                  .addEventListener('click', login);
              document.getElementById('registerButton')
                  .addEventListener('click', register);
            }
          })
        })

    /*
    document.getElementById("thread").addEventListener('click', function(){
        alert("Thread pulsado: ");
    })*/

    /*
    document.getElementById('boton_add_item').addEventListener('click',
    function(){
        //Creamos un objeto JS con los datos del nuevo item
        var nuevo = {}
        nuevo.nombre = document.getElementById('nuevo_nombre').value
        nuevo.cantidad = document.getElementById('nuevo_cantidad').value
        nuevo.comentario = document.getElementById('nuevo_comentario').value
        //Enviamos el objeto al servidor, usando el API
        servicio_API.addItem(nuevo).then(function(creado){
             //Añadimos el HTML del nuevo item a la lista
             //1. Mezclamos datos con plantilla handlebars
             var nuevoHTML = tmpl_item_compilada(creado)
             //2. Añadimos el HTML resultante al final de la lista
          document.getElementById('threadslist').insertAdjacentHTML('beforeend',
    nuevoHTML)
        })
     })*/
    /*
    //manejador de eventos para el botón de "Añadir" item a la lista
    document.getElementById('boton_add_item').addEventListener('click',
    function(){
       //Creamos un objeto JS con los datos del nuevo item
       var nuevo = {}
       nuevo.nombre = document.getElementById('nuevo_nombre').value
       nuevo.cantidad = document.getElementById('nuevo_cantidad').value
       nuevo.comentario = document.getElementById('nuevo_comentario').value
       //Enviamos el objeto al servidor, usando el API
       servicio_API.addItem(nuevo).then(function(creado){
             //Añadimos el HTML del nuevo item a la lista
             //1. Mezclamos datos con plantilla handlebars
             var nuevoHTML = tmpl_item_compilada(creado)
             //2. Añadimos el HTML resultante al final de la lista
         document.getElementById('threadslist').insertAdjacentHTML('beforeend',
    nuevoHTML)
       })
    })*/
    /*
    //llamada cuando pulsamos en un enlace "Detalles"
    function verDetalles(id) {
            servicio_API.getThread(id).then(function(thread){
                    //creamos un objeto JS con los datos de los detalles a
    mostrar var datos = {id: thread._id, title: thread.title}
                    //lo fusionamos con la plantilla handlebars
                    var datosHTML = tmpl_detalles_compilada(datos)
                    //metemos el HTML resultante en la página
                //aprovechamos que hemos hecho que el thread con un id
    determinado
                //esté en el HTML en un div con el mismo id
                    var divthread = document.getElementById(id)
                    divthread.insertAdjacentHTML('beforeend', datosHTML)
                    //TEDIOSO: ahora hay que cambiar el enlace "ver detalles"
    por uno "ocultar"
                    //hemos hecho que el HTML del enlace tenga un id con
    "enlace_" y el id del thread var enlaceDetalles =
    document.getElementById('enlace_'+id)
                    //Cambiamos a dónde apunta el enlace
                    enlaceDetalles.href = 'javascript:ocultarDetalles('+ id +')'
                    //cambiamos el texto del enlace
                    enlaceDetalles.innerHTML = 'Ocultar detalles'
            })
    }

    //IMPORTANTE: para que desde la página se pueda llamar a la función,
    // la guardamos en el ámbito global (window). Si no, no será visible,
    //porque el código del main.js no es visible directamente para el HTML, sino
    el bundle.js window.verDetalles = verDetalles
    //Nótese que es el único caso en que desde el HTML (un enlace) llamamos a
    algo de JS
    //El resto de casos es al contrario: los manejadores de eventos de antes los
    hemos
    //definido en JS no en el HTML original

    //llamada cuando pulsamos en un enlace "Ocultar Detalles"
    function ocultarDetalles(id) {
            //forma sencilla de eliminar un fragmento HTML, asignarle la cadena
    vacía
            //usamos outerHTML porque incluye la propia etiqueta, innerHTML
    sería solo el contenido document.getElementById('detalles_'+id).outerHTML =
    ''
            //TEDIOSO: volvemos a poner el enlace en modo "mostrar detalles"
            document.getElementById('enlace_'+id).href =
    'javascript:verDetalles('+id+')'
            document.getElementById('enlace_'+id).innerHTML = 'Detalles'

    }
    //hacemos visible ocultarDetalles para el HTML, por lo mismo que con
    "verDetalles" window.ocultarDetalles = ocultarDetalles*/