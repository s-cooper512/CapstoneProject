package org.example.diabeticcalculatorbackend.test;

import org.example.diabeticcalculatorbackend.model.User;
import org.example.diabeticcalculatorbackend.model.UserDosageProfile;
import org.example.diabeticcalculatorbackend.repository.IUserRepository;
import org.example.diabeticcalculatorbackend.service.UserDosageProfileService;
import org.example.diabeticcalculatorbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserDosageProfileService profileService;

    @InjectMocks
    private UserService userService;

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

        // Mock repository method
        when(userRepository.findAll()).thenReturn(users);

        // Call service method
        List<User> retrievedUsers = userService.getAllUsers();

        // Verify that the result is not null and has the correct size
        assertNotNull(retrievedUsers);
        assertEquals(2, retrievedUsers.size());
    }

    @Test
    void testGetUserByID() {
        // Create a sample user
        User user = new User();
        user.setID(1L);

        // Mock repository method
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Call service method
        User retrievedUser = userService.getUserByID(1L);

        // Verify that the result is not null and has the correct ID
        assertNotNull(retrievedUser);
        assertEquals(1L, retrievedUser.getID());
    }

    @Test
    void testGetUserByID_UserNotFound() {
        // Mock repository method
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Call service method
        User retrievedUser = userService.getUserByID(1L);

        // Verify that the result is null
        assertNull(retrievedUser);
    }

    @Test
    void testCreateUser() {
        // Create a sample user and dosage profile
        User user = new User();
        UserDosageProfile dosageProfile = new UserDosageProfile();
        dosageProfile.setID(1L);

        // Mock service method
        when(profileService.getUserDosageProfileByID(1L)).thenReturn(dosageProfile);

        // Call service method
        userService.createUser(user, 1L);

        // Verify that the user's dosage profile was set correctly
        assertEquals(1L, user.getDosageProfile().getID());

        // Verify that the repository's save method was called
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUserByID() {
        // Call service method
        userService.deleteUserByID(1L);

        // Verify that the repository's deleteById method was called
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateUserByID() {
        // Create sample users and dosage profile
        User oldUser = new User();
        oldUser.setID(1L);

        User updatedUser = new User();
        updatedUser.setFirstName("John");
        updatedUser.setLastName("Doe");
        updatedUser.setEmail("john.doe@example.com");
        updatedUser.setPassword("password123");
        updatedUser.setDateOfBirth(new Date());

        UserDosageProfile dosageProfile = new UserDosageProfile();
        dosageProfile.setID(1L);

        // Mock service and repository methods
        when(userRepository.findById(1L)).thenReturn(Optional.of(oldUser));
        when(profileService.getUserDosageProfileByID(1L)).thenReturn(dosageProfile);

        // Call service method
        userService.updateUserByID(1L, 1L, updatedUser);

        // Verify that the old user's properties were updated correctly
        assertEquals("John", oldUser.getFirstName());
        assertEquals("Doe", oldUser.getLastName());
        assertEquals("john.doe@example.com", oldUser.getEmail());
        assertEquals("password123", oldUser.getPassword());
        assertEquals(updatedUser.getDateOfBirth(), oldUser.getDateOfBirth());
        assertEquals(dosageProfile, oldUser.getDosageProfile());
    }
}

