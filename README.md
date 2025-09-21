# Hospital Management System (HMS)

This repository contains a full-stack Hospital Management System with a React frontend and an Express.js backend.

## Project Structure

```
HMS-Backend/
├── express_server/   # Express.js backend API
├── Frontend/         # React frontend application
└── .vscode/          # VSCode workspace settings
```

## Getting Started

### Prerequisites

- Node.js (v18+ recommended)
- npm

### Setup

1. **Clone the repository:**
   ```sh
   git clone <repo-url>
   cd HMS-Backend
   ```

2. **Configure environment variables:**
   - Copy `.env.example` to `.env` in both `express_server` and `Frontend` folders and fill in the required values.

3. **Install dependencies:**
   ```sh
   cd express_server
   npm install
   cd ../Frontend
   npm install
   ```

4. **Start the backend server:**
   ```sh
   cd ../express_server
   npm start
   ```

5. **Start the frontend app:**
   ```sh
   cd ../Frontend
   npm start
   ```

## License

MIT