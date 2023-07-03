package com.movie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.dao.MovieRepository;
import com.movie.dto.ReqMovieDto;
import com.movie.dto.ResMovieDto;
import com.movie.entities.Movie;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Movie dtoToEntity(ReqMovieDto reqMovieDto) {
		Movie movie = this.modelMapper.map(reqMovieDto, Movie.class);
		return movie;
	}
	
	private ResMovieDto entityToDto(Movie movie) {
		ResMovieDto resMovieDto = this.modelMapper.map(movie, ResMovieDto.class);
		return resMovieDto;
	}
	
	@Override
	public List<ResMovieDto> addMovie(List<ReqMovieDto> reqMovieDto) {
		//dtoToEntity
		List<Movie> movies = new ArrayList<>();
		movies = reqMovieDto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());

		//storing in database
		for(Movie movie: movies) {
			movieRepository.save(movie);
		}
		
		List<Movie> addedMovies = movieRepository.findAll();
		
		//entityToDto
		List<ResMovieDto> movieDtos = new ArrayList<>();
		movieDtos = addedMovies.stream().map(x->entityToDto(x)).collect(Collectors.toList());
		
		return movieDtos;
	}


	@Override
	public List<ResMovieDto> getAllMovies() {
		
		List<Movie> movies = movieRepository.findAll();
		
		//entityToDto
		List<ResMovieDto> resMovieDtoList = new ArrayList<>();
		resMovieDtoList = movies.stream().map(x->entityToDto(x)).collect(Collectors.toList());
		
		return resMovieDtoList;
	}

	@Override
	public ResMovieDto getMovie(String movieId) {
		
		Movie movie = movieRepository.findById(movieId).get();
		
		ResMovieDto resMovieDto = this.modelMapper.map(movie, ResMovieDto.class);
		
		return resMovieDto;
	}
	
	@Override
	public List<ResMovieDto> searchPosts(String keyword) {
		List<Movie> movie = this.movieRepository.findByMovieNameContaining(keyword);
		List<ResMovieDto> movieDtos = movie.stream().map((mov)->this.modelMapper.map(mov, ResMovieDto.class)).collect(Collectors.toList());
		return movieDtos;
	}
	
	
	
	
	
}
