const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');

const appointmentRoutes = require('./routes/appointment');
const profileDoctorRoutes = require('./routes/profileDoctor');
const profilePatientRoutes = require('./routes/profilePatient');
const userRoutes = require('./routes/user');

const app = express();
app.use(cors());
app.use(bodyParser.json());

app.use('/appointment', appointmentRoutes);
app.use('/profile/doctor', profileDoctorRoutes);
app.use('/profile/patient', profilePatientRoutes);
app.use('/user', userRoutes);

const PORT = process.env.PORT || 3001;
app.listen(PORT, () => {
  console.log(`Express server running on port ${PORT}`);
});