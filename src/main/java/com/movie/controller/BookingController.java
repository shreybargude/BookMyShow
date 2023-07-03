package com.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.dto.ReqPayNowDto;
import com.movie.dto.ReqTicketDto;
import com.movie.dto.ResPayNowDto;
import com.movie.dto.ResTicketDto;
import com.movie.service.BookingService;

@RestController
@CrossOrigin
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/pay-now")
	public ResponseEntity<ResPayNowDto> bookingDetails(@RequestBody ReqPayNowDto reqPayNowDto){
		
		ResPayNowDto resPayNowDto = bookingService.bookingDetails(reqPayNowDto);
		
		if(resPayNowDto!=null) {
			return new ResponseEntity<>(resPayNowDto, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/ticket")
	public ResponseEntity<ResTicketDto> bookTicket(@RequestBody ReqTicketDto reqTicketDto){
		
		ResTicketDto resTicketDto = bookingService.bookTicket(reqTicketDto);
		
		if(resTicketDto!=null) {
			return new ResponseEntity<>(resTicketDto, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/view-tickets")
	public ResponseEntity<List<ResTicketDto>> viewTickets(){
		
		List<ResTicketDto> resTicketDtoList = bookingService.viewTickets();
		
		if(resTicketDtoList!=null && !resTicketDtoList.isEmpty()) {
			return new ResponseEntity<>(resTicketDtoList,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/ticket-details/{email}")
	public ResponseEntity<List<ResTicketDto>> viewMyTickets(@PathVariable String email) {

		List<ResTicketDto> resTicketDtoList = bookingService.viewMyTickets(email);
		
		if(resTicketDtoList!=null && !resTicketDtoList.isEmpty()) {
			return new ResponseEntity<>(resTicketDtoList,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
} 
