const express = require('express');
const router = express.Router();

const db = require('../db');

// POST /appointment/schedule
router.post('/schedule', async (req, res) => {
  const { patientId, doctorId, appointmentTime, reason, notes } = req.body;
  try {
    // In real app, check doctorExists and patientExists via ProfileMS
    const [result] = await db.query(
      'INSERT INTO appointment (patient_id, doctor_id, appointment_time, status, reason, notes) VALUES (?, ?, ?, ?, ?, ?)',
      [patientId, doctorId, appointmentTime, 'SCHEDULED', reason, notes]
    );
    res.status(201).json({ id: result.insertId });
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

// PUT /appointment/cancel/:appointmentId
router.put('/cancel/:appointmentId', async (req, res) => {
  try {
    const [rows] = await db.query('SELECT status FROM appointment WHERE id = ?', [req.params.appointmentId]);
    if (rows.length === 0) return res.status(404).json({ message: 'APPOINTMENT_NOT_FOUND' });
    if (rows[0].status === 'CANCELLED') {
      return res.status(409).json({ message: 'APPOINTMENT_ALREADY_CANCELLED' });
    }
    await db.query('UPDATE appointment SET status = ? WHERE id = ?', ['CANCELLED', req.params.appointmentId]);
    res.status(204).send();
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

// GET /appointment/details/:appointmentId
router.get('/details/:appointmentId', async (req, res) => {
  try {
    const [rows] = await db.query('SELECT * FROM appointment WHERE id = ?', [req.params.appointmentId]);
    if (rows.length === 0) return res.status(404).json({ message: 'APPOINTMENT_NOT_FOUND' });
    res.json(rows[0]);
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

// GET /appointment/details/name/:appointmentId
router.get('/details/name/:appointmentId', async (req, res) => {
  try {
    const [rows] = await db.query('SELECT a.*, d.name as doctorName, p.name as patientName FROM appointment a LEFT JOIN doctor d ON a.doctor_id = d.id LEFT JOIN patient p ON a.patient_id = p.id WHERE a.id = ?', [req.params.appointmentId]);
    if (rows.length === 0) return res.status(404).json({ message: 'APPOINTMENT_NOT_FOUND' });
    res.json(rows[0]);
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

// GET /appointment/getAllByPatient/:patientId
router.get('/getAllByPatient/:patientId', async (req, res) => {
  try {
    const [rows] = await db.query('SELECT * FROM appointment WHERE patient_id = ?', [req.params.patientId]);
    res.json(rows);
  } catch (err) {
    res.status(500).json({ message: 'DB_ERROR', error: err.message });
  }
});

module.exports = router;