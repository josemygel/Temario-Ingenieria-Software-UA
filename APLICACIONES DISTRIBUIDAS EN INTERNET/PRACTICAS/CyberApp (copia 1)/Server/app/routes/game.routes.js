module.exports = (app) => {
    const games = require('../controllers/game.controller.js');
    // Require Logged Middleware
    var middlewares = require('../middlewares.js');

    // Create a new Game
    app.post('/games', middlewares.checkAuth, middlewares.isAdmin, games.create);

    // Retrieve all Games
    app.get('/games', middlewares.checkAuth ,games.findAll);

    // Retrieve a single Game with gameId
    app.get('/games/:gameId', middlewares.checkAuth, games.findOne);


    // Retrieve a single Game with gameId
    app.post('/games/:gameId', middlewares.checkAuth, middlewares.canPlay, games.play);

    // Update a Game with gameId
    app.put('/games/:gameId', middlewares.checkAuth, middlewares.isAdmin, games.update);

    // Delete a Game with gameId
    app.delete('/games/:gameId', middlewares.checkAuth, middlewares.isAdmin, games.delete);
}