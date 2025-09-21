const express = require('express');
const router = express.Router();

const db = require('../db');

// POST /profile/doctor/add
router.post('/add', async (req, res) => {
  const { name, email, dob, phone, address, licenseNo, specialization, department, totalExp } = req.body;
  try {
    // Check for duplicate email or licenseNo
    const [rows] = await db.query('SELECT id FROM doctor WHERE email = ? OR license_no = ?', [email, licenseNo]);
    if (rows.length > 0) {
      return res.status(409).json({ message: 'DOCTOR_ALREADY_EXISTS' });
    }
    const [result] = await db.query(
      'INSERT INTO doctor (name, email, dob, phone, address, license_no, specialization, department, total_exp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)',
      [name, email, dob, phone, address, licenseNo, specialization, department, totalExp]
    );
    res.status(201).json({ id: result.insertId });
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

// GET /profile/doctor/get/:id
router.get('/get/:id', async (req, res) => {
  try {
    const [rows] = await db.query('SELECT * FROM doctor WHERE id = ?', [req.params.id]);
    if (rows.length === 0) return res.status(404).json({ message: 'DOCTOR_NOT_FOUND' });
    res.json(rows[0]);
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

// PUT /profile/doctor/update
router.put('/update', async (req, res) => {
  const { id, name, email, dob, phone, address, licenseNo, specialization, department, totalExp } = req.body;
  try {
    const [result] = await db.query(
      'UPDATE doctor SET name=?, email=?, dob=?, phone=?, address=?, license_no=?, specialization=?, department=?, total_exp=? WHERE id=?',
      [name, email, dob, phone, address, licenseNo, specialization, department, totalExp, id]
    );
    if (result.affectedRows === 0) return res.status(404).json({ message: 'DOCTOR_NOT_FOUND' });
    const [rows] = await db.query('SELECT * FROM doctor WHERE id = ?', [id]);
    res.json(rows[0]);
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

// GET /profile/doctor/exists/:id
router.get('/exists/:id', async (req, res) => {
  try {
    const [rows] = await db.query('SELECT id FROM doctor WHERE id = ?', [req.params.id]);
    res.json(rows.length > 0);
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

// GET /profile/doctor/dropdown
router.get('/dropdown', async (req, res) => {
  try {
    const [rows] = await db.query('SELECT id, name FROM doctor');
    res.json(rows);
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

module.exports = router;