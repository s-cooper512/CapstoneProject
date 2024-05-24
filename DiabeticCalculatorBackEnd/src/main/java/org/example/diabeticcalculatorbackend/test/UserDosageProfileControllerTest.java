package org.example.diabeticcalculatorbackend.test;

import org.example.diabeticcalculatorbackend.controller.UserDosageProfileController;
import org.example.diabeticcalculatorbackend.model.UserDosageProfile;
import org.example.diabeticcalculatorbackend.service.UserDosageProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserDosageProfileControllerTest {

    @Mock
    private UserDosageProfileService dosageProfileService;

    @InjectMocks
    private UserDosageProfileController dosageProfileController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dosageProfileController).build();
    }

    @Test
    void testGetAllDosageProfiles() throws Exception {
        // Create sample dosage profiles
        List<UserDosageProfile> dosageProfiles = new ArrayList<>();
        dosageProfiles.add(new UserDosageProfile());
        dosageProfiles.add(new UserDosageProfile());

        // Mock service method
        when(dosageProfileService.getAllUserDosageProfiles()).thenReturn(dosageProfiles);

        // Perform GET request
        mockMvc.perform(get("/DosageProfile"))
                .andExpect(status().isOk());

        // Verify service method was called
        verify(dosageProfileService, times(1)).getAllUserDosageProfiles();
    }

    @Test
    void testGetDosageProfileByID() throws Exception {
        // Create a sample dosage profile
        UserDosageProfile dosageProfile = new UserDosageProfile();
        dosageProfile.setID(1L);

        // Mock service method
        when(dosageProfileService.getUserDosageProfileByID(1L)).thenReturn(dosageProfile);

        // Perform GET request
        mockMvc.perform(get("/DosageProfile/1"))
                .andExpect(status().isOk());

        // Verify service method was called
        verify(dosageProfileService, times(1)).getUserDosageProfileByID(1L);
    }

    @Test
    void testCreateDosageProfile() throws Exception {
        // Create a sample dosage profile
        UserDosageProfile dosageProfile = new UserDosageProfile();

        // Perform POST request
        mockMvc.perform(post("/DosageProfile")
                        .contentType("application/json")
                        .content("{\"carbsPerUnitOfInsulin\":10,\"targetBloodGlucose\":100,\"BloodGlucosePointsPerUnitOfInsulin\":30}"))
                .andExpect(status().isCreated());

        // Verify service method was called
        verify(dosageProfileService, times(1)).createUserDosageProfile(any(UserDosageProfile.class));
    }

    @Test
    void testDeleteDosageProfileByID() throws Exception {
        // Perform DELETE request
        mockMvc.perform(delete("/DosageProfile/1"))
                .andExpect(status().isNoContent());

        // Verify service method was called
        verify(dosageProfileService, times(1)).deleteUserDosageProfileByID(1L);
    }

    @Test
    void testUpdateDosageProfileByID() throws Exception {
        // Create a sample dosage profile
        UserDosageProfile dosageProfile = new UserDosageProfile();
        dosageProfile.setID(1L);

        // Perform PUT request
        mockMvc.perform(put("/DosageProfile/1")
                        .contentType("application/json")
                        .content("{\"carbsPerUnitOfInsulin\":15,\"targetBloodGlucose\":110,\"BloodGlucosePointsPerUnitOfInsulin\":35}"))
                .andExpect(status().isOk());

        // Verify service method was called
        verify(dosageProfileService, times(1)).updateUserDosageProfileByID(eq(1L), any(UserDosageProfile.class));
    }
}

