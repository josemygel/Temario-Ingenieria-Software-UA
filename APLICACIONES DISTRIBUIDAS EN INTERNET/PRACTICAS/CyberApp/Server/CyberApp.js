const express = require('express');
//Para examinar los objetos en el body req.body.*
const bodyParser = require('body-parser');

// create express app
const app = express();

// incorporamos CORS para omitir medidas de seguridad de "mismo origen"
var cors = require('cors')
app.use(cors())

app.config = require('./config.js');

// parse requests of content-type - application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }))

// Configuring the database
const dbConfig = require('./config/database.config.js');
const mongoose = require('mongoose');

mongoose.Promise = global.Promise;

// Connecting to the database
mongoose.connect(dbConfig.url, {
    useNewUrlParser: true
}).then(() => {
    console.log("Successfully connected to the database");    
}).catch(err => {
    console.log('Could not connect to the database. Exiting now...', err);
    process.exit();
});

// parse requests of content-type - application/json
app.use(bodyParser.json())

// define a simple route
app.get('/', (req, res) => {
    res.json({"message": "Welcome to CyberApp application. You will can learn a lot of things!!!"});
});

// Require Users routes
require('./app/routes/user.routes.js')(app);
// Require Subjects routes
require('./app/routes/subject.routes.js')(app);
// Require Threads routes
require('./app/routes/thread.routes.js')(app);

// listen for requests
app.listen(3000, () => {
    console.log("CyberApp is listening on port 3000");
});
