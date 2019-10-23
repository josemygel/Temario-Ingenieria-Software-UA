const Subject = require('../models/subject.model.js');
var middlewares = require('../middlewares.js');

// Create and Save a new subject
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
    //Buscar por categoria, si existe se añadie parte, si no se crea la parte
    if(!req.body.title || !req.body.content || !req.body.experience || !req.body.exam || !req.body.solution)
        return res.status(400).send({error:"Debes indicar titulo, contenido, experiencia(Number), pregunta de examen y solución."})

    var parte = {
            title: req.body.title, 
            content: req.body.content,
            experience: req.body.experience,
            exam: req.body.exam,
            solution: req.body.solution
        };
    var categoria = req.body.category || "Sin categoría.";

    Subject.findOne({category: categoria})
    .then( subject => {
        if(!subject)
        {
            //Creamos el temario
            var subject = new Subject({
                category: categoria,
                part: [parte]
            })

        }else{
            //Actualizamos el temario
            subject.part.push(parte);
        }
            subject.save().then( sub=>{
                //FORMATEAMOS SUBJECT SEGUN LA PARTE QUE DEBA RECIBIR EL USUARIO
                var prev, next, select;
        
                //NOS ASEGURAMOS DE QUE SI NO ESPECIFICA PARTE SE COJA LA PRIMERA
                req.query.part = sub.part[sub.part.length-1].id;
        
                //OBTENEMOS LOS DATOS NECESARIO
                sub.part.map( elem => {
                    if(elem.id == req.query.part)
                    {
                        select = elem;
                        return true;
                    }else{
                        if(!select){
                            prev = elem.id;
                            return false;
                        }else if(!select.next){
                            next = elem.id;
                            return false;
                        }
                    }
                });
        
        
                //CREAMOS EL TEMARIO
                var temario = {
                    next: next,
                    prev: prev,
                    subject: sub.id,
                    _id: select.id,
                    experience: select.experience,
                    title: select.title,
                    content: select.content,
                    exam: select.exam,
                    solution: select.solution,
                    category: sub.category
                }
    
                return res.status(201).send(temario);
            })
        
                    
        
    }).catch(err => {
        return res.status(500).send({
            message: err.message || "Some error occurred while creating the subject."
        });
    });
};

// Retrieve and return all subjects from the database.
exports.findAll = (req, res) => {
    Subject.find()
    .then(subjects => {
        if(subjects){
        subjects.map(elem => { 
            elem.part = undefined;
            elem.requirements = undefined;
        });

        return res.send(subjects).end();
        }
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while retrieving subjects."
        });
    });
};

// Find a single subject with a subjectId
exports.readOne = (req, res) => {

    // passing options
    Subject.findById(req.params.subjectId)
    .then(subject => {
        if(!subject) {
            return res.status(404).send({
                message: "subject not found with id " + req.params.subjectId
            });            
        }

        //FORMATEAMOS SUBJECT SEGUN LA PARTE QUE DEBA RECIBIR EL USUARIO
        var prev;
        var next;
        var select;
        var temario = {};
        //NOS ASEGURAMOS DE QUE SI NO ESPECIFICA PARTE SE COJA LA PRIMERA

        if(req.query.part){
            //OBTENEMOS LOS DATOS NECESARIO
            subject.part.map( elem => {
                if(elem.id == req.query.part)
                {
                    select = elem;
                    return true;
                }else{
                    if(!select){
                        prev = elem.id;
                        return false;
                    }else if(!select.next){
                        next = elem.id;
                        return false;
                    }
                }
            });


            //CREAMOS EL TEMARIO
            temario = {
                subject: subject.id,
                next: next,
                prev: prev,
                _id: select.id,
                experience: select.experience,
                title: select.title,
                content: select.content,
                exam: select.exam,
                solution: select.solution,
                category: subject.category
            }
            return res.status(200).send(temario);
        }else{
            temario = {
                subject: subject.id,
                category: subject.category,
                part: subject.part
            }

            temario.part.map( elem  => {
                elem.content = elem.exam = elem.solution = undefined;
                elem.requirements = undefined;
            })
            var resultado = middlewares.canRead(req,temario.subject,temario.part);
            console.log(resultado)
            return res.status(200).send(temario);
        }
        
        
    }).catch(err => {
        if(err.kind === 'ObjectId') {
            return res.status(404).send({
                message: "subject not found with id " + req.params.subjectId
            });                
        }
        return res.status(500).send({
            message: "Error retrieving subject with id " + req.params.subjectId
        });
    });  

};

// A user doing exam
exports.exam = (req, res) => {

    return res.status(200).send("I haven't test, please, try again later...");
}

// Update a subject identified by the subjectId in the request
exports.update = (req, res) => {
    // Validate Request
    if(!req.body.content) {
        return res.status(400).send({
            message: "subject content can not be empty"
        });
    }

    // Find subject and update it with the request body
    Subject.findByIdAndUpdate(req.params.subjectId, {
        title: req.body.title || "Untitled subject",
        content: req.body.content
    }, {new: true})
    .then(subject => {
        if(!subject) {
            return res.status(404).send({
                message: "subject not found with id " + req.params.subjectId
            });
        }
        res.send(subject);
    }).catch(err => {
        if(err.kind === 'ObjectId') {
            return res.status(404).send({
                message: "subject not found with id " + req.params.subjectId
            });                
        }
        return res.status(500).send({
            message: "Error updating subject with id " + req.params.subjectId
        });
    });
};

// Delete a subject with the specified subjectId in the request
exports.delete = (req, res) => {
    Subject.findByIdAndRemove(req.params.subjectId)
    .then(subject => {
        if(!subject) {
            return res.status(404).send({
                message: "subject not found with id " + req.params.subjectId
            });
        }
        res.send({message: "subject deleted successfully!"});
    }).catch(err => {
        if(err.kind === 'ObjectId' || err.name === 'NotFound') {
            return res.status(404).send({
                message: "subject not found with id " + req.params.subjectId
            });                
        }
        return res.status(500).send({
            message: "Could not delete subject with id " + req.params.subjectId
        });
    });
};
