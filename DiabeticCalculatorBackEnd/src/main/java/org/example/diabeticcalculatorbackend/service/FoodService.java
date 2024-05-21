package org.example.diabeticcalculatorbackend.service;

import org.example.diabeticcalculatorbackend.model.Food;
import org.example.diabeticcalculatorbackend.repository.IFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    @Autowired
    IFoodRepository foodRepository;

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Food getFoodByID(long foodID) {
        if (foodRepository.findById(foodID).isPresent()) {
            return foodRepository.findById(foodID).get();
        }

        return null;
    }

    public void createFood (Food createThisFood) {
        foodRepository.save(createThisFood);
    }

    public void deleteFoodByID (long deleteThisFood) {
        foodRepository.deleteById(deleteThisFood);
    }

    public void updateFoodByID (long foodID, Food updatedFood) {
        Food oldFood = getFoodByID(foodID);
        oldFood.setFoodName(updatedFood.getFoodName());
        oldFood.setCarbsPerServing(updatedFood.getCarbsPerServing());
        oldFood.setServingSize(updatedFood.getServingSize());

        foodRepository.save(oldFood);
    }
}
