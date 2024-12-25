const express = require('express');
const multer = require('multer');
const { uploadVideos, getUserVideos } = require('../controllers/videoController');
const authenticate = require('../middlewares/authenticate');

const router = express.Router();
const upload = multer({ dest: 'uploads/' });

router.post('/upload', authenticate, upload.array('videos'), uploadVideos);
router.get('/:userId', authenticate, getUserVideos);

module.exports = router;
    