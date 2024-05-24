package org.example.diabeticcalculatorbackend.controller;

import org.example.diabeticcalculatorbackend.model.User;
import org.example.diabeticcalculatorbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers () {
       return userService.getAllUsers();
    }

    @GetMapping("/{ID}")
    public User getUserByID (@PathVariable long ID) {
        return userService.getUserByID(ID);
    }

    @PostMapping("/{dosageProfileID}")
    public ResponseEntity<User> createUser (@PathVariable long dosageProfileID, @RequestBody User user) {
        userService.createUser(user, dosageProfileID);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<?> deleteUserByID (@PathVariable long ID) {
        userService.deleteUserByID(ID);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userID}/{dosageProfileID}")
    public ResponseEntity<User> updateUserByID (@PathVariable long userID, @PathVariable long dosageProfileID, @RequestBody User user) {
        userService.updateUserByID(userID, dosageProfileID, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
