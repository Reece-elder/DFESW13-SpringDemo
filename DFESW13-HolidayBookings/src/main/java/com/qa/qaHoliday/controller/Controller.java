package com.qa.qaHoliday.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.qaHoliday.model.HolidayBooking;

// Telling Spring this class is a controller, meaning it takes in HTTP Requests
@RestController
public class Controller {
	
	// Because this is powered by Java 11, no need to add the data type to the 2nd pointy brackets
	private ArrayList<HolidayBooking> bookingList = new ArrayList<>();
	
	// this method listens for /getBookings
	// Returns our arrayList 
	@GetMapping("/getBookings")
	public ArrayList<HolidayBooking> getBookings() {
		System.out.println(bookingList);
		return bookingList;
	}
	
	// Create a method to post data 
	// listens to port 8080 and /createBooking
	@PostMapping("/createSetBooking")
	public boolean createSetBooking() {
		System.out.println("Will this print to the console???");
		bookingList.add(new HolidayBooking("Wales", "rainy", 14f, true));
		return true;
	}
	
	// GET requests are the default
	// When sending a HTTP request via browser unless you specify, its a GET request
	
	
	// Creating a POST request that takes in information to add to the database
	
	// The HTTP Request will be supplied with data, in the form of a Request Body
	// Our method takes in an object of type HolidayBooking, this will be a request body
	@PostMapping("/createBooking")
	public boolean createBooking(@RequestBody HolidayBooking booking) {
		System.out.println(booking);
		bookingList.add(booking);
		return true;
	}
	
	
	

}
