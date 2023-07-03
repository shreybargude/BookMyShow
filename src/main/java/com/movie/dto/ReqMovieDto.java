package com.movie.dto;

import lombok.Data;

@Data
public class ReqMovieDto {
	private String _id;
	private String moviePoster;
	private String movieName;
	private String releaseDate;
	private String category;
	private String genre;
	private String rating;
	private String country;
}
