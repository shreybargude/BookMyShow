package com.movie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.entities.Booking;
import com.movie.entities.User;

public interface BookingRepository extends JpaRepository<Booking, String>{
	
	List<Booking> findByUser(User user);
	
}
