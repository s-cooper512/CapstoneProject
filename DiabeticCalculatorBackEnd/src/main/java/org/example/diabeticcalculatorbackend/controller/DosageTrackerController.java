package org.example.diabeticcalculatorbackend.controller;

import org.example.diabeticcalculatorbackend.model.DosageTracker;
import org.example.diabeticcalculatorbackend.service.DosageTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createDosageTracker (@RequestBody DosageTracker dosageTracker, @PathVariable List<Long> foodIDs, @PathVariable long userID) {
        dosageTrackerService.createDosageTracker(dosageTracker, foodIDs, userID);
    }

    @DeleteMapping("/{ID}")
    public void deleteDosageTracker (@PathVariable long ID) {
        dosageTrackerService.deleteDosageTrackerByID(ID);
    }
}
