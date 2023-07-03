package com.movie.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.movie.dao.UserRepository;
import com.movie.dto.AuthRequest;
import com.movie.dto.ReqUserDto;
import com.movie.dto.ResLoginDto;
import com.movie.dto.ResUserDto;
import com.movie.entities.User;
import com.movie.helper.RandomIdGenerator;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public ResUserDto addUser(ReqUserDto reqUserDto){
		
		User user = new User();
		
		BeanUtils.copyProperties(user, reqUserDto);
        
		user.set_id(RandomIdGenerator.generateRandomId());
		
		userRepository.save(user);
		
		ResUserDto resUserDto = new ResUserDto();
		try {
            BeanUtils.copyProperties(resUserDto, user);
        } catch (Exception e) {
            // Handle any exceptions thrown by BeanUtils.copyProperties()
            throw new ServiceException("Failed to map user to DTO", e);
        }

		return resUserDto;
	}

	@Override
	public List<ResUserDto> getUsers() {
		List<User> users = userRepository.findAll();
		
        List<ResUserDto> userDtos = new ArrayList<>();
        for (User user : users) {
        	ResUserDto userDto = new ResUserDto();
            
            BeanUtils.copyProperties(userDto, user);
            
            userDtos.add(userDto);
        }
        return userDtos;
    }

	@Override
	public ResLoginDto authenticateAndGetToken(AuthRequest authRequest) {
		System.out.println("authentication start");
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		System.out.println("authentication end");
		
		if(authentication.isAuthenticated()) {
			final String token = jwtService.generateToken(authRequest.getEmail());
			
			User user = userRepository.getUserByUserName(authRequest.getEmail());
			ResLoginDto resLoginDto = new ResLoginDto();
			resLoginDto.setUser(this.modelMapper.map(user, ResUserDto.class));
			resLoginDto.setAuth(token);
			
			return resLoginDto;
		}else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}
}
