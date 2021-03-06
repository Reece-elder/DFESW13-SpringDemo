package com.qa.qaHoliday.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.qaHoliday.model.HolidayBooking;
import com.qa.qaHoliday.repo.Repo;

@Service
public class ServiceDB {
	
	private Repo repo;

	public ServiceDB(Repo repo) {
		super();
		this.repo = repo;
	}
	
	// Creates a new Record and puts it in the DB
	public boolean createBooking(HolidayBooking booking) {
		repo.save(booking); // Takes in an entity, and puts in the DB 
		return true;
	}

	// Returning a record by searching for its ID
	public HolidayBooking getById(long id) {
		// .get() if there is a value return it, otherwise throw an exception 
		return repo.findById(id).get();
	}

	// Returns all the objects as a List
	public List<HolidayBooking> getBookings() {
		return repo.findAll();
	}

	public boolean remove(long id) {
		repo.deleteById(id);
		return true;
	}
	
	public boolean deleteAll() {
		repo.deleteAll();
		return true;
	}

	// Update takes in an ID and a request body
	public boolean update(long id, HolidayBooking booking) {
		
		// Find the object we want to update 
		HolidayBooking oldBooking = getById(id);
		
		// Update the properties of this object 
		// running the setProperty method of the old booking, replacing with values from the new booking 
		oldBooking.setCountry(booking.getCountry());
		oldBooking.setWeather(booking.getWeather());
		oldBooking.setPrice(booking.getPrice());
		oldBooking.setAllInclusive(booking.isAllInclusive());
		
		// Creating a new booking with the value of oldBooking
		HolidayBooking updatedBooking = oldBooking;
		
		// Saving this old Booking into the DB
		repo.save(updatedBooking);
		
		return true;
	}
}
