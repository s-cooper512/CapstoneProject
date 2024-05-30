// src/components/SignUpPage.jsx

import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const SignUpPage = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        firstName: '',
        lastName: '',
        dateOfBirth: '',
    });
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const existingUser = localStorage.getItem('userProfile');
        if (existingUser && JSON.parse(existingUser).username === formData.username) {
            setMessage('Username already exists');
            return;
        }
        localStorage.setItem('userProfile', JSON.stringify(formData));
        localStorage.setItem('username', formData.username);
        navigate('/');
    };

    return (
        <div className="signup-page">
            <h2>Sign Up</h2>
            {message && <p>{message}</p>}
            <form onSubmit={handleSubmit}>
                <label>
                    Username:
                    <input type="text" name="username" value={formData.username} onChange={handleChange} required />
                </label>
                <label>
                    Password:
                    <input type="password" name="password" value={formData.password} onChange={handleChange} required />
                </label>
                <label>
                    First Name:
                    <input type="text" name="firstName" value={formData.firstName} onChange={handleChange} required />
                </label>
                <label>
                    Last Name:
                    <input type="text" name="lastName" value={formData.lastName} onChange={handleChange} required />
                </label>
                <label>
                    Date of Birth:
                    <input type="date" name="dateOfBirth" value={formData.dateOfBirth} onChange={handleChange} required />
                </label>
                <button type="submit">Sign Up</button>
            </form>
        </div>
    );
};

export default SignUpPage;
