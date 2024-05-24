package org.example.diabeticcalculatorbackend.test;

import org.example.diabeticcalculatorbackend.model.DosageTracker;
import org.example.diabeticcalculatorbackend.model.User;
import org.example.diabeticcalculatorbackend.model.UserDosageProfile;
import org.example.diabeticcalculatorbackend.repository.IDosageTrackerRepository;
import org.example.diabeticcalculatorbackend.service.DosageTrackerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DosageTrackerServiceTest {
    @Autowired
    private DosageTrackerService dosageTrackerService;

    @MockBean
    private IDosageTrackerRepository dosageTrackerRepository;

    final DosageTracker dosageTracker = new DosageTracker(1, 102, new Date());
    final User exampleUser = new User(1, "John", "Doe", "JD@example.com", "Password", new Date(), null);

    final UserDosageProfile dosageProfile = new UserDosageProfile(1, 1, 1, 1);

    @Test
    public void testGetAllDosageTrackers () {
        List<DosageTracker> trackers = new ArrayList<>();
        trackers.add(new DosageTracker(1, 100, new Date()));
        trackers.add(new DosageTracker(2, 200, new Date()));

        when(dosageTrackerService.getAllDosageTrackers()).thenReturn(trackers);

        List<DosageTracker> result = dosageTrackerService.getAllDosageTrackers();

        assertNotNull(result);
        assertEquals(trackers.size(), result.size());
        assertEquals(trackers.get(0).getID(), result.get(0).getID());
        assertEquals(trackers.get(1).getID(), result.get(1).getID());
        assertEquals(trackers.get(0).getCurrentBloodGlucose(), result.get(0).getCurrentBloodGlucose());
        assertEquals(trackers.get(1).getCurrentBloodGlucose(), result.get(1).getCurrentBloodGlucose());
    }

    @Test
    public void testGetDosageTrackerByID () {
        given(dosageTrackerRepository.findById(dosageTracker.getID())).willReturn(Optional.of(dosageTracker));
        DosageTracker example = dosageTrackerService.getDosageTrackerByID(dosageTracker.getID());

        assertNotNull(example);
        assertEquals(example, dosageTracker);
    }

    @Test
    public void testCreateDosageTracker() {
        dosageTracker.setUser(exampleUser);
        List<Long> foodIDs = new ArrayList<>();
        foodIDs.add(1L);
        exampleUser.setDosageProfile(dosageProfile);
        given(dosageTrackerRepository.save(dosageTracker)).willReturn(dosageTracker);

        DosageTracker result = dosageTrackerRepository.save(dosageTracker);

        assertNotNull(result);
        assertEquals(dosageTracker.getCurrentBloodGlucose(), result.getCurrentBloodGlucose());
    }

    @Test
    public void deleteDosageTracker() {
        dosageTrackerService.deleteDosageTrackerByID(dosageTracker.getID());

        verify(dosageTrackerRepository, times(1)).deleteById(dosageTracker.getID());
    }
}
