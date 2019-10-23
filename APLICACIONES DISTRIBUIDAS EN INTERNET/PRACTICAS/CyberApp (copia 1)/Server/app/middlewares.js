// middleware.js
var jwt = require('jwt-simple');
var moment = require('moment');
var config = require('../config');
const User = require('./models/user.model.js');
const Subject = require('./models/subject.model.js');

exports.cleanUserLite = function(user){
    //Se limpian los datos basicos (contraseña, __v, cuando se actualizo la cuenta por ultima vez y si es admin)
    if(!user) return user;
    user.password = user.isAdmin = user.updatedAt = user.__v = undefined;
    return user;
}

exports.cleanUser = function(user){
    if(!user) return user;
    user = exports.cleanUserLite(user);
    //Se realizará una limpieza de todos los datos que NO deben recibir los usuarios cuando se solicita un usuario (solo recibirán su nivel, su id y su username de momento)
    user.experience = user.games = user.subjects = user.email = undefined;
    return user;
}

exports.checkAuth = function (req, res, next) {

    if(req && req.headers && req.headers.authorization)
    console.log(req.headers.authorization)
    else console.log("TOKEN inexistente en register.")

    if (!req.headers.authorization) {
        var fail = { error : "Por favor, inicia sesión para acceder aquí." };
        return res.status(401).send(fail);
    }

    var token = req.headers.authorization.split(" ")[1];

    try{
        var payload = jwt.decode(token, config.TOKEN_SECRET);

        //Expriación aun no pasó
        if (payload.expire <= moment().unix()) {
            var fail = { error : "La sesión ha caducado." };
            return res.status(400).send(fail);
        }else{
            if(!payload.userID) {
                var fail = { error : "Por favor, inicia sesión para acceder aquí." };
                return res.status(401).send(fail);
            }else{
                User.findById(payload.userID)
                .then( user => {
                    req.userID = user.id;
                    req.username = user.username;
                    req.isAdmin = user.isAdmin;
                    req.subjects = user.subjects;
                
                    //console.log("Username: " + req.username + " admin: " + req.isAdmin + "   Request: " + req.method + " " + req.url);

                    return next();
                }).catch( err =>{
                    var fail = {error : "¡Por favor, inicia sesión!" || err};
                    return res.status(401).send(fail);
                })
            }
        }
    }catch (err) {
        var fail = {error : "Por favor, inicia sesión." || err};
        return res.status(401).send(fail);
    };
};

exports.canRead = function (req, subjectID, partes) {
    
    if(!req.subjects || req.subjects.indexOf(subjectID) == -1){
        partes[0].readable = true;
        return false;
    }else{
        var next = false;
        var now = true;

        partes.map(elem => {
            if(now)
            {
                now = next;
                elem.readable = true;

                if(req.subjects.parts.indexOf(elem.id) == -1)
                    next = false;

                else 
                    now = next = true;
            }
        })
    }
}

exports.canLearn = function (req, res, next) {
    if(!req.query.part){
        return next();
    }else{
        Subject.findById(req.params.subjectId)
        .then( subject => {
            if(!subject)
                return res.status(404).send( {error: "Subject not found!"});
                
            if(subject.part[0].id == req.query.part){
                return next();
            }else{
                exports.checkAuth(req,res, () => {
                    if(!req.subjects.length == 0)
                        return res.status(401).send({error: "No puedes entrar en este temario."})
                    var temario = req.subjects.filter(elem => {return elem.id==subject.id})[0];
                    var partes = temario.part;

                    exports.canRead(req,subject.id,partes);
    
                    if(partes.indexOf(req.query.part) != -1 && partes[partes.indexOf(req.query.part)].readable){
                            return next();
                    }else{
                        return res.status(400).send({ error: "ERROR, part not found!" });
                    }
                })
            }
        }).catch(err=>{
            return res.status(400).send("ERROR IN CAN LEARN?");
        })
    }
}

exports.canExam = function(req, res, next) {
    if(!req.query.part)
        return next();
    else return res.status(400).send("IMPLEMENTAR: inicia sesión para seguir aprendiendo y guardar tus avances.")
}

exports.canPlay = function (req, res, next) {
    exports.checkAuth(req,res, () => {
    //SI LOS REQUISITOS DEL JUEGO SELECCIONADO NO ESTAN EN LOS LOGROS DEL USUARIO
    //SE DEBE ENVIAR UN MENSAJE DE ERROR, Y ENVIARLE LOS LOGROS QUE LE HACEN FALTA
 
    //DE MOMENTO, NO VAMOS A VERIFICAR SI CUMPLE CON LOS LOGROS, solo comprobaremos que el usuario ESTE YA logueado para jugar
    //SI CUMPLE LOS REQUISITOS, NEXT() 
    return next();
    });
};

//ANTES DE HACER isAdmin hay que hacer checkAuth
exports.isAdmin = function (req, res, next) {
    exports.checkAuth(req, res, function(){
        if(!req.username || !req.isAdmin){
            var fail = {error : "No estás autorizado para entrar aquí."};
            return res.status(401).send(fail);
        }else{
            return next();
        }
    });
};