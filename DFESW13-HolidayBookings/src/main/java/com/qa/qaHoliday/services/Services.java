package com.qa.qaHoliday.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.qa.qaHoliday.model.HolidayBooking;
import com.qa.qaHoliday.repo.Repo;

// Annotation tells Spring this is our Services class
// Services is the business Logic, pushing data to database / arrayList, updating deleting etc.

//@Service
public class Services {
	
	// Specify that we want to use our repo class
	
	private Repo repo;
	
	// When spring creates the Service Object / bean, it takes in our repo class
	public Services(Repo repo) {
		super();
		this.repo = repo;
	}
	
	// Replaces the SQL query INSERT INTO Holiday_bookings (weather, country).. VALUE("spain")..
	public boolean createBookingDB(HolidayBooking booking) {
		repo.save(booking); // Takes in an entity, and puts in the DB 
		return true;
	}

	private ArrayList<HolidayBooking> bookingList = new ArrayList<>();
	
	// Has the same functionality as the createBooking in the controller class 
	public boolean createBooking(HolidayBooking booking) {	
		booking.setId(bookingList.size() + 1);
		bookingList.add(booking);
		return true; // it returns a boolean, but this is not sent to the user
	}

	public HolidayBooking getByIndex(int index) {
		return bookingList.get(index);
	}

	public ArrayList<HolidayBooking> getBookings() {
		return bookingList;
	}

	public boolean remove(int index) {
		bookingList.remove(index);
		return true;
	}

	public boolean update(int index, HolidayBooking booking) {
		bookingList.set(index, booking);
		return true;
	}

	public boolean deleteAll() {
		bookingList.clear();
		return true;
	}

}
