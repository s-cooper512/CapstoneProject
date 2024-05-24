package org.example.diabeticcalculatorbackend.service;

import org.example.diabeticcalculatorbackend.model.DosageTracker;
import org.example.diabeticcalculatorbackend.model.Food;
import org.example.diabeticcalculatorbackend.model.UserDosageProfile;
import org.example.diabeticcalculatorbackend.repository.IDosageTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DosageTrackerService {
    @Autowired
    private IDosageTrackerRepository dosageTrackerRepository;

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    public List<DosageTracker> getAllDosageTrackers() {
        return dosageTrackerRepository.findAll();
    }

    public DosageTracker getDosageTrackerByID(long dosageTrackerID) {
        if (dosageTrackerRepository.findById(dosageTrackerID).isPresent()) {
            dosageTrackerRepository.findById(dosageTrackerID).get().setLastIOBCheck(new Date());
            Date deliveryDate = dosageTrackerRepository.findById(dosageTrackerID).get().getTimeCalculated();
            Date checkDate = dosageTrackerRepository.findById(dosageTrackerID).get().getLastIOBCheck();

            if (checkDate.getDate() == deliveryDate.getDate()) {
                long timeBetween = checkDate.getHours() - deliveryDate.getHours();
                checkDate.compareTo(deliveryDate);
                if (timeBetween < 4) {
                    dosageTrackerRepository.findById(dosageTrackerID).get().setInsulinOnBoard(dosageTrackerRepository.findById(dosageTrackerID).get().getCalculatedTotalDosage() - ((timeBetween * dosageTrackerRepository.findById(dosageTrackerID).get().getCalculatedTotalDosage()) / 4));
                }
            }


            return dosageTrackerRepository.findById(dosageTrackerID).get();
        }

        return null;
    }

    public DosageTracker createDosageTracker (DosageTracker createThisDosageTracker, List<Long> foodIDs, long userID) {
        List<Food> foods = new ArrayList<>();
        foodIDs.forEach(foodID -> {
            foods.add(foodService.getFoodByID(foodID));
        });
        createThisDosageTracker.setFood(foods);
        createThisDosageTracker.setUser(userService.getUserByID(userID));

        UserDosageProfile dosageProfile = userService.getUserByID(userID).getDosageProfile();

        createThisDosageTracker.getFood().forEach(food -> {
            createThisDosageTracker.setCalculatedFoodDosage(createThisDosageTracker.getCalculatedFoodDosage() + (food.getCarbsPerServing() * dosageProfile.getCarbsPerUnitOfInsulin()));
        });

        createThisDosageTracker.setCalculatedBloodGlucoseDosage((createThisDosageTracker.getCurrentBloodGlucose() - dosageProfile.getTargetBloodGlucose()) / dosageProfile.getBloodGlucosePointsPerUnitOfInsulin());

        createThisDosageTracker.setCalculatedTotalDosage(createThisDosageTracker.getCalculatedFoodDosage() + createThisDosageTracker.getCalculatedBloodGlucoseDosage());

        createThisDosageTracker.setTimeCalculated(new Date());

        return dosageTrackerRepository.save(createThisDosageTracker);
    }

    public void deleteDosageTrackerByID (long deleteThisDosageTracker) {
        dosageTrackerRepository.deleteById(deleteThisDosageTracker);
    }
}
