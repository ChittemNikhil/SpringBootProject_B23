package com.akshara.UserService.controller;


import com.akshara.UserService.entities.User;
import com.akshara.UserService.repositories.UserRepository;
import com.akshara.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){

       User createdUser = userService.createOrUpdateUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);

    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") String userId, @RequestBody User user ){

        User existingUser = userService.getUser(userId);

        if(existingUser == null){
            User savedUser =  userService.createOrUpdateUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }

        user.setUserId(userId);
        User updateUser = userRepository.save(user);
        return ResponseEntity.ok(updateUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
