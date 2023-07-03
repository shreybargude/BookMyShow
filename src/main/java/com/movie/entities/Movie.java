package com.movie.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Movie {
	@Id
	private String _id;
	private String moviePoster;
	private String movieName;
	private String releaseDate;
	private String category;
	private String genre;
	private String rating;
	private String country;
}
