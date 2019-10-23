const Thread = require('../models/thread.model.js');
var middlewares = require('../middlewares.js');

// Create and Save a new thread
exports.create = (req, res) => {
    // Validate request
    if(!req.body.title) {
        return res.status(400).send({
            message: "I need a title!"
        });
    }else if(!req.body.content) {
        return res.status(400).send({
            message: "I need a content!"
        });
    }
    // Create a thread
    const thread = new Thread({
        title: req.body.title, 
        content: req.body.content,
        category: req.body.category || "General",
        author: req.userID
    });

    // Save thread in the database
    thread.save()
    .then(data => {
        res.status(201).send(data);
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while creating the thread."
        });
    });
};

// Retrieve and return all threads from the database.
exports.findAll = (req, res) => {
    Thread.find().populate('author')
    .then(threads => {
        threads.map( elem => { elem.comments = undefined; elem.likeTo = undefined; elem.author = middlewares.cleanUser(elem.author)});
        res.send(threads);
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while retrieving threads."
        });
    });
};

// Find a single thread with a threadId
exports.findOne = (req, res) => {
    
    // passing options
    Thread.findById(req.params.threadId)
    .then(thread => {
        if(!thread) {
            return res.status(404).send({
                message: "Thread not found with id " + req.params.threadId
            });            
        }
        
        thread = Array.isArray(thread) ? thread[0] : thread;
        res.send(thread);

    }).catch(err => {
        if(err.kind === 'ObjectId') {
            return res.status(404).send({
                message: "Thread not found with id " + req.params.threadId
            });                
        }
        return res.status(500).send({
            message: "Error retrieving thread with id " + req.params.threadId
        });
    });    

};

// Update a thread identified by the threadId in the request
exports.update = (req, res) => {
    // Validate Request
    if(!req.body.content && !req.body.title) {
        return res.status(400).send({
            error: "Especifica usuario y contraseña para modificar el hilo."
        });
    }

    Thread.findByIdAndUpdate(req.params.threadId).populate("author")
    .then( thread => {
        if(thread.author.username == req.username || req.isAdmin){

           thread.title = req.body.title||thread.title;
           thread.content = req.body.content||thread.content;
           thread.category = req.body.category||thread.category;
            
           thread.save().then(()=>{
            res.status(201).send(thread || "THEAD OK!").end();
           })
        }else{
            res.status(400).send("YOU AREN'T THE AUTHOR OF THE THREAD!").end();
        }
    });

};

    

// Update a thread identified by the threadId in the request
exports.addComment = (req, res) => {
    // Validate Request
    if(!req.body.contentComment) {
        return res.status(400).send({
            message: "thread content can not be empty"
        });
    }

    // Validate Request
    if(!req.body.titleComment) {
        return res.status(400).send({
            message: "Thread title can not be empty"
        });
    }

    // Find thread and update it with the request body
    Thread.findByIdAndUpdate(req.params.threadId)
    .then(thread => {
        if(!thread) {
            return res.status(404).send({
                message: "Thread not found with id " + req.params.threadId
            });
        }
        
        var hoy = new Date();
        comment = {
            titleComment: req.body.titleComment, 
            contentComment: req.body.contentComment,
            date: hoy.toLocaleDateString('ES')
        };

        thread.comments.push(comment);
        thread.save();

        res.send(thread);
    }).catch(err => {
        if(err.kind === 'ObjectId') {
            return res.status(404).send({
                message: "thread not found with id " + req.params.threadId
            });                
        }
        return res.status(500).send({
            message: "Error creating comment thread with id " + req.params.threadId
        });
    });
};

// Delete a thread with the specified threadId in the request
exports.delete = (req, res) => {
    Thread.findById(req.params.threadId)
    .populate('author')
    .then(thread => {
        if(!thread) {
            return res.status(404).send({
                message: "thread not found with id " + req.params.threadId
            });
        }
        
        if(thread.author.username == req.username || req.isAdmin)
        {
            Thread.findByIdAndDelete(req.params.threadId)
            .then( thread => {
                res.send({message: "Thread deleted successfully!"});
            });
        }else{
            var fail = { error: "You can't delete threads of others users!" };
            res.status(401).send(fail);
        }
    }).catch(err => {
        if(err.kind === 'ObjectId' || err.name === 'NotFound') {
            return res.status(404).send({
                message: "thread not found with id " + req.params.threadId
            });                
        }
        return res.status(500).send({
            message: "Could not delete thread with id " + req.params.threadId
        });
    });
};
