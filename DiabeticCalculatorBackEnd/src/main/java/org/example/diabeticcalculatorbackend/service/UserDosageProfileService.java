package org.example.diabeticcalculatorbackend.service;

import org.example.diabeticcalculatorbackend.model.UserDosageProfile;
import org.example.diabeticcalculatorbackend.repository.IUserDosageProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDosageProfileService {
    @Autowired
    IUserDosageProfileRepository userDosageProfileRepository;
    public List<UserDosageProfile> getAllUserDosageProfiles() {
        return userDosageProfileRepository.findAll();
    }

    public UserDosageProfile getUserDosageProfileByID(long userDosageProfileID) {
        if (userDosageProfileRepository.findById(userDosageProfileID).isPresent()) {
            return userDosageProfileRepository.findById(userDosageProfileID).get();
        }

        return null;
    }

    public void createUserDosageProfile (UserDosageProfile createThisUserDosageProfile) {
        userDosageProfileRepository.save(createThisUserDosageProfile);
    }

    public void deleteUserDosageProfileByID (long deleteThisUserDosageProfile) {
        userDosageProfileRepository.deleteById(deleteThisUserDosageProfile);
    }

    public void updateUserDosageProfileByID (long dosageProfileID, UserDosageProfile dosageProfile) {
        UserDosageProfile oldProfile = getUserDosageProfileByID(dosageProfileID);

        oldProfile.setCarbsPerUnitOfInsulin(dosageProfile.getCarbsPerUnitOfInsulin());
        oldProfile.setBloodGlucosePointsPerUnitOfInsulin(dosageProfile.getBloodGlucosePointsPerUnitOfInsulin());
        oldProfile.setTargetBloodGlucose(dosageProfile.getTargetBloodGlucose());

        userDosageProfileRepository.save(oldProfile);
    }
}
