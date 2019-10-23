/**
 * module.exports sirve para añadir las rutas a la variable asignada entre parentesis
 * NO es necesario que se llame app, pero lo haremos asi para no confundirla
 * 
 */
module.exports = (app) => {
    const users = require('../controllers/user.controller.js');
    var middlewares = require('../middlewares.js');

    // Un usuario se registra
    app.post('/register/', users.register);

    // Un usuario inicia sesion
    app.post('/login/', users.login);

    // Recibir la lista de usuarios
    app.get('/users/', middlewares.isAdmin, users.findAll);
    
    // Recibir la información de un usuario
    app.get('/me/', middlewares.checkAuth, users.getMe);
    // Recibir la información de un usuario
    app.put('/me/', middlewares.checkAuth, users.putMe);
    // Recibir la información de un usuario
    app.delete('/me/', middlewares.checkAuth, users.deleteMe);

    // Banea a un usuario
//    app.delete('/users/:userId', middlewares.isAdmin, users.delete);
}
