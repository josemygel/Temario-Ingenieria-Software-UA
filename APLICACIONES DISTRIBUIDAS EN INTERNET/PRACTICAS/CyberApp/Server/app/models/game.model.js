const mongoose = require('mongoose');

const GameSchema = mongoose.Schema({
    title: {
        type: String,
        required: true
      },
      content: {
          type: String,
          required: true
      },
      category: {
          type: String,
          required: true,
          default: "General"
      },
      solution: {
          type: String,
          required: true
      },
      experience: {
          type: Number,
          required: true,
      },
      requirements: [{
          type: String
      }]
}, {
    timestamps: true
});

module.exports = mongoose.model('Game', GameSchema);
