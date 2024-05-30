// src/App.jsx

import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Header from './components/Header';
import LoginPage from './components/LoginPage';
import SignUpPage from './components/SignUpPage';
import HomePage from './components/HomePage';
import ProfilePage from './components/ProfilePage';
import AboutPage from './components/AboutPage';
import ErrorPage from './components/ErrorPage';
import './index.css';  // Import CSS

const App = () => {
    const [username, setUsername] = useState('');
    const [isDarkMode, setIsDarkMode] = useState(false);

    useEffect(() => {
        const storedUsername = localStorage.getItem('username');
        if (storedUsername) {
            setUsername(storedUsername);
        }
        const storedTheme = localStorage.getItem('isDarkMode') === 'true';
        setIsDarkMode(storedTheme);
    }, []);

    const handleLogin = (username) => {
        setUsername(username);
    };

    const handleLogout = () => {
        setUsername('');
        localStorage.removeItem('username');
    };

    const toggleTheme = () => {
        const newTheme = !isDarkMode;
        setIsDarkMode(newTheme);
        localStorage.setItem('isDarkMode', newTheme);
    };

    return (
        <Router>
            <div className={isDarkMode ? 'dark-mode' : 'light-mode'}>
                <Header
                    username={username}
                    onLogout={handleLogout}
                    toggleTheme={toggleTheme}
                    isDarkMode={isDarkMode}
                />
                <Routes>
                    <Route path="/login" element={username ? <Navigate to="/" /> : <LoginPage onLogin={handleLogin} />} />
                    <Route path="/signup" element={<SignUpPage />} />
                    <Route path="/" element={username ? <HomePage /> : <Navigate to="/login" />} />
                    <Route path="/profile" element={username ? <ProfilePage username={username} /> : <Navigate to="/login" />} />
                    <Route path="/about" element={<AboutPage />} />
                    <Route path="/error" element={<ErrorPage />} />
                    <Route path="*" element={<Navigate to="/error" />} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;
