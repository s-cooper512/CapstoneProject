package org.example.diabeticcalculatorbackend.service;

import org.example.diabeticcalculatorbackend.model.User;
import org.example.diabeticcalculatorbackend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserDosageProfileService profileService;
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByID(long userID) {
        if (userRepository.findById(userID).isPresent()) {
            return userRepository.findById(userID).get();
        }

        return null;
    }

    public void createUser (User createThisUser, long dosageProfileID) {
        createThisUser.setDosageProfile(profileService.getUserDosageProfileByID(dosageProfileID));

        userRepository.save(createThisUser);
    }

    public void deleteUserByID (long deleteThisUser) {
        userRepository.deleteById(deleteThisUser);
    }

    public void updateUserByID (long userID, long dosageProfileID, User updatedUser) {
        User oldUser = getUserByID(userID);

        oldUser.setFirstName(updatedUser.getFirstName());
        oldUser.setLastName(updatedUser.getLastName());
        oldUser.setEmail(updatedUser.getEmail());
        oldUser.setPassword(updatedUser.getPassword());
        oldUser.setDateOfBirth(updatedUser.getDateOfBirth());
        oldUser.setDosageProfile(profileService.getUserDosageProfileByID(dosageProfileID));
    }
}
