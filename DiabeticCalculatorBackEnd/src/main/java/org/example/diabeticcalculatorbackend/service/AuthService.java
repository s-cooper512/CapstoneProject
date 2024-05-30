//package org.example.diabeticcalculatorbackend.service;
//
//import org.example.diabeticcalculatorbackend.model.User;
//import org.example.diabeticcalculatorbackend.repository.IUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AuthService {
//    @Autowired
//    private IUserRepository userRepository;
//
//    public ResponseEntity<User> authLogin(String userEmail) {
//        List<User> matchingUsers = userRepository.findAll().stream().filter(user -> user.getEmail().equals(userEmail)).toList();
//        if (matchingUsers.size() == 1 && matchingUsers.getFirst().equals(userEmail)) {
//            return new ResponseEntity<User>(HttpStatus.OK);
//        }
//
//        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//    }
//
//    public ResponseEntity<User> authRegister(String userEmail, User addThisUser) {
//        List<User> matchingUsers = userRepository.findAll().stream().filter(user -> user.getEmail().equals(userEmail)).toList();
//        if (matchingUsers.isEmpty()) {
//            userRepository.save(addThisUser);
//            return new ResponseEntity<User>(HttpStatus.CREATED);
//        }
//
//        return new ResponseEntity<User>(HttpStatus.CONFLICT);
//    }
//}
