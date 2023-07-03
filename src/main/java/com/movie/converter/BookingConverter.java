package com.movie.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.movie.dto.ResTicketDto;
import com.movie.entities.Booking;

@Component
public class BookingConverter {
	
	public ResTicketDto entityToDto(Booking booking) {
		
		ResTicketDto resTicketDto = new ResTicketDto();
		resTicketDto.setUserName(booking.getUser().getFname());
		resTicketDto.setUserEmail(booking.getUserEmail());
		resTicketDto.setMovieName(booking.getMovieName());
		resTicketDto.setPoster(booking.getMovie().getMoviePoster());
		resTicketDto.setLocation(booking.getLocation());
		resTicketDto.setTheater(booking.getTheater());
		resTicketDto.setBookedSeats(booking.getBookedSeats());
		resTicketDto.setTime(booking.getTime());
		resTicketDto.setFood(booking.getFood());
		resTicketDto.setPrice(booking.getPrice());
		resTicketDto.setTotalPrice(booking.getTotalPrice());
		resTicketDto.setCardNo(booking.getCardNo());
		resTicketDto.setCardHolder(booking.getCardHolder());
		resTicketDto.setExpDate(booking.getExpDate());
		resTicketDto.set_id(booking.get_id());
		
		return resTicketDto;
		
	}
	
	public List<ResTicketDto> entityToDto(List<Booking> booking){
		return booking.stream().map(x->entityToDto(x)).collect(Collectors.toList());
	}
}
