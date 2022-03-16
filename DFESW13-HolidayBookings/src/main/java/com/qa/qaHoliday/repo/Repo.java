package com.qa.qaHoliday.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.qaHoliday.model.HolidayBooking;

// Repo class has all of the basic methods stored we may need to access our database
// repo.findAll() - return all data
// repo. save(Object) - Create an object and save it into the db

// JpaRepository takes in our object type (entity) AND the data type for our id
public interface Repo extends JpaRepository<HolidayBooking, Long> {
	
	// This class extends off JpaRepo which has *most* methods we need
	
	// Get Holiday filtered by country 

}
