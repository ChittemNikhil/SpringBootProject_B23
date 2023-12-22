package com.akshara.UserService.service;

import com.akshara.UserService.entities.User;

import java.util.List;

public interface UserService {

    User createOrUpdateUser(User user);

    List<User> getAllUsers();

    User getUser(String userId);

    void deleteUser(String userId);

}
