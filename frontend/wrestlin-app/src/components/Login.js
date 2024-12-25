// src/components/Login.js

import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './css/Login.css'; // Import the CSS file

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://192.168.0.103:5000/api/auth/login', {
        username: email,  // Ensure you're sending 'username' as email
        password,
      });
      console.log(response.data);
      localStorage.setItem('token', response.data.token); // Save token to localStorage
      localStorage.setItem('userId', response.data.user._id); // Store userId
      navigate('/videos'); // Redirect to videos page
    } catch (err) {
      setError('Invalid credentials');
    }
  };

  return (
    <div className="container">
      <div className="login-form">
        <h2>Login</h2>
        {error && <p className="error">{error}</p>}
        <form onSubmit={handleSubmit}>
          <input
            type="email" // Change to 'email' for better accessibility
            placeholder="Username"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button type="submit">Login</button>
        </form>
      </div>
    </div>
  );
}

export default Login;
