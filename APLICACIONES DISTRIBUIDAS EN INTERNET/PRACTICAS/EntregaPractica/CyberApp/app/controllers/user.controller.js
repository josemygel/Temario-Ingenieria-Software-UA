const User = require('../models/user.model.js');
var config = require('../../config');
var jwt = require('jwt-simple');

var moment = require('moment');  //para trabajar cómodamente con fechas
var bcrypt = require('bcryptjs'); //Para encriptar/desencriptar

var middlewares = require('../middlewares.js');

// Register a new user
exports.register = (req, res) => {
    var fail = {};

    if(!req.body.password)
        fail.password = "Necesito un password!"
    
    if(!req.body.username)
        fail.username = "Necesito un username!"

    if(!req.body.email)
        fail.email = "Necesito un email!"
    
    if(fail.password || fail.username || fail.email)
        return res.status(400).send(fail).end();

    var hashedPassword = bcrypt.hashSync(req.body.password, 8);
    
    User.findOne({email: req.body.email})
    .then( usuario => {
        if(usuario!=null){
            var fail = {};
            fail.email = "El correo ya existe!";

            User.findOne({username: req.body.username})
            .then( usu => {
                if(usu)
                    fail.username = "El usuario ya existe!";
                return res.status(400).send(fail);
            
            }).catch(err =>{
                return res.status(400).send(fail);
            })
        }else throw "Correo no encontrado";
    }).catch(err =>{
        User.findOne({username: req.body.username})
        .then( user => {
            if(user){
                var fail = {};
                fail.username = "El usuario ya existe!";
                return res.status(400).send(fail);
            }else throw "Usuario no encontrado";
        }).catch(err =>{
            new User({
                username: req.body.username,
                email: req.body.email,
                password: hashedPassword
                //La siguiente linea es solo para modo desarrollo
                ,isAdmin: req.body.username == "admin"
            }).save()
            .then( user => {
                return res.status(201).send(middlewares.cleanUserLite(user)).end();
            }).catch( err => {
                return res.status(400).send(err).end();
            })
        });
    });
};

// Register a new user
exports.login = (req, res) => {
    if(!req.body.email){
        var fail = {};
        fail.email = "Debes introducir un email!";
        return res.status(400).send(fail).end()
    }else if(!req.body.password){
        var fail = {};
        fail.password = "Debes introducir una contraseña!";
        return res.status(400).send(fail).end()
    }

    User.findOne({email: req.body.email})
    .then( usuario => {
        var fail = {};
        
        //Comprobando contraseñas
        if(!bcrypt.compareSync(req.body.password, usuario.password)){
                fail.password = "Password is incorrect";
                return res.status(400).send(fail).end();
        }else{

            var payload = {
                userID: usuario.id,
                expire: moment().add(2, 'days').valueOf()
            }

            //crear el JWT a partir de payload + secret
            var token = jwt.encode(payload, config.TOKEN_SECRET);
    
            return res.status(200).send({token: token});
        }
    }).catch( err => {
        var fail = {
            email: "Email no registrado"
        };
        res.status(404).send(fail).end();
    });
};

// Retrieve and return all users from the database.
exports.findAll = (req, res) => {
    User.find()
    .then(users => {
        //COMPROBAR QUE FUNCIONAAAA
        res.send(users.map(middlewares.cleanUserLite));
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while retrieving users."
        });
    });
};

// Find a single user with a userId
exports.getMe = (req, res) => {
// passing options
    User.findOne({ username: req.username })
    .then(user => {
        res.send({ me: middlewares.cleanUserLite(user) });
    }).catch(err => {
        if(err.kind === 'ObjectId') {
            return res.status(404).send({
                message: "Usuario no encontrado"
            });                
        }
        return res.status(500).send({
            message: "Error buscando el usuario"
        });
    });

};

// Update a user identified by the userId in the request
exports.putMe = (req, res) => {
    //Express-validator
    if(req.body.email==null && req.body.password==null)
        return res.status(400).send({
            fail: "Debes enviar tu email y/o tu contraseña!"
        })

    User.findOne({ username: req.username })
    .then( user => {

        newpassword = false;
        newemail = false;

        if(user && req.body.email)
        {
            if(req.body.email == user.email)
            {
                var fail = {};
                fail.email = "Escribe una dirección de correo nueva!"; 
                return res.status(400).send(fail);
            }else{

            User.findOne({email: req.body.email})
            .then( usuario => {
                if(usuario)
                    return res.status(400).send("Este correo ya está en uso!");
                    
                else(!usuario)
                {
                    if(req.body.email != user.email){
                        user.email = req.body.email;
                        newemail = true;
                    }
                    if(req.body.password){
                        user.password = bcrypt.hashSync(req.body.password,8);
                        newpassword = true;
                    }
                    
                    user.save().then(()=>{
                        user = middlewares.cleanUserLite(user);
            
                        return res.send( { me: user, newpassword: newpassword, newemail: newemail} );
                    })
                }
            });
        }
        }
        else if(user && req.body.password)
        {
            user.password = bcrypt.hashSync(req.body.password,8);
            newpassword = true;
            user.save().then(()=>{
                user = middlewares.cleanUserLite(user);
                return res.send( { me: user, newpassword: newpassword, newemail: newemail} );
            })
        }
        else
        {
            console.log(user)
            return res.status(404).send("USER NOT FOUND!");
        }
    }).catch(err => {
        return res.send("Error actualizando usuario.");
    });
};



// Retrieve and return all users from the database.
exports.deleteMe = (req, res) => {
    User.findByIdAndRemove(req.userID)
    .then(user => {
        if(!user) {
            return res.status(404).send({
                message: "No se encuentra tu cuenta."
            });
        }
        res.send({message: "Usuario eliminado definitivamente."});
    }).catch(err => {
        if(err.kind === 'ObjectId' || err.name === 'NotFound') {
            return res.status(404).send({
                message: "Usuario no encontrado."
            });                
        }
        return res.status(500).send({
            message: "No se pudo borrar el usuario."
        });
    });
};