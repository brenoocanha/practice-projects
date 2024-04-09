require("dotenv").config();
const express = require("express");
const mongoose = require("mongoose");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");

const port = process.env.PORT || 3000;

const app = express();

// Config JSON Response
app.use(express.json());

// Models
const User = require("./models/User");

// Open Route - Public Route
app.get("/", (req, res) => {
  res.status(200).json({ msg: "Hello World!" });
});

// Private Route
app.get("/user/:id", checkToken, async (req, res) => {
  const id = req.params.id;

  // Check if User Exists
  const user = await User.findById(id, '-password');

  // Validations
  if (!user) {
    return res.status(404).json({ msg: "User not foud" });
  }

  res.status(200).json({ user });

});

function checkToken(req, res, next) {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(" ")[1];

  if (!token) {
    return res.status(401).json({ msg: "Access denied" });
  }

  try {
    const secret = process.env.SECRET;
    jwt.verify(token, secret);
    next();
  } catch (error) {
    res.status(400).json({ msg: "Invalid token" });
  }

}

// Register
app.post("/auth/register", async (req, res) => {
  const { name, email, password, confirmPassword } = req.body;

  // Validations
  if (!name) {
    return res.status(422).json({ msg: "Name is required" });
  }
  if (!email) {
    return res.status(422).json({ msg: "Email is required" });
  }
  if (!password) {
    return res.status(422).json({ msg: "Password is required" });
  }
  if (!confirmPassword) {
    return res.status(422).json({ msg: "Confirmation password is required" });
  }
  if (password !== confirmPassword) {
    return res.status(422).json({ msg: "Passwords don't match" });
  }

  // Check If User Exists
  const userExists = await User.findOne({ email: email });

  if (userExists) {
    return res.status(422).json({ msg: "Email already exists" });
  }

  // Create Password
  const salt = await bcrypt.genSalt(12);
  const passwordHash = await bcrypt.hash(password, salt);

  // Create User
  const user = new User({
    name,
    email,
    password: passwordHash,
  });

  try {
    await user.save();
    res.status(201).json({ msg: "User registered successfully" });
  } catch (error) {
    console.log(error);
    res
      .status(500)
      .json({
        msg: `An error ocurred while trying to register, please try again later`,
      });
  }
});

// Login
app.post("/auth/login", async (req, res) => {
  const { email, password } = req.body;

  // Validations
  if (!email) {
    return res.status(422).json({ msg: "Email is required" });
  }
  if (!password) {
    return res.status(422).json({ msg: "Password is required" });
  }

  // Check If User Exists
  const user = await User.findOne({ email: email });

  if (!user) {
    return res.status(404).json({ msg: "User not found" });
  }

  // Check If Password Match
  const checkPassword = await bcrypt.compare(password, user.password);

  if (!checkPassword) {
    return res.status(422).json({ msg: "Invalid password" });
  }

  try {
    const secret = process.env.SECRET;
    const token = jwt.sign(
      {
        id: user._id,
      },
      secret,
    );

    res.status(200).json({ msg: "User authenticated", token });
  } catch (error) {
    console.log(error);
    res
      .status(500)
      .json({
        msg: `An error ocurred while trying to login, please try again later`,
      });
  }
});

// Credentials
const dbUser = process.env.DB_USER;
const dbPassword = process.env.DB_PASSWORD;
const connectionString = `mongodb+srv://${dbUser}:${dbPassword}@auth.lgwn98x.mongodb.net/?retryWrites=true&w=majority&appName=auth`;

mongoose
  .connect(connectionString)
  .then(() => {
    app.listen(port);
    console.log(`Server listening on port ${port}`);
  })
  .catch((err) => console.log(err));
