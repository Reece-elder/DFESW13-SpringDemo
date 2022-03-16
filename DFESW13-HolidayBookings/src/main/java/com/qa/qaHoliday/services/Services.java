package com.qa.qaHoliday.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.qa.qaHoliday.model.HolidayBooking;

// Annotation tells Spring this is our Services class
// Services is the business Logic, pushing data to database / arrayList, updating deleting etc.

@Service
public class Services {
	
	
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

}
