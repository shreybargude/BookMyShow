package com.movie.dto;

import lombok.Data;

@Data
public class ResLoginDto {
	private ResUserDto user;
	private String auth;
}
