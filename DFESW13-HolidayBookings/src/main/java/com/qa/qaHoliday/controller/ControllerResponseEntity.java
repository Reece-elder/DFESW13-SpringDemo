package com.qa.qaHoliday.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.qaHoliday.model.HolidayBooking;

// If you have multiple classes with annotation @RestController, Spring will use the first one it finds

@RestController
public class ControllerResponseEntity {
	
	private ArrayList<HolidayBooking> bookingList = new ArrayList<>();
	
	// Response Entities offer more info when sending a response back
	// Response insludes a message AND a status code we can specify AND the data it requested
	
	// Sending a Delete Request, we respond with "Deleted ID of x" AND code 202 
	// Sending a Get request, we respond with the Data requested AND a status code 200
	
	@PostMapping("/createBooking")
	public ResponseEntity<String> createBooking(@RequestBody HolidayBooking booking) {
		System.out.println(booking);
		
		// bookinglist.size = Length of the array
		booking.setId(bookingList.size() + 1);
		bookingList.add(booking);
		
		// returns a response entity<data it contains>
		ResponseEntity<String> response = new ResponseEntity<>("Booking added with ID: " + booking.getId(), HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/get/{index}")
	public ResponseEntity<HolidayBooking> getByIndex(@PathVariable("index") int index) {
		
		// Making an object variable called result = the data we're retrieving
		HolidayBooking result = bookingList.get(index);
		
		// Making a ResponseEntity that contains the data we're sending
		ResponseEntity<HolidayBooking> response = new ResponseEntity<>(result, HttpStatus.ACCEPTED);
		
		return response;
	}

}
