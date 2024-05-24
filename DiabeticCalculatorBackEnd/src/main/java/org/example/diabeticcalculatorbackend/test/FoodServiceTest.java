package org.example.diabeticcalculatorbackend.test;

import org.example.diabeticcalculatorbackend.model.Food;
import org.example.diabeticcalculatorbackend.repository.IFoodRepository;
import org.example.diabeticcalculatorbackend.service.FoodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FoodServiceTest {

    @Mock
    private IFoodRepository foodRepository;

    @InjectMocks
    private FoodService foodService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFoods() {
        // Create sample foods
        List<Food> foods = new ArrayList<>();
        foods.add(new Food());
        foods.add(new Food());

        // Mock repository method
        when(foodRepository.findAll()).thenReturn(foods);

        // Call service method
        List<Food> retrievedFoods = foodService.getAllFoods();

        // Verify that the result is not null and has the correct size
        assertNotNull(retrievedFoods);
        assertEquals(2, retrievedFoods.size());
    }

    @Test
    void testGetFoodByID() {
        // Create a sample food
        Food food = new Food();
        food.setID(1L);

        // Mock repository method
        when(foodRepository.findById(1L)).thenReturn(Optional.of(food));

        // Call service method
        Food retrievedFood = foodService.getFoodByID(1L);

        // Verify that the result is not null and has the correct ID
        assertNotNull(retrievedFood);
        assertEquals(1L, retrievedFood.getID());
    }

    @Test
    void testCreateFood() {
        // Create a sample food
        Food food = new Food();

        // Call service method
        foodService.createFood(food);

        // Verify that the repository's save method was called
        verify(foodRepository, times(1)).save(food);
    }

    @Test
    void testDeleteFoodByID() {
        // Call service method
        foodService.deleteFoodByID(1L);

        // Verify that the repository's deleteById method was called
        verify(foodRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateFoodByID() {
        // Create a sample food
        Food food = new Food();
        food.setID(1L);

        // Mock repository method
        when(foodRepository.findById(1L)).thenReturn(Optional.of(food));

        // Call service method
        Food updatedFood = new Food();
        updatedFood.setFoodName("Updated Food Name");
        updatedFood.setServingSize(100);
        updatedFood.setCarbsPerServing(20);
        foodService.updateFoodByID(1L, updatedFood);

        // Verify that the repository's save method was called with the updated food
        verify(foodRepository, times(1)).save(food);
    }
}
