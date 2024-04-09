# NodeJS Authentication API

This is a simple authentication API built with NodeJS, using MongoDB for data storage and JWT for handling authentication.

## Features

- User registration and login
- Password hashing with bcrypt
- JWT token generation for authenticated sessions
- Public and private routes

## Prerequisites

Before you begin, ensure you have met the following requirements:
- NodeJS installed on your machine
- A MongoDB database

## Getting Started

To get a local copy up and running follow these simple steps.

### 1. Clone the repository:
```bash
git clone https://your-repository-url.git
```

### 2. Navigate to the project directory:
```bash
cd your-project-directory
```

### 3. Install the dependencies:
```bash
npm install
```

### 4. Set up your environment variables:
- Create a .env file in the root directory
- Add the following variables:
  - PORT: The port number for the server
  - DB_USER: Your MongoDB username
  - DB_PASSWORD: Your MongoDB password
  - SECRET: A secret key for JWT

### 5. Start the server:
```bash
npm run start
```
