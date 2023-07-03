package com.movie.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ResUserDto {
	private String _id;
	private String fname;
	private String lname;
	private LocalDate dob;
	private String email;
}