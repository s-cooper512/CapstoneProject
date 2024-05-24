package org.example.diabeticcalculatorbackend.test;
import org.example.diabeticcalculatorbackend.controller.UserController;
import org.example.diabeticcalculatorbackend.model.User;
import org.example.diabeticcalculatorbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Create sample users
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        // Mock service method
        when(userService.getAllUsers()).thenReturn(users);

        // Call controller method
        List<User> retrievedUsers = userController.getAllUsers();

        // Verify that the result is not null and has the correct size
        assertNotNull(retrievedUsers);
        assertEquals(2, retrievedUsers.size());
    }

    @Test
    void testGetUserByID() {
        // Create a sample user
        User user = new User();
        user.setID(1L);

        // Mock service method
        when(userService.getUserByID(1L)).thenReturn(user);

        // Call controller method
        User retrievedUser = userController.getUserByID(1L);

        // Verify that the result is not null and has the correct ID
        assertNotNull(retrievedUser);
        assertEquals(1L, retrievedUser.getID());
    }

    @Test
    void testCreateUser() {
        // Create a sample user
        User user = new User();

        // Call controller method
        ResponseEntity<User> response = userController.createUser(1L, user);

        // Verify that the service's createUser method was called
        verify(userService, times(1)).createUser(user, 1L);

        // Verify that the response status is CREATED
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testDeleteUserByID() {
        // Call controller method
        ResponseEntity<?> response = userController.deleteUserByID(1L);

        // Verify that the service's deleteUserByID method was called
        verify(userService, times(1)).deleteUserByID(1L);

        // Verify that the response status is NO_CONTENT
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testUpdateUserByID() {
        // Create a sample user
        User user = new User();

        // Call controller method
        ResponseEntity<User> response = userController.updateUserByID(1L, 1L, user);

        // Verify that the service's updateUserByID method was called
        verify(userService, times(1)).updateUserByID(1L, 1L, user);

        // Verify that the response status is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

