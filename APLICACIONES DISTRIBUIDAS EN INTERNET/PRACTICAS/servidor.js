/*var gitSemverTags = require('git-semver-tags', []);

gitSemverTags(function(err, tags) {
  console.log(tags);
  //=> [ 'v2.0.0', 'v1.0.0' ]
});
*/

// Utilizar funcionalidades del Ecmascript 6
'use strict'
// Cargamos el módulo de mongoose para poder conectarnos a MongoDB
var mongoose = require('mongoose');
mongoose.connect('mongodb://127.0.0.1/characters', { useNewUrlParser: true }, function (err) {
    if (err) throw err;

    console.log('Successfully connected');
});

var userSchema = mongoose.Schema({
        _id : String,
        slot: Number,
        title: String,
        user: String,
        sex: Number,
        hair: Number,
        color: Number,
        maxhp: Number,
        maxmp: Number,
        hp: Number,
        mp: Number,
        level: { 
            lv: Number, 
            exp: Number 
        },
        profession: { 
            lv: Number, 
            exp: Number 
        },
        heroe: { 
            lv: Number, 
            exp: Number 
        },
        myClass: Number,
        gold: Number,
        goldBank: Number,
        equipment: { 
            pos: Number, 
            cat: Number, 
            subcat: Number, 
            class: Number, 
            data: String },
        inventory: {
            nInv: Number,
            pos: Number, 
            cat: Number, 
            subcat: Number, 
            class: Number, 
            data: String
        }
    });


var userModel = mongoose.Schema({ _id: String });
var characters = mongoose.model('characters',userSchema);

// *Cargamos el fichero app.js con la configuración de Express
// var app = require('./app');


var express = require('express');
var fs = require('fs');
var Item = require('./item');
var nStatic = require('node-static');

var privateKey = fs.readFileSync('certs/server.key');
var certificate = fs.readFileSync('certs/server.crt');

var credentials = {key: privateKey, cert: certificate};

var https = require('https');
https.port = 4443;

var app = express(credentials);

//setting middleware
app.use(express.static('public')); //Serves resources from public folder

app.port = 4080;

app.get('/users/:id', function(req,res) {
    var nick = req.params.id;

    characters.findOne({ user : nick }, function(err, character) {
        if (err) throw err;

        if (character == null) {
            res.status(404);
            
            res.send("<center>ERROR: 404 <br>You haven't account! D=> </center>")
        }else{
            res.status(200);
            res.send(JSON.stringify(character,null,'<br>'));
        }


        console.log(character);
    });
});

app.get('/equipment/:id', function(req,res) {
    var nick = req.params.id;

    characters.findOne({ user : nick}, function(err, character) {
        if (err) throw err;

        if (character == null) {
            res.status(404);
            
            res.send("<center>ERROR: 404 <br>You haven't account! D=> </center>")
        }else{
            res.status(200);
            res.send(cahracter);
        }

        console.log(characters);
    });
});


https.createServer(credentials, app).listen(https.port, function () {
    console.log('Example app with https listening on port ' + https.port +
     '! Go to https://localhost:' + https.port + '/')
  });

app.listen(app.port, function () {
    console.log('Example app listening on port ' + app.port +
     '! Go to http://localhost:' + app.port + '/')
  });