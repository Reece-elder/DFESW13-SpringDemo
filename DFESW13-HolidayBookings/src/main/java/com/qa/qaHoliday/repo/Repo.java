package com.qa.qaHoliday.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.qaHoliday.model.HolidayBooking;

// Repo class has all of the basic methods stored we may need to access our database
// repo.findAll() - return all data
// repo. save(Object) - Create an object and save it into the db

// JpaRepository takes in our object type (entity) AND the data type for our id
public interface Repo extends JpaRepository<HolidayBooking, Long> {
	
	// This class extends off JpaRepo which has *most* methods we need
	
	// Get Holiday filtered by country 
	
	// Abstract Method - Takes in no method body
	// Takes in a String called country and it WILL return all bookings of that country
	public List<HolidayBooking> findByCountry(String country);
	
	public List<HolidayBooking> findByWeather(String weather);
	// If you call a method findBy<column header>  -> Returns a List of objects with that value

	public List<HolidayBooking> findByPriceGreaterThan(float price);
	
	// SELECT * FROM holiday_booking WHERE all_inclusive = true
	public List<HolidayBooking> findByAllInclusive(boolean bool);
	
	public List<HolidayBooking> findByOrderByCountryAsc();
}
