package com.akshara.UserService.service.impl;

import com.akshara.UserService.entities.User;
import com.akshara.UserService.repositories.UserRepository;
import com.akshara.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createOrUpdateUser(User user) {
//        String randomUserId = UUID.randomUUID().toString();
//        user.setUserId(randomUserId);
        return userRepository.save(user);

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElse(null);

    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }


}
