package com.akshara.UserService.repositories;

import com.akshara.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {



}
