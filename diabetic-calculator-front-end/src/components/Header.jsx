// src/components/Header.jsx

import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

const Header = ({ username, onLogout, toggleTheme, isDarkMode }) => {
    const navigate = useNavigate();

    const handleLogout = () => {
        onLogout();
        navigate('/login');
    };

    const handleProfileClick = () => {
        navigate('/profile');
    };

    return (
        <header className="header">
            <Link to="/" className="app-name">Diabetic Calculator</Link>
            <div className="nav-links">
                <Link to="/about">About</Link>
            </div>
            {username && (
                <div className="user-info">
                    <div className="user-initial" onClick={handleProfileClick}>{username.charAt(0).toUpperCase()}</div>
                    <div className="user-details">
                        <div className="username">{username}</div>
                        <div className="toggle-label">
                            Dark Mode
                            <label className="toggle-switch">
                                <input type="checkbox" checked={isDarkMode} onChange={(e) => {
                                    e.stopPropagation();
                                    toggleTheme();
                                }} />
                                <span className="slider"></span>
                            </label>
                        </div>
                        <button onClick={handleLogout}>Log Out</button>
                    </div>
                </div>
            )}
        </header>
    );
};

export default Header;
