// const mongoose = require('mongoose');

// const videoSchema = new mongoose.Schema({
//     userId: { type: mongoose.Schema.Types.ObjectId, ref: 'User', required: true },
//     filename: { type: String, required: true },
//     path: { type: String, required: true },
// });

// module.exports = mongoose.model('Video', videoSchema);



const mongoose = require('mongoose');

const scoreSchema = new mongoose.Schema({
  matchScoreID: { type: Number, required: true },
  matchID: { type: Number, required: true },
  time: { type: String, required: true },
  period: { type: Number, required: true },
  scoreID: { type: Number, required: true },
  scorer: { type: String, required: true },
  timestamp: { type: Date, required: true },
  description: { type: String, required: true },
  color: { type: String, required: true },
  greenPlayer: { type: String, required: true },
  redPlayer: { type: String, required: true },
});

const matchDataSchema = new mongoose.Schema({
  redPlayerName: { type: String, required: true },
  greenPlayerName: { type: String, required: true },
  scores: [scoreSchema],
});

const videoSchema = new mongoose.Schema({
  userId: { type: mongoose.Schema.Types.ObjectId, ref: 'User', required: true },
  filename: { type: String, required: true },
  path: { type: String, required: true },
  matchData: matchDataSchema, // Add matchData field
});

module.exports = mongoose.model('Video', videoSchema);
