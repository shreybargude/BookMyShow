package com.movie.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResTicketDto {
	private String totalPrice;
	private String price;
	private String userName;
	private String userEmail;
	private String movieName;
	private String poster;
	private String location;
	private String theater;
	
	private List<String> bookedSeats;
	
	private String time;
	private String food;
	private String cardHolder;
	private String cardNo;
	private String expDate;
	private String _id;
}
