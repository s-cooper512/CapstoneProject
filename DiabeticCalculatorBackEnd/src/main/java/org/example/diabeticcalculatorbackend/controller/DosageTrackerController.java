package org.example.diabeticcalculatorbackend.controller;

import org.example.diabeticcalculatorbackend.model.DosageTracker;
import org.example.diabeticcalculatorbackend.service.DosageTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DosageTracker")
public class DosageTrackerController {
    @Autowired
    DosageTrackerService dosageTrackerService;

    @GetMapping
    public List<DosageTracker> getAllDosageTrackers () {
        return dosageTrackerService.getAllDosageTrackers();
    }

    @GetMapping("/{ID}")
    public DosageTracker getDosageTrackerByID (@PathVariable long ID) {
        return dosageTrackerService.getDosageTrackerByID(ID);
    }

    @PostMapping("/{userID}/{foodIDs}")
    public ResponseEntity<DosageTracker> createDosageTracker (@RequestBody DosageTracker dosageTracker, @PathVariable List<Long> foodIDs, @PathVariable long userID) {
        dosageTrackerService.createDosageTracker(dosageTracker, foodIDs, userID);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<?> deleteDosageTracker (@PathVariable long ID) {
        dosageTrackerService.deleteDosageTrackerByID(ID);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
