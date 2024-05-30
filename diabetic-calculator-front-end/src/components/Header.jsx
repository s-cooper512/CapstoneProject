import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

const Header = ({ username, onLogout, toggleTheme, isDarkMode }) => {
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem('username');
        onLogout();
        navigate('/login');
    };

    return (
        <header className="header">
            <Link to="/" className="app-name">MyApp</Link>
            {username && (
                <div className="user-info">
                    <div className="user-initial">{username.charAt(0)}</div>
                    <span>{username}</span>
                </div>
            )}
            <nav>
                <Link to="/">Home</Link>
                <Link to="/profile">Profile</Link>
                <Link to="/about">About</Link>
                <Link to="/error">Error</Link>
                <button onClick={handleLogout}>Log out</button>
                <button onClick={toggleTheme}>
                    {isDarkMode ? 'Switch to Light Mode' : 'Switch to Dark Mode'}
                </button>
            </nav>
        </header>
    );
};

export default Header;
