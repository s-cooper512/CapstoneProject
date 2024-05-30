import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Header from './components/Header';
import LoginPage from './components/LoginPage';
import SignUpPage from './components/SignUpPage';
import HomePage from './components/HomePage';
import ProfilePage from './components/ProfilePage';
import AboutPage from './components/AboutPage';
import ErrorPage from './components/ErrorPage';

const App = () => {
    const [username, setUsername] = useState('');
    const [isDarkMode, setIsDarkMode] = useState(false);

    useEffect(() => {
        const storedUsername = localStorage.getItem('username');
        if (storedUsername) {
            setUsername(storedUsername);
        }
    }, []);

    const handleLogin = (username) => {
        setUsername(username);
    };

    const handleLogout = () => {
        setUsername('');
    };

    const toggleTheme = () => {
        setIsDarkMode(!isDarkMode);
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
                    <Route path="/" element={<HomePage />} />
                    <Route path="/profile" element={<ProfilePage />} />
                    <Route path="/about" element={<AboutPage />} />
                    <Route path="/error" element={<ErrorPage />} />
                    <Route path="*" element={<Navigate to="/error" />} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;
