package org.example.diabeticcalculatorbackend.test;

import org.example.diabeticcalculatorbackend.controller.DosageTrackerController;
import org.example.diabeticcalculatorbackend.model.DosageTracker;
import org.example.diabeticcalculatorbackend.service.DosageTrackerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DosageTrackerControllerTest {
    @Autowired
    DosageTrackerController dosageTrackerController;

    @MockBean
    DosageTrackerService dosageTrackerService;

    final DosageTracker dosageTracker = new DosageTracker(1, 102, new Date());

    @Test
    public void testGetAllDosageTrackers() {
        List<DosageTracker> trackers = new ArrayList<>();
        trackers.add(new DosageTracker(1, 100, new Date()));
        trackers.add(new DosageTracker(2, 200, new Date()));

        when(dosageTrackerService.getAllDosageTrackers()).thenReturn(trackers);

        List<DosageTracker> result = dosageTrackerController.getAllDosageTrackers();

        assertNotNull(result);
        assertEquals(trackers.size(), result.size());
        assertEquals(trackers.get(0).getID(), result.get(0).getID());
        assertEquals(trackers.get(1).getID(), result.get(1).getID());
        assertEquals(trackers.get(0).getCurrentBloodGlucose(), result.get(0).getCurrentBloodGlucose());
        assertEquals(trackers.get(1).getCurrentBloodGlucose(), result.get(1).getCurrentBloodGlucose());
    }

    @Test
    public void testGetDosageTrackerByID() {
        given(dosageTrackerService.getDosageTrackerByID(dosageTracker.getID())).willReturn(dosageTracker);
        DosageTracker example = dosageTrackerController.getDosageTrackerByID(dosageTracker.getID());

        assertNotNull(example);
        assertEquals(example.getCurrentBloodGlucose(), dosageTracker.getCurrentBloodGlucose());
    }

    @Test
    public void testCreateDosageTracker() {
        ResponseEntity<DosageTracker> result = dosageTrackerController.createDosageTracker(dosageTracker, null, 1);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void testDeleteDosageTracker() {
        ResponseEntity<?> result = dosageTrackerController.deleteDosageTracker(dosageTracker.getID());

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}
