const mongoose = require('mongoose');

const ThreadSchema = mongoose.Schema({
    author: {
        type: mongoose.Schema.Types.ObjectId, ref: 'User',
        required: true
    },
    category: {
        type: String,
        required: true,
        default: "General"
    },
    title: {
        type: String,
        required: true
    },
    content: {
        type: String,
        required: true
    },
    comments: [{ 
        title: String,
        content: String, 
        type: mongoose.Schema.Types.ObjectId, ref: 'User'
    }],
    likeTo: [{
        type: mongoose.Schema.Types.ObjectId, ref: 'User'
    }]
}, {
        timestamps: true
    });

module.exports = mongoose.model('Thread', ThreadSchema);
