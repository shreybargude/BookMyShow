package com.movie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movie.entities.User;

public interface UserRepository extends JpaRepository<User, String>{
	@Query("select u from User u where u.email =:email")
	public User getUserByUserName(@Param("email") String string);
}
