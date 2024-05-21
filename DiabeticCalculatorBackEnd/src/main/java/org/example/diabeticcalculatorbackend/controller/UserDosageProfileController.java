package org.example.diabeticcalculatorbackend.controller;

import org.example.diabeticcalculatorbackend.model.UserDosageProfile;
import org.example.diabeticcalculatorbackend.service.UserDosageProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DosageProfile")
public class UserDosageProfileController {
    @Autowired
    UserDosageProfileService dosageProfileService;

    @GetMapping
    public List<UserDosageProfile> getAllDosageProfiles() {
        return dosageProfileService.getAllUserDosageProfiles();
    }

    @GetMapping("/{ID}")
    public UserDosageProfile getDosageProfileByID (@PathVariable long ID) {
        return dosageProfileService.getUserDosageProfileByID(ID);
    }

    @PostMapping
    public void createDosageProfile (@RequestBody UserDosageProfile dosageProfile) {
        dosageProfileService.createUserDosageProfile(dosageProfile);
    }

    @DeleteMapping("/{ID}")
    public void deleteDosageProfileByID (@PathVariable long ID) {
        dosageProfileService.deleteUserDosageProfileByID(ID);
    }

    @PutMapping("/{ID}")
    public void updateDosageProfileByID (@PathVariable long ID, @RequestBody UserDosageProfile dosageProfile) {
        dosageProfileService.updateUserDosageProfileByID(ID, dosageProfile);
    }
}
