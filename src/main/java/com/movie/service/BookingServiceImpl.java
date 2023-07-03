package com.movie.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.converter.BookingConverter;
import com.movie.dao.BookingRepository;
import com.movie.dao.MovieRepository;
import com.movie.dao.UserRepository;
import com.movie.dto.ReqPayNowDto;
import com.movie.dto.ReqTicketDto;
import com.movie.dto.ResPayNowDto;
import com.movie.dto.ResTicketDto;
import com.movie.entities.Booking;
import com.movie.entities.Movie;
import com.movie.entities.User;
import com.movie.helper.RandomIdGenerator;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired private BookingRepository bookingRepository;
	
	@Autowired private UserRepository userRepository;
	
	@Autowired private MovieRepository movieRepository;
	
	@Autowired private ModelMapper modelMapper;
	
	@Autowired private BookingConverter bookingConverter;
	
	@Override
	public List<ResTicketDto> viewTickets() {
		
		List<Booking> booking = bookingRepository.findAll();
		
		//entityToDto for List
		List<ResTicketDto> resTicketDtoList = bookingConverter.entityToDto(booking);
				
		return resTicketDtoList;
	}

	@Override
	public ResPayNowDto bookingDetails(ReqPayNowDto reqPayNowDto) {
		
		ResPayNowDto resPayNowDto = this.modelMapper.map(reqPayNowDto, ResPayNowDto.class);
		resPayNowDto.set_id(RandomIdGenerator.generateRandomId());
		
		return resPayNowDto;
	}

	@Override
	public ResTicketDto bookTicket(ReqTicketDto reqTicketDto) {
		
		User user = userRepository.getUserByUserName(reqTicketDto.getUserEmail());
		
		Movie movie = movieRepository.findByMovieName(reqTicketDto.getMovieName());
		
		Booking booking = this.modelMapper.map(reqTicketDto, Booking.class);
		booking.set_id(RandomIdGenerator.generateRandomId());
		booking.setUser(user);
		booking.setMovie(movie);
		booking.setBookingDate(LocalDate.now());
		booking.setBookingTime(LocalTime.now());
		
		bookingRepository.save(booking);
		
		ResTicketDto resTicketDto = bookingConverter.entityToDto(booking);
		
		return resTicketDto;
	}
	
	@Override
	public List<ResTicketDto> viewMyTickets(String email) {
		
		User user = userRepository.getUserByUserName(email);
		
		List<Booking> myBookings = bookingRepository.findByUser(user);
		
		List<ResTicketDto> myTicketsDtos = new ArrayList<ResTicketDto>();
		myTicketsDtos = bookingConverter.entityToDto(myBookings);
		
		return myTicketsDtos;
	}
}
