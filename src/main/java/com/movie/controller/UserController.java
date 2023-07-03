package com.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.dto.AuthRequest;
import com.movie.dto.ReqUserDto;
import com.movie.dto.ResLoginDto;
import com.movie.dto.ResUserDto;
import com.movie.service.UserService;

@RestController
@CrossOrigin
public class UserController {
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<ResUserDto> addUser(@RequestBody ReqUserDto reqUserDto){
		
		reqUserDto.setPassword(passwordEncoder.encode(reqUserDto.getPassword()));
		
		ResUserDto resUserDto = userService.addUser(reqUserDto);
		
		if(resUserDto!=null) {
			return new ResponseEntity<>(resUserDto,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/register")
	public ResponseEntity<List<ResUserDto>> getUsers(){
		
		List<ResUserDto> resUserDtoList = userService.getUsers();
		
		if(resUserDtoList!=null && !resUserDtoList.isEmpty()) {
			return new ResponseEntity<>(resUserDtoList,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResLoginDto> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		
		ResLoginDto resLoginDto = userService.authenticateAndGetToken(authRequest);
		
		if(resLoginDto!=null) {
			return new ResponseEntity<>(resLoginDto, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
