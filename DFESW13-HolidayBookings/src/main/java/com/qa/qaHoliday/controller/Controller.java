package com.qa.qaHoliday.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.qa.qaHoliday.services.ServiceDB;

// If you have multiple classes with annotation @RestController, Spring will use the first one it finds

@RestController
public class Controller {
	
	// Not using the arrayList in the controller anymore!!!
//	private ArrayList<HolidayBooking> bookingList = new ArrayList<>(); 
	
	// Response Entities offer more info when sending a response back
	// Response includes a message AND a status code we can specify AND the data it requested
	
	// Sending a Delete Request, we respond with "Deleted ID of x" AND code 202 
	// Sending a Get request, we respond with the Data requested AND a status code 200
	
	// Tell our Controller to use the Services Object
	// When Spring creates our Controller, it passes in the Service object
	private ServiceDB service;
	
	public Controller(ServiceDB service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<String> createBooking(@RequestBody HolidayBooking booking) {
		
		// run the method in the Services class, passing in the object recieved via HTTP Request
		service.createBooking(booking);
		
		// returns a response entity<data it contains>
		ResponseEntity<String> response = new ResponseEntity<>("Booking added with ID: " + booking.getId(), HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/getId/{id}")
	public ResponseEntity<HolidayBooking> getById(@PathVariable("id") long id) {
		
		// Making an object variable called result = the data we're retrieving
		HolidayBooking result = service.getById(id);
		
		// Making a ResponseEntity that contains the data we're sending
		ResponseEntity<HolidayBooking> response = new ResponseEntity<>(result, HttpStatus.ACCEPTED);
		
		return response;
	}
	
	@GetMapping("/getBookings")
	public ResponseEntity<List<HolidayBooking>> getBookings() {
		
		List<HolidayBooking> response = service.getBookings();
		
		// Either one of these returns will work in the same way
		// ResponseEntity<ArrayList<HolidayBooking>> response = new ResponseEntity<>(bookingList, HttpStatus.ACCEPTED);
		// return response;
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteByid(@PathVariable("id") long id) {
		service.remove(id);
		String response = "Booking of id: " + id + " deleted";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	// Update via id
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateByid(@PathVariable("id") long id, @RequestBody HolidayBooking booking) {
		
		service.update(id, booking); // Telling the Service to do the method, but not doing anything with the return
		
		String response = "Updating booking of id: " + id;
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
	
	// Method to get all bookings with a country value of x
	@GetMapping("/getCountry/{country}")
	public ResponseEntity<List<HolidayBooking>> getByCountry(@PathVariable("country") String country) {
		
		List<HolidayBooking> response = service.getByCountry(country);
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
	
	// Method to get all bookings with a country value of x
	@GetMapping("/getPrice/{price}")
	public ResponseEntity<List<HolidayBooking>> getByPriceGreater(@PathVariable("price") float price) {
		
		List<HolidayBooking> response = service.getByPriceGreater(price);
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}

}
