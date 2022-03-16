package com.qa.qaHoliday.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.qaHoliday.model.HolidayBooking;
import com.qa.qaHoliday.services.Services;

// If you have multiple classes with annotation @RestController, Spring will use the first one it finds

@RestController
public class Controller {
	
	// Not using the arrayList in the controller anymore!!!
//	private ArrayList<HolidayBooking> bookingList = new ArrayList<>(); 
	
	// Response Entities offer more info when sending a response back
	// Response insludes a message AND a status code we can specify AND the data it requested
	
	// Sending a Delete Request, we respond with "Deleted ID of x" AND code 202 
	// Sending a Get request, we respond with the Data requested AND a status code 200
	
	// Tell our Controller to use the Services Object
	// When Spring creates our Controller, it passes in the Service object
	private Services service;
	
	public Controller(Services service) {
		super();
		this.service = service;
	}

	@PostMapping("/createBooking")
	public ResponseEntity<String> createBooking(@RequestBody HolidayBooking booking) {
		
		// run the method in the Services class, passing in the object recieved via HTTP Request
		service.createBookingDB(booking);
		
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
	
	// Update via index
	@PutMapping("/update/{index}")
	public ResponseEntity<String> updateByIndex(@PathVariable("index") int index, @RequestBody HolidayBooking booking) {
		
		service.update(index, booking); // Telling the Service to do the method, but not doing anything with the return
		
		String response = "Updating booking of index: " + index;
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	// Delete all method within the controller 
	@DeleteMapping("/delete") // <-- Handling the request coming in
	public ResponseEntity<String> deleteAll() {
		
		// bookingList.clear();  <-- Business logic, removing all data 
		service.deleteAll();
		
		//                       <-- Handling the response to be sent back
		return new ResponseEntity<>("all bookings deleted", HttpStatus.ACCEPTED);
	}

}
