const express = require('express');
const router = express.Router();

const db = require('../db');
const jwt = require('jsonwebtoken');
const SECRET = process.env.JWT_SECRET || 'deac50fc5b0d49204fa5ed757df86f7322548994248ad9cc93174ae59f92e86f4140b1136fd215b0afbc16a3db8b5ed181be8a0ddfe0878b39b2b7343ef51a62';

// POST /user/register
router.post('/register', async (req, res) => {
  const { name, email, password, role } = req.body;
  if (!name || !email || !password || !role) {
    return res.status(400).json({ message: 'Missing required fields' });
  }
  try {
    // Check if user exists
    const [rows] = await db.query('SELECT id FROM user WHERE email = ?', [email]);
    if (rows.length > 0) {
      return res.status(409).json({ message: 'USER_ALREADY_EXISTS' });
    }
    // Insert user (for demo, password is plain; hash in production)
    await db.query('INSERT INTO user (name, email, password, role) VALUES (?, ?, ?, ?)', [name, email, password, role]);
    res.status(201).json({ message: 'Account Created.' });
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

// POST /user/login
router.post('/login', async (req, res) => {
  const { email, password } = req.body;
  try {
    const [rows] = await db.query('SELECT * FROM user WHERE email = ?', [email]);
    if (rows.length === 0 || rows[0].password !== password) {
      return res.status(401).json({ message: 'INVALID_CREDENTIALS' });
    }
    const user = rows[0];
    // In real app, use JWT claims as in UserMS/jwt/JwtUtil.java
    const token = jwt.sign(
      {
        id: user.id,
        email: user.email,
        role: user.role,
        name: user.name,
        profileId: user.profile_id || null
      },
      SECRET,
      { expiresIn: '5h' }
    );
    res.json({ token });
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

// GET /user/test
router.get('/test', (req, res) => {
  res.status(200).send('Test');
});

module.exports = router;