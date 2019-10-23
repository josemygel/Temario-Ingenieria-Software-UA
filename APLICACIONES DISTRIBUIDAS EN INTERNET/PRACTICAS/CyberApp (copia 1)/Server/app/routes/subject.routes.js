module.exports = (app) => {
    const subjects = require('../controllers/subject.controller.js');
    // Require Logged Middleware
    var middlewares = require('../middlewares.js');

    // Create a new Subject
    app.post('/subjects/', middlewares.isAdmin, subjects.create);

    // Retrieve all subjects
    app.get('/subjects/', subjects.findAll);

    // Retrieve a single Subject with subjectId
    app.get('/subjects/:subjectId', middlewares.canLearn, subjects.readOne);

    // Exam in Subject
    app.post('/subjects/:subjectId', middlewares.checkAuth, subjects.exam);

    // Update a Subject with subjectId
    app.put('/subjects/:subjectId', middlewares.isAdmin, subjects.update);

    // Delete a Subject with subjectId
    app.delete('/subjects/:subjectId', middlewares.isAdmin, subjects.delete);
}
