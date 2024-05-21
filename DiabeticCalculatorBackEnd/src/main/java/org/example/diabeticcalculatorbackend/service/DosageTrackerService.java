package org.example.diabeticcalculatorbackend.service;

import org.example.diabeticcalculatorbackend.model.DosageTracker;
import org.example.diabeticcalculatorbackend.model.Food;
import org.example.diabeticcalculatorbackend.repository.IDosageTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DosageTrackerService {
    @Autowired
    private IDosageTrackerRepository dosageTrackerRepository;

    @Autowired
    private FoodService foodService;

    public List<DosageTracker> getAllDosageTrackers() {
        return dosageTrackerRepository.findAll();
    }

    public DosageTracker getDosageTrackerByID(long dosageTrackerID) {
        if (dosageTrackerRepository.findById(dosageTrackerID).isPresent()) {
            return dosageTrackerRepository.findById(dosageTrackerID).get();
        }

        return null;
    }

    public void createDosageTracker (DosageTracker createThisDosageTracker, List<Long> foodIDs) {
        List<Food> foods = new ArrayList<>();
        foodIDs.forEach(foodID -> {
            foods.add(foodService.getFoodByID(foodID));
        });
        createThisDosageTracker.setFood(foods);

        dosageTrackerRepository.save(createThisDosageTracker);
    }

    public void deleteDosageTrackerByID (long deleteThisDosageTracker) {
        dosageTrackerRepository.deleteById(deleteThisDosageTracker);
    }
}
