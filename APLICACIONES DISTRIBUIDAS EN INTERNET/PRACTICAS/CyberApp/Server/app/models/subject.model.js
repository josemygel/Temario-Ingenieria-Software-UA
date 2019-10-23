const mongoose = require('mongoose');

const SubjectSchema = mongoose.Schema({
    category: {
        type: String,
        required: true,
        unique: true
    },
    //Cada Temario lleva distintas partes, y se usará para indicar cuantas ha logrado el usuario y a cual quiere acceder
    part:[{
        title: {
            type: String,
            required: true
        },
        content: {
            type: String,
            required: true
        },
        experience: {
            type: Number,
            required: true
        },
        exam: {
            type: String,
            required: true
        },
        solution: {
            type: String,
            required: true
        },
        readable: {
            type: Boolean,
            default: false
        }
    }],
    //Un temario dependerá de partes de otros temarios (Para llegar a la parte 3, se obvia que pasó la 1 y la 2 de dicho temario)
    requirements: [{
        type: mongoose.Schema.Types.ObjectId, ref: 'Subject',
        part: Number
    }]
}, {
    timestamps: true
});

module.exports = mongoose.model('Subject', SubjectSchema);
