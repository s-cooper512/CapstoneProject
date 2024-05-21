package org.example.diabeticcalculatorbackend.controller;

import org.example.diabeticcalculatorbackend.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.diabeticcalculatorbackend.model.Food;

import java.util.List;

@RestController
@RequestMapping("/Food")
public class FoodController {
    @Autowired
    FoodService foodService;

    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{foodID}")
    public Food getFoodByID(@PathVariable long foodID) {
        return foodService.getFoodByID(foodID);
    }

    @PostMapping
    public void createFood (@RequestBody Food foodToAdd) {
        foodService.createFood(foodToAdd);
    }

    @DeleteMapping("/{ID}")
    public void deleteFoodByID (@PathVariable long ID) {
        foodService.deleteFoodByID(ID);
    }

    @PutMapping("/{ID}")
    public void updateFoodByID (@PathVariable long ID, @RequestBody Food food) {
        foodService.updateFoodByID(ID, food);
    }
}
