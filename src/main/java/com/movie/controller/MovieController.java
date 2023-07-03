package com.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.dto.ReqMovieDto;
import com.movie.dto.ResMovieDto;
import com.movie.service.MovieService;

@RestController
@CrossOrigin
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping("/movies")
	public ResponseEntity<List<ResMovieDto>> addMovie(@RequestBody List<ReqMovieDto> reqMovieDto){
		
		List<ResMovieDto> resMovieDto = movieService.addMovie(reqMovieDto);
		
		if(resMovieDto!=null) {
			return new ResponseEntity<>(resMovieDto,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/movies")
	public ResponseEntity<List<ResMovieDto>> getAllMovies(){
		
		List<ResMovieDto> resMovieDtos = movieService.getAllMovies();
		
		if(resMovieDtos!=null && !resMovieDtos.isEmpty()) {
			return new ResponseEntity<>(resMovieDtos,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("book/{movieId}")
	public ResponseEntity<ResMovieDto> getMovie(@PathVariable String movieId){
		
		ResMovieDto resMovieDto = movieService.getMovie(movieId);
		
		if(resMovieDto!=null) {
			return new ResponseEntity<>(resMovieDto,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//search
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<ResMovieDto>> searchPostByTitle(
			@PathVariable("keywords") String keywords){
		List<ResMovieDto> result = this.movieService.searchPosts(keywords);
		if(result!=null) {
			return new ResponseEntity<>(result,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
