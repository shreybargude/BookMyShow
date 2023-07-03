package com.movie.service;

import java.util.List;

import com.movie.dto.ReqMovieDto;
import com.movie.dto.ResMovieDto;

public interface MovieService {

	public List<ResMovieDto> addMovie(List<ReqMovieDto> reqMovieDto);

	public List<ResMovieDto> getAllMovies();

	public ResMovieDto getMovie(String movieId);

	public List<ResMovieDto> searchPosts(String keywords);

}
