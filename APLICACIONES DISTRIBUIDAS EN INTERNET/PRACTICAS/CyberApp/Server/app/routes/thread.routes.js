module.exports = (app) => {
    const threads = require('../controllers/thread.controller.js');
    // Require Logged Middleware
    const middlewares = require('../middlewares.js');

    // Create a new Thread
    app.post('/threads', middlewares.checkAuth, threads.create);

    // Retrieve all Threads
    app.get('/threads', threads.findAll);

    // Create a new Comment in a Thread
    app.post('/threads/:threadId', middlewares.checkAuth, threads.addComment);

    // Retrieve a single Threads with threadId
    app.get('/threads/:threadId', threads.findOne);

    // Update a Thread with threadId
    app.put('/threads/:threadId', middlewares.checkAuth, threads.update);

    // Delete a Thread with threadId
    app.delete('/threads/:threadId', middlewares.checkAuth, threads.delete);
}
