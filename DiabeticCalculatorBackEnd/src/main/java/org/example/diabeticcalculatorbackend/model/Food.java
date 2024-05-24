package org.example.diabeticcalculatorbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Food {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String foodName;
    private long servingSize;
    private long carbsPerServing;

    //Constructors
    public Food () {

    }

    public Food(long foodID, String foodName, long foodServingSize, long foodCarbs) {
        this.setID(foodID);
        this.setFoodName(foodName);
        this.setServingSize(foodServingSize);
        this.setCarbsPerServing(foodCarbs);
    }

    //Setters
    public void setID(long ID) {
        this.ID = ID;
    }

    public void setFoodName(String name) {
        this.foodName = name;
    }

    public void setServingSize(long servingSize) {
        this.servingSize = servingSize;
    }

    public void setCarbsPerServing(long carbsPerServing) {
        this.carbsPerServing = carbsPerServing;
    }

    //Getters
    public long getID() {
        return ID;
    }

    public String getFoodName() {
        return foodName;
    }

    public long getServingSize() {
        return servingSize;
    }

    public long getCarbsPerServing() {
        return carbsPerServing;
    }
}
