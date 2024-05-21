package org.example.diabeticcalculatorbackend.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
public class DosageTracker {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @ManyToOne
    private User user;
    @OneToMany
    private List<Food> food;
    private long servingsConsumed;

    private long currentBloodGlucose;

    private long calculatedFoodDosage;

    private long calculatedBloodGlucoseDosage;
    private long calculatedTotalDosage;
    private Date timeCalculated;

    //Constructors


    //Setters

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public void setServingsConsumed(long servingsConsumed) {
        this.servingsConsumed = servingsConsumed;
    }

    public void setCurrentBloodGlucose(long currentBloodGlucose) {
        this.currentBloodGlucose = currentBloodGlucose;
    }

    public void setCalculatedFoodDosage(long calculatedFoodDosage) {
        this.calculatedFoodDosage = calculatedFoodDosage;
    }

    public void setCalculatedBloodGlucoseDosage(long calculatedBloodGlucoseDosage) {
        this.calculatedBloodGlucoseDosage = calculatedBloodGlucoseDosage;
    }

    public void setCalculatedTotalDosage(long calculatedTotalDosage) {
        this.calculatedTotalDosage = calculatedTotalDosage;
    }

    public void setTimeCalculated(Date timeCalculated) {
        this.timeCalculated = timeCalculated;
    }


    //Getters

    public long getID() {
        return ID;
    }

    public User getUser() {
        return user;
    }

    public List<Food> getFood() {
        return food;
    }

    public long getServingsConsumed() {
        return servingsConsumed;
    }

    public long getCurrentBloodGlucose() {
        return currentBloodGlucose;
    }

    public long getCalculatedFoodDosage() {
        return calculatedFoodDosage;
    }

    public long getCalculatedBloodGlucoseDosage() {
        return calculatedBloodGlucoseDosage;
    }

    public long getCalculatedTotalDosage() {
        return calculatedTotalDosage;
    }

    public Date getTimeCalculated() {
        return timeCalculated;
    }
}
