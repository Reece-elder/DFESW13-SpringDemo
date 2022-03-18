package com.qa.qaHoliday.testing.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.qaHoliday.model.HolidayBooking;
import com.qa.qaHoliday.repo.Repo;
import com.qa.qaHoliday.services.ServiceDB;

@SpringBootTest
public class ServiceTest {
	
	// Adding the class we are Mocking (Repo)
	// Creating a Mock bean object of our Repo, for us to plug into our service 
	@MockBean
	private Repo repo;
	
	// Autowired - Spring automatically plugs in the beans into the object
	@Autowired
	private ServiceDB service; // Create a service object, plug in the mock repo automatically 
	
	
	// Test Objects I can pass into methods and have my repo return them 
	HolidayBooking booking1 = new HolidayBooking("country1", "weather2", 1, true);
	HolidayBooking booking2 = new HolidayBooking("country2", "weather2", 2, false);
	// Objects WITH id are for when we return them from our repo 
	HolidayBooking booking1Id = new HolidayBooking(1l, "country1", "weather2", 1, true);
	HolidayBooking booking2Id = new HolidayBooking(2l, "country2", "weather2", 2, false);
	
	@Test
	public void testCreate() {
		
		// Arrange 
		// When we tell our repo to save data, it should return the object with id
		// When our mock repo runs the save method taking in booking1
		// it should return booking1Id
		Mockito.when(repo.save(booking1)).thenReturn(booking1Id); 
		
		// Act
		// our boolean result equals to whatever our createBooking returns (true)
		HolidayBooking result = service.createBooking(booking1);
		System.out.println(result);
		System.out.println(booking1Id);
		
		// Assert
		Assertions.assertEquals(booking1Id, result);
		
//		Assertions.assertTrue(result);
		// Behvaiour Testing
		
		// Verifying if the mock Repo, was triggered (1) times, and what methods are we checking
		Mockito.verify(repo, Mockito.times(1)).save(booking1);
		
	}
	
	@Test
	public void testGetById() {
		
		// Arrange
		// Returns an Optional of an object, an object wrapped up in a box (so we dont cause a null pointer exception)
		Mockito.when(repo.findById(1l)).thenReturn(Optional.of(booking1Id));
		
		// Act
		HolidayBooking result = service.getById(1l);
		
		// Assert
		Assertions.assertEquals(booking1Id, result);
		
		// Our mocked object NEVER runs the .count()
		Mockito.verify(repo, Mockito.never()).findById(1l);
		
	}
}
