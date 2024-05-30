// src/components/ProfilePage.jsx

import React, { useState, useEffect } from 'react';
import './ProfilePage.css';

const ProfilePage = ({ username, isDarkMode }) => {
    const [profileData, setProfileData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        dateOfBirth: '',
    });

    const [dosageProfile, setDosageProfile] = useState({
        insulinToCarbRatio: '',
        targetBloodGlucose: '',
        insulinSensitivity: ''
    });

    useEffect(() => {
        const storedProfile = JSON.parse(localStorage.getItem('profileData'));
        const storedDosageProfile = JSON.parse(localStorage.getItem('dosageProfile'));
        if (storedProfile) {
            setProfileData(storedProfile);
        }
        if (storedDosageProfile) {
            setDosageProfile(storedDosageProfile);
        }
    }, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setProfileData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleDosageInputChange = (e) => {
        const { name, value } = e.target;
        setDosageProfile((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleSaveProfile = () => {
        localStorage.setItem('profileData', JSON.stringify(profileData));
        localStorage.setItem('dosageProfile', JSON.stringify(dosageProfile));
        alert('Profile saved successfully!');
    };

    return (
        <div className={`profile-page ${isDarkMode ? 'dark-mode' : 'light-mode'}`}>
            <div className="profile-box account-info">
                <h2>Account Information</h2>
                <label>Username: {username}</label>
                <label>First Name:</label>
                <input
                    type="text"
                    name="firstName"
                    value={profileData.firstName}
                    onChange={handleInputChange}
                />
                <label>Last Name:</label>
                <input
                    type="text"
                    name="lastName"
                    value={profileData.lastName}
                    onChange={handleInputChange}
                />
                <label>Email:</label>
                <input
                    type="email"
                    name="email"
                    value={profileData.email}
                    onChange={handleInputChange}
                />
                <label>Date of Birth:</label>
                <input
                    type="date"
                    name="dateOfBirth"
                    value={profileData.dateOfBirth}
                    onChange={handleInputChange}
                />
            </div>
            <div className="profile-box dosage-profile">
                <h2>Dosage Profile</h2>
                <label>Insulin-to-Carb Ratio (Carbs per Unit of Insulin):</label>
                <input
                    type="text"
                    name="insulinToCarbRatio"
                    value={dosageProfile.insulinToCarbRatio}
                    onChange={handleDosageInputChange}
                />
                <label>Target Blood Glucose:</label>
                <input
                    type="text"
                    name="targetBloodGlucose"
                    value={dosageProfile.targetBloodGlucose}
                    onChange={handleDosageInputChange}
                />
                <label>Insulin-to-Blood-Glucose Sensitivity Factor <br></br>(Blood Glucose Points to Unit of Insulin):</label>
                <input
                    type="text"
                    name="insulinSensitivity"
                    value={dosageProfile.insulinSensitivity}
                    onChange={handleDosageInputChange}
                />
            </div>
            <button onClick={handleSaveProfile}>Save Profile</button>
        </div>
    );
};

export default ProfilePage;
