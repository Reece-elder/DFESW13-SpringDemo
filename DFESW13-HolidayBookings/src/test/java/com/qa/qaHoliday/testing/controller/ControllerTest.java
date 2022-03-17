package com.qa.qaHoliday.testing.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.qaHoliday.model.HolidayBooking;
 
// Telling Spring this is a test class
// Our tests will run on a random port, if the port is already in use choose a new one
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// Set up our Mock MVC (replacing postman) to work with the project
@AutoConfigureMockMvc
public class ControllerTest {
	
	
	@Autowired // Plugs our MockMvc into the spring app directly 
	private MockMvc mvc; // Object that performs a Mock request (like postman) imports in springFramework
	
	@Autowired // plugs into our spring app
	private ObjectMapper mapper; // Converts Java Objects to JSON Strings {"country":"wales","weather":"rainy"}
	
	// Test Object
	HolidayBooking testBooking = new HolidayBooking("test country", "test weather", 1, true);
	
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
		ResultMatcher checkBody = content().string("Booking added with ID: 6"); // import in result string
		// Assert
		
		// tell our mvc (postman mock) to run the request (click send)
		// run the request and checkStatus SHOULD be true (status code is correct) 
		// AND checkBody SHOULD Be true (response body is correct) 
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}

}
