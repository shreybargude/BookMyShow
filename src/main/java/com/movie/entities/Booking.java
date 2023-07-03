package com.movie.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Booking {
	@Id
	private String _id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private String userEmail;
	
	@ManyToOne
	private Movie movie;
	
	private String movieName;
	private String location;
	private String theater;
	private List<String> bookedSeats;
	private String time;
	private String food;
	private String price;
	private String totalPrice;
	
	private LocalDate bookingDate;
	private LocalTime bookingTime;
	
	private String cardHolder;
	private String cardNo;
	private String expDate;
}
