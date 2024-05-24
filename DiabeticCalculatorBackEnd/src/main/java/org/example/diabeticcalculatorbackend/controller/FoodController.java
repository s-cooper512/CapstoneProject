package org.example.diabeticcalculatorbackend.controller;

import org.example.diabeticcalculatorbackend.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Food> createFood (@RequestBody Food foodToAdd) {
        foodService.createFood(foodToAdd);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<?> deleteFoodByID (@PathVariable long ID) {
        foodService.deleteFoodByID(ID);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{ID}")
    public ResponseEntity<Food> updateFoodByID (@PathVariable long ID, @RequestBody Food food) {
        foodService.updateFoodByID(ID, food);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
