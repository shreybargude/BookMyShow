package com.movie.dto;

import java.util.List;

import lombok.Data;

@Data
public class ReqTicketDto {
	private String userName;
	private String userEmail;
	private String movieName;
	private String poster;
	private String location;
	private String theater;
	
	private List<String> bookedSeats;
	
	private String time;
	private String food;
	private String price;
	private String totalPrice;
	private String cardNo;
	private String cardHolder;
	private String expDate;
	private String _id;
}
