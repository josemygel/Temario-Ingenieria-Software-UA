var express = require('express')
var bodyParser = require('body-parser')

var users = new Map()
var lista = new Map()
var idActual = 3
lista.set(1, {id:1, nombre:"Ron", cantidad:"1 botella"})
lista.set(2, {id:2, nombre:"Tomates", cantidad:"1 kg"})
lista.set(3, {id:3, nombre:"Agua", cantidad:"5 litros"})

users.set("adi", {login:"adi", password:"adi"})
users.set("pepe", {login:"pepe", password:"pepe"})


function checkAuth(pet, resp, next) {
    //TO-DO: Ahora mismo SIEMPRE deja hacer cualquier operación por el if(true). Cambiar eso por comprobar auth. Basic
    //1. Extraer la cabecera 'Authorization'
    var token = pet.get("Authorization").split(' ')[1];
  
    //2. Parsear la cabecera para quedarnos con login&pw en Base64
    var result = new Buffer.from(token,'Base64').toString();
  
    //3. Decodificar Base64
    var session = result.split(":");
    
    //4. Separar login y pw y comprobarlos con isLoginAndPasswordOK
    if (isLoginAndPasswordOK(session[0],session[1]))
        next();
    else {
        resp.status(401);
        resp.send("Debes autentificarte");
    }
}

var router = express.Router()
router.use(bodyParser.json())


function isLoginAndPasswordOK(login, password) {
  var user = users.get(login)  
  return (user && user.password==password)
}

router.post('/testCredentials', function(pet, resp){
    var credentials = pet.body
    if (isLoginAndPasswordOK(credentials.login, credentials.password)) {
        resp.status(200).end()
    }
    else
        resp.status(401).end()
})


router.get('/items', checkAuth, function(pet, resp){
   resp.status(200)
   var array = []
   lista.forEach(function(valor) {
        array.push(valor)
   })  
   resp.send(array)
})

router.get('/items/:id', checkAuth, function(pet, resp){
   var id = parseInt(pet.params.id)
   if (isNaN(id)) {
    resp.status(400)
    resp.send("El id debe ser numérico")
   }
   else {
    var resultado = lista.get(id)
    if (resultado) {
        resp.status(200)
        resp.send(resultado) 
    }
    else {
        resp.status(404)
        resp.send({userMessage:"El item no se ha encontrado",devMessage:""})   
    }
   }
})

router.post('/items', checkAuth, function(pet, resp){
  var nuevoItem = pet.body
  idActual++
  var insertado = {id: idActual, nombre: nuevoItem.nombre, cantidad: nuevoItem.cantidad}
  lista.set(idActual, insertado)
  resp.status(201)
  resp.header('Location', 'http://localhost:3000/api/items/'+insertado.id)
  resp.send(insertado)
  resp.end();
})

module.exports = router;