package org.example.diabeticcalculatorbackend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class User {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dateOfBirth;
    @OneToOne
    private UserDosageProfile dosageProfile;

    //Constructors
    public User(long userID, String userFirstName, String userLastName, String userEmail, String userPassword, Date userDOB, UserDosageProfile userDosageProfile) {
        this.setID(userID);
        this.setFirstName(userFirstName);
        this.setLastName(userLastName);
        this.setEmail(userEmail);
        this.setPassword(userPassword);
        this.setDateOfBirth(userDOB);
        this.setDosageProfile(userDosageProfile);
    }

    public User() {

    }

    //Setters
    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDosageProfile(UserDosageProfile dosageProfile) {
        this.dosageProfile = dosageProfile;
    }

    //Getters
    public Long getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public UserDosageProfile getDosageProfile() {
        return dosageProfile;
    }
}
