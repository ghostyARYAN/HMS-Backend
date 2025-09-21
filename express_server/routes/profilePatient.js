const express = require('express');
const router = express.Router();

const db = require('../db');

// POST /profile/patient/add
router.post('/add', async (req, res) => {
  const { name, email, dob, phone, address, aadharNo, bloodGroup, allergies, chronicDisease } = req.body;
  try {
    // Check for duplicate email or aadharNo
    const [rows] = await db.query('SELECT id FROM patient WHERE email = ? OR aadhar_no = ?', [email, aadharNo]);
    if (rows.length > 0) {
      return res.status(409).json({ message: 'PATIENT_ALREADY_EXISTS' });
    }
    const [result] = await db.query(
      'INSERT INTO patient (name, email, dob, phone, address, aadhar_no, blood_group, allergies, chronic_disease) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)',
      [name, email, dob, phone, address, aadharNo, bloodGroup, allergies, chronicDisease]
    );
    res.status(201).json({ id: result.insertId });
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

// GET /profile/patient/get/:id
router.get('/get/:id', async (req, res) => {
  try {
    const [rows] = await db.query('SELECT * FROM patient WHERE id = ?', [req.params.id]);
    if (rows.length === 0) return res.status(404).json({ message: 'PATIENT_NOT_FOUND' });
    res.json(rows[0]);
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

// PUT /profile/patient/update
router.put('/update', async (req, res) => {
  const { id, name, email, dob, phone, address, aadharNo, bloodGroup, allergies, chronicDisease } = req.body;
  try {
    const [result] = await db.query(
      'UPDATE patient SET name=?, email=?, dob=?, phone=?, address=?, aadhar_no=?, blood_group=?, allergies=?, chronic_disease=? WHERE id=?',
      [name, email, dob, phone, address, aadharNo, bloodGroup, allergies, chronicDisease, id]
    );
    if (result.affectedRows === 0) return res.status(404).json({ message: 'PATIENT_NOT_FOUND' });
    const [rows] = await db.query('SELECT * FROM patient WHERE id = ?', [id]);
    res.json(rows[0]);
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

// GET /profile/patient/exists/:id
router.get('/exists/:id', async (req, res) => {
  try {
    const [rows] = await db.query('SELECT id FROM patient WHERE id = ?', [req.params.id]);
    res.json(rows.length > 0);
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

module.exports = router;