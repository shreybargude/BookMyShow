package com.movie.service;

import java.util.List;

import com.movie.dto.ReqPayNowDto;
import com.movie.dto.ReqTicketDto;
import com.movie.dto.ResPayNowDto;
import com.movie.dto.ResTicketDto;

public interface BookingService {
	
	public List<ResTicketDto> viewTickets();

	public ResPayNowDto bookingDetails(ReqPayNowDto reqPayNowDto);

	public ResTicketDto bookTicket(ReqTicketDto reqTicketDto);

	public List<ResTicketDto> viewMyTickets(String email);


}