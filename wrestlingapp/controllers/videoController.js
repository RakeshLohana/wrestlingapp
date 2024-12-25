// const Video = require('../models/Video');

// exports.uploadVideos = async (req, res) => {
//     try {
//         const userId = req.user._id;
//         const files = req.files.map(file => ({
//             userId,
//             filename: file.filename,
//             path: file.path,
//         }));
//         const videos = await Video.insertMany(files);
//         res.status(201).json(videos);
//     } catch (err) {
//         res.status(400).json({ error: err.message });
//     }
// };

// exports.getUserVideos = async (req, res) => {
//     try {
//         const videos = await Video.find({ userId: req.params.userId });
//         res.json(videos);
//     } catch (err) {
//         res.status(400).json({ error: err.message });
//     }
// };



const Video = require('../models/Video');

exports.uploadVideos = async (req, res) => {
  try {
    const userId = req.user._id;
    const files = req.files.map(file => ({
      userId,
      filename: file.filename,
      path: file.path,
      matchData: req.body.matchData ? JSON.parse(req.body.matchData) : null, // Parse matchData from request
    }));
    const videos = await Video.insertMany(files);
    res.status(201).json(videos);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
};

exports.getUserVideos = async (req, res) => {
  try {
    const videos = await Video.find({ userId: req.params.userId });
    res.json(videos);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
};
