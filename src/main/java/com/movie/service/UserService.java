package com.movie.service;

import java.util.List;

import com.movie.dto.AuthRequest;
import com.movie.dto.ReqUserDto;
import com.movie.dto.ResLoginDto;
import com.movie.dto.ResUserDto;

public interface UserService {

	public ResUserDto addUser(ReqUserDto reqUserDto);

	public List<ResUserDto> getUsers();

	public ResLoginDto authenticateAndGetToken(AuthRequest authRequest);


}
