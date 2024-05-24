package org.example.diabeticcalculatorbackend.test;

import org.example.diabeticcalculatorbackend.controller.FoodController;
import org.example.diabeticcalculatorbackend.model.Food;
import org.example.diabeticcalculatorbackend.service.FoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodControllerTest {
    @Autowired
    FoodController foodController;

    @MockBean
    FoodService foodService;

    final Food testFood = new Food(1, "test", 1, 1);

    @Test
    public void testGetAllFood() {
        List<Food> testFoods = new ArrayList<>();
        testFoods.add(testFood);
        testFoods.add(new Food(2, "test", 2, 2));

        given(foodService.getAllFoods()).willReturn(testFoods);

        List<Food> result = foodController.getAllFoods();
        assertNotNull(result);
        assertEquals(result, testFoods);
    }

    @Test
    public void testGetFoodByID() {
        given(foodService.getFoodByID(testFood.getID())).willReturn(testFood);

        Food result = foodService.getFoodByID(testFood.getID());

        assertNotNull(result);
        assertEquals(testFood, result);
    }

    @Test
    public void testCreateFood() {
        ResponseEntity<Food> result = foodController.createFood(testFood);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void testDeleteFood() {
        ResponseEntity<?> result = foodController.deleteFoodByID(testFood.getID());

        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void testUpdateFood() {
        ResponseEntity<Food> result = foodController.updateFoodByID(testFood.getID(), new Food(2, "test2", 2, 2));

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
