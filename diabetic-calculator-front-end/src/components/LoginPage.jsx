import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const LoginPage = ({ onLogin }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleLogin = () => {
        const storedUsername = localStorage.getItem('username');
        if (username === storedUsername) {
            onLogin(username);
            navigate('/');
        } else {
            setError('Username does not exist. Please sign up.');
        }
    };

    return (
        <div className="login-page">
            <h2>Login</h2>
            {error && <p>{error}</p>}
            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <button onClick={handleLogin}>Login</button>
            <button onClick={() => navigate('/signup')}>Sign Up</button>
        </div>
    );
};

export default LoginPage;
