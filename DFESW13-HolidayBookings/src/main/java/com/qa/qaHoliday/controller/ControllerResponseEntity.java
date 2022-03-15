package com.qa.qaHoliday.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.qaHoliday.model.HolidayBooking;
import com.qa.qaHoliday.services.Services;

// If you have multiple classes with annotation @RestController, Spring will use the first one it finds

@RestController
public class ControllerResponseEntity {
	
	// Not using the arrayList in the controller anymore!!!
//	private ArrayList<HolidayBooking> bookingList = new ArrayList<>(); 
	
	// Response Entities offer more info when sending a response back
	// Response insludes a message AND a status code we can specify AND the data it requested
	
	// Sending a Delete Request, we respond with "Deleted ID of x" AND code 202 
	// Sending a Get request, we respond with the Data requested AND a status code 200
	
	// Tell our Controller to use the Services Object
	// When Spring creates our Controller, it passes in the Service object
	private Services service;
	
	public ControllerResponseEntity(Services service) {
		super();
		this.service = service;
	}

	@PostMapping("/createBooking")
	public ResponseEntity<String> createBooking(@RequestBody HolidayBooking booking) {
		System.out.println(booking);
		
		// run the method in the Services class
		service.createBooking(booking);
		
		// returns a response entity<data it contains>
		ResponseEntity<String> response = new ResponseEntity<>("Booking added with ID: " + booking.getId(), HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/get/{index}")
	public ResponseEntity<HolidayBooking> getByIndex(@PathVariable("index") int index) {
		
		// Making an object variable called result = the data we're retrieving
		HolidayBooking result = service.getByIndex(index);
		
		// Making a ResponseEntity that contains the data we're sending
		ResponseEntity<HolidayBooking> response = new ResponseEntity<>(result, HttpStatus.I_AM_A_TEAPOT);
		
		return response;
	}
	
	@GetMapping("/getBookings")
	public ResponseEntity<ArrayList<HolidayBooking>> getBookings() {
		
		ArrayList<HolidayBooking> response = service.getBookings();
		
		// Either one of these returns will work in the same way
		// ResponseEntity<ArrayList<HolidayBooking>> response = new ResponseEntity<>(bookingList, HttpStatus.ACCEPTED);
		// return response;
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{index}")
	public ResponseEntity<String> deleteByIndex(@PathVariable("index") int index) {
		service.remove(index);
		String response = "Booking of index: " + index + " deleted";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

}
