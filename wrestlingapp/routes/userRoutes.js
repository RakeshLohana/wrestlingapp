const express = require('express');
const authenticate = require('../middlewares/authenticate');
const router = express.Router();

// Protected route example
router.get('/profile', authenticate, (req, res) => {
    res.json({ user: req.user });
});

module.exports = router;
