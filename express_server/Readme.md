# Express Server

This is the backend API for the Hospital Management System, built with Express.js and MySQL.

## Features

- RESTful API endpoints for users, doctors, patients, and appointments
- JWT-based authentication
- Modular route handling

## Setup

1. **Install dependencies:**
   ```sh
   npm install
   ```

2. **Configure environment variables:**
   - Copy `.env.example` to `.env` and fill in your database and JWT secret.

3. **Start the server:**
   ```sh
   npm start
   ```

## Project Structure

```
express_server/
├── app.js
├── db.js
├── routes/
│   ├── appointment.js
│   ├── profileDoctor.js
│   ├── profilePatient.js
│   └── user.js
├── package.json
└── .env.example
```

## License

MIT
