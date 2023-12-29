package com.akshara.UserService.service.impl;

import com.akshara.UserService.entities.User;
import com.akshara.UserService.repositories.UserRepository;
import com.akshara.UserService.service.UserService;
import org.hibernate.annotations.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
        User fetchedUser = userRepository.findById(userId).orElse(null);
        ArrayList userRatings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+userId , ArrayList.class);
        logger.info("user ratings are {}, fetched user is {} ", userRatings, fetchedUser );
        fetchedUser.setRating(userRatings);
        return fetchedUser;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }


}
