# Frontend - Hospital Management System

This is the React frontend for the Hospital Management System.

## Features

- Modern UI with Mantine and PrimeReact
- Role-based dashboards (Admin, Doctor, Patient)
- Authentication and protected routes
- Patient and doctor profile management
- Appointment scheduling

## Setup

1. **Install dependencies:**
   ```sh
   npm install
   ```

2. **Configure environment variables:**
   - Copy `.env.example` to `.env` and set the backend API URL.

3. **Start the development server:**
   ```sh
   npm start
   ```

## Project Structure

```
Frontend/
├── public/
├── src/
│   ├── Components/
│   ├── Data/
│   ├── Interceptor/
│   ├── Layout/
│   ├── Pages/
│   ├── Routes/
│   ├── Service/
│   ├── Slices/
│   ├── Store.tsx
│   └── Utility/
├── package.json
└── .env.example
```

## License

MIT
