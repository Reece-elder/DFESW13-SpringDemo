package com.qa.qaHoliday.testing.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
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
// It will run the .sql file data.sql BEFORE every @Test
@Sql(scripts = { "classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("dev") // When running tests it will use the dev profile
public class ControllerTest {

	@Autowired // Plugs our MockMvc into the spring app directly
	private MockMvc mvc; // Object that performs a Mock request (like postman) imports in springFramework

	@Autowired // plugs into our spring app
	private ObjectMapper mapper; // Converts Java Objects to JSON Strings {"country":"wales","weather":"rainy"}

	// Test Object
	HolidayBooking testBooking3 = new HolidayBooking("country3", "weather3", 3, true);
	HolidayBooking testBookingID = new HolidayBooking(1l, "country1", "weather1", 1, true);
	HolidayBooking testBookingID2 = new HolidayBooking(2l, "country2", "weather2", 2, true);

	// Spring comes with JUnit 5 and Mockito

	@Test
	public void testCreate() throws Exception {

		// Arrange
		// Converting our Test Object into a JSON string called bookingJson
		String bookingJson = mapper.writeValueAsString(testBooking3);

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
		ResultMatcher checkBody = content().string("Booking added with ID: 3"); // import in result string
		// Assert

		// tell our mvc (postman mock) to run the request (click send)
		// run the request and checkStatus SHOULD be true (status code is correct)
		// AND checkBody SHOULD Be true (response body is correct)
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	public void testDeleteId() throws Exception {
		RequestBuilder req = delete("/delete/1"); // Telling our request to delete id 1

		ResultMatcher checkStatus = status().isAccepted(); // is the responseEntity status code accepted
		ResultMatcher checkBody = content().string("Booking of id: 1 deleted"); // what string does the response entity
																				// respond with

		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody); // Run the request and check the status and
																		// content
	}

	@Test
	public void testGetAll() throws Exception {

		// Make a list of the testData to check the response
		List<HolidayBooking> allBookings = List.of(testBookingID, testBookingID2); // creating a list which contains
																					// these objects

		// Convert my list to JSON
		String allBookingsJson = mapper.writeValueAsString(allBookings);

		// Our request doesnt take in any other params
		RequestBuilder req = get("/getBookings");

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(allBookingsJson); // If not valid, we'll be able to see the exact
																	// response

		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	public void testGetId() throws Exception {

		// Arrange
//		HolidayBooking testBookingId = testBooking1; // Making a new booking obj equal to our testBooking Object
//		testBookingId.setId(1l); // Setting the id of our object to be 6, identical to how it will be
		// when we get it from the database

		String testBookingIdJson = mapper.writeValueAsString(testBookingID); // converting our obj (with id) to JSON

		// Act
		// Passing in the path variable 6, but is added as part of the string
		RequestBuilder req = get("/getId/1");

		ResultMatcher checkStatus = status().isAccepted(); // is the response status code accepted?
		ResultMatcher checkBody = content().json(testBookingIdJson); // Is the response content a Json String of our
																		// object?

		// Assert
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	public void testUpdate() throws Exception {

		// Arrange
		// The updated object we'll be passing in
		HolidayBooking updatedBooking = new HolidayBooking("new country", "new weather", 10, false);

		// Converting our object to JSON
		String updatedBookingJson = mapper.writeValueAsString(updatedBooking);

		// requestBuilder takes in the , request type, Path and JSON object
		RequestBuilder req = put("/update/1").contentType(MediaType.APPLICATION_JSON).content(updatedBookingJson);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().string("Updating booking of id: 1");

		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

}
