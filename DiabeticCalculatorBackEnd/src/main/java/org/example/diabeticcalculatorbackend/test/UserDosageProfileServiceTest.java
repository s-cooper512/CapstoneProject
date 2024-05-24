package org.example.diabeticcalculatorbackend.test;

import org.example.diabeticcalculatorbackend.model.UserDosageProfile;
import org.example.diabeticcalculatorbackend.repository.IUserDosageProfileRepository;
import org.example.diabeticcalculatorbackend.service.UserDosageProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class UserDosageProfileServiceTest {

    @Mock
    private IUserDosageProfileRepository userDosageProfileRepository;

    @InjectMocks
    private UserDosageProfileService userDosageProfileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUserDosageProfiles() {
        UserDosageProfile profile1 = new UserDosageProfile();
        UserDosageProfile profile2 = new UserDosageProfile();
        List<UserDosageProfile> profiles = Arrays.asList(profile1, profile2);

        when(userDosageProfileRepository.findAll()).thenReturn(profiles);

        List<UserDosageProfile> result = userDosageProfileService.getAllUserDosageProfiles();

        assertEquals(2, result.size());
        verify(userDosageProfileRepository, times(1)).findAll();
    }

    @Test
    void testGetUserDosageProfileByID_Found() {
        UserDosageProfile profile = new UserDosageProfile();
        profile.setID(1L);

        when(userDosageProfileRepository.findById(1L)).thenReturn(Optional.of(profile));

        UserDosageProfile result = userDosageProfileService.getUserDosageProfileByID(1L);

        assertEquals(1L, result.getID());
    }

    @Test
    void testGetUserDosageProfileByID_NotFound() {
        when(userDosageProfileRepository.findById(1L)).thenReturn(Optional.empty());

        UserDosageProfile result = userDosageProfileService.getUserDosageProfileByID(1L);

        assertNull(result);
        verify(userDosageProfileRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateUserDosageProfile() {
        UserDosageProfile profile = new UserDosageProfile();

        userDosageProfileService.createUserDosageProfile(profile);

        verify(userDosageProfileRepository, times(1)).save(profile);
    }

    @Test
    void testDeleteUserDosageProfileByID() {
        userDosageProfileService.deleteUserDosageProfileByID(1L);

        verify(userDosageProfileRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateUserDosageProfileByID() {
        UserDosageProfile existingProfile = new UserDosageProfile();
        existingProfile.setID(1L);
        existingProfile.setCarbsPerUnitOfInsulin(10);
        existingProfile.setBloodGlucosePointsPerUnitOfInsulin(30);
        existingProfile.setTargetBloodGlucose(100);

        UserDosageProfile updatedProfile = new UserDosageProfile();
        updatedProfile.setCarbsPerUnitOfInsulin(15);
        updatedProfile.setBloodGlucosePointsPerUnitOfInsulin(35);
        updatedProfile.setTargetBloodGlucose(110);

        when(userDosageProfileRepository.findById(1L)).thenReturn(Optional.of(existingProfile));

        userDosageProfileService.updateUserDosageProfileByID(1L, updatedProfile);

        assertEquals(15, existingProfile.getCarbsPerUnitOfInsulin());
        assertEquals(35, existingProfile.getBloodGlucosePointsPerUnitOfInsulin());
        assertEquals(110, existingProfile.getTargetBloodGlucose());
        verify(userDosageProfileRepository, times(1)).save(existingProfile);
    }
}

