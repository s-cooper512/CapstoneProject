package org.example.diabeticcalculatorbackend.controller;

import org.example.diabeticcalculatorbackend.model.User;
import org.example.diabeticcalculatorbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createUser (@PathVariable long dosageProfileID, @RequestBody User user) {
        userService.createUser(user, dosageProfileID);
    }

    @DeleteMapping("/{ID}")
    public void deleteUserByID (@PathVariable long ID) {
        userService.deleteUserByID(ID);
    }

    @PutMapping("/{userID}/{dosageProfileID}")
    public void updateUserByID (@PathVariable long userID, @PathVariable long dosageProfileID, @RequestBody User user) {
        userService.updateUserByID(userID, dosageProfileID, user);
    }
}
