package org.example.diabeticcalculatorbackend.controller;

import org.example.diabeticcalculatorbackend.model.UserDosageProfile;
import org.example.diabeticcalculatorbackend.service.UserDosageProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDosageProfile> createDosageProfile (@RequestBody UserDosageProfile dosageProfile) {
        dosageProfileService.createUserDosageProfile(dosageProfile);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<?> deleteDosageProfileByID (@PathVariable long ID) {
        dosageProfileService.deleteUserDosageProfileByID(ID);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{ID}")
    public ResponseEntity<UserDosageProfile> updateDosageProfileByID (@PathVariable long ID, @RequestBody UserDosageProfile dosageProfile) {
        dosageProfileService.updateUserDosageProfileByID(ID, dosageProfile);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
