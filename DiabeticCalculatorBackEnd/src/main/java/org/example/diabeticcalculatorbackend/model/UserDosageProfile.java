package org.example.diabeticcalculatorbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserDosageProfile {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private long carbsPerUnitOfInsulin;
    private long targetBloodGlucose;
    private long BloodGlucosePointsPerUnitOfInsulin;

    //Constructors
    public UserDosageProfile() {

    }

    public UserDosageProfile(long dosageProfileID, long dosageProfileICRation, long dosageProfileTargetBG, long dosageProfileInsulinSensitivity) {
        this.setID(dosageProfileID);
        this.setCarbsPerUnitOfInsulin(dosageProfileICRation);
        this.setTargetBloodGlucose(dosageProfileTargetBG);
        this.setBloodGlucosePointsPerUnitOfInsulin(dosageProfileInsulinSensitivity);
    }

    //Setters

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setCarbsPerUnitOfInsulin(long carbsPerUnitOfInsulin) {
        this.carbsPerUnitOfInsulin = carbsPerUnitOfInsulin;
    }

    public void setTargetBloodGlucose(long targetBloodGlucose) {
        this.targetBloodGlucose = targetBloodGlucose;
    }

    public void setBloodGlucosePointsPerUnitOfInsulin(long bloodGlucosePointsPerUnitOfInsulin) {
        BloodGlucosePointsPerUnitOfInsulin = bloodGlucosePointsPerUnitOfInsulin;
    }


    //Getters

    public long getID() {
        return ID;
    }

    public long getCarbsPerUnitOfInsulin() {
        return carbsPerUnitOfInsulin;
    }

    public long getTargetBloodGlucose() {
        return targetBloodGlucose;
    }

    public long getBloodGlucosePointsPerUnitOfInsulin() {
        return BloodGlucosePointsPerUnitOfInsulin;
    }
}
