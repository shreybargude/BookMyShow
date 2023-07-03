package com.movie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie,String>{
	
	public Movie findByMovieName(String name);
	
	List<Movie> findByMovieNameContaining(String keyword);
}
