package com.qa.qaHoliday.testing.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.qaHoliday.model.HolidayBooking;
 
// Telling Spring this is a test class
// Our tests will run on a random port, if the port is already in use choose a new one
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// Set up our Mock MVC (replacing postman) to work with the project
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Allows us to use @BeforeAll in a NON STATIC way
// It will run the .sql file data.sql BEFORE the test method 
@Sql(scripts = {"classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ControllerTest {
	
	
	@Autowired // Plugs our MockMvc into the spring app directly 
	private MockMvc mvc; // Object that performs a Mock request (like postman) imports in springFramework
	
	@Autowired // plugs into our spring app
	private ObjectMapper mapper; // Converts Java Objects to JSON Strings {"country":"wales","weather":"rainy"}
	
	// Test Object
	HolidayBooking testBooking = new HolidayBooking("country1", "weather1", 1, true);
	
	// This will run before all of the other tests once
//	@BeforeAll
//	public void setup() throws Exception {
//		// Before any tests run, I want there to be atleast one test object in the database
//		String bookingJson = mapper.writeValueAsString(testBooking);
//		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(bookingJson);
//		mvc.perform(req);
//	}
	
	// Spring comes with JUnit 5 and Mockito
	
	@Test
	public void testCreate() throws Exception {
		
		// Arrange 
		// Converting our Test Object into a JSON string called bookingJson 
		String bookingJson = mapper.writeValueAsString(testBooking);
		
		// Import RequestBuilder from Spring framework
		// = Request Type (get, post, put) 
		// ("/request path")  
		// we are posting JSON string
		// content() the body of data you are sending
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(bookingJson);
		// Equivalent to writing our request in postman BEFORE clicking send
		
		// Act 
		// Making a ResultMatcher object (mvc thing), it is either true or false
		// this depends on the status().isCreated()
		
		ResultMatcher checkStatus = status().isCreated(); // Is the status code of our request created? (201) 
		ResultMatcher checkBody = content().string("Booking added with ID: 1"); // import in result string
		// Assert
		
		// tell our mvc (postman mock) to run the request (click send)
		// run the request and checkStatus SHOULD be true (status code is correct) 
		// AND checkBody SHOULD Be true (response body is correct) 
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	public void testDeleteId() throws Exception {
		RequestBuilder req = delete("/delete/1");
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().string("Booking of id: 1 deleted");
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);		
	}
	
	@Test
	public void testGetId() throws Exception {
		
		// Arrange 
		HolidayBooking testBookingId = testBooking; // Making a new booking obj equal to our testBooking Object
		testBookingId.setId(1l); // Setting the id of our object to be 6, identical to how it will be
								 // when we get it from the database
		
		String testBookingIdJson = mapper.writeValueAsString(testBookingId); // converting our obj (with id) to JSON
		
		// Act 
		// Passing in the path variable 6, but is added as part of the string
		RequestBuilder req = get("/getId/1");
		
		ResultMatcher checkStatus = status().isAccepted(); // is the response status code accepted?
		ResultMatcher checkBody = content().json(testBookingIdJson); // Is the response content a Json String of our object?
		
		// Assert
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
}