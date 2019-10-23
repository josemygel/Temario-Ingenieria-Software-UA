const mongoose = require('mongoose');

const UserSchema = mongoose.Schema({
    username: {
        type: String,
        unique: true,
        required: true
      },
    email: {
        type: String,
        unique: true,
        required: true
    },
    password:{
        type: String,
        required: true
    },
    isAdmin:{
        type: Boolean,
        default: false
    },
    level:{
        type: Number,
        default: 1
    },
    experience:{
        type: Number,
        default: 0
    },
    games:[{
        type: mongoose.Schema.Types.ObjectId, ref: 'Game'
    }],
    //Partes de temario aprobadas
    subjects:[{
        type: mongoose.Schema.Types.ObjectId, ref: 'Subject',
        parts:[{
            type: mongoose.Schema.Types.ObjectId, ref: 'Subject.part'
        }]
    }]
}, {
    timestamps: true
});

module.exports = mongoose.model('User', UserSchema);
