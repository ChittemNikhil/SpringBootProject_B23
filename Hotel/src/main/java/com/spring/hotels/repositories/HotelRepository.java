package com.spring.hotels.repositories;

import com.spring.hotels.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String>{
}
