package com.qa.qaHoliday.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Put in the annotation @Entity so Spring knows this is an entity - import javax.persistence
@Entity
public class HolidayBooking {
	
	// Tell spring what property is our primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Telling Spring it will auto generate and auto increment??
	private long id;
	
	// @Column tells Spring this will be a header for our table
	// (unique = true, otherOptions=value)  
	@Column(nullable = false, length = 30) // value cannot be null
	private String country;
	
	@Column(nullable = false, length = 25)
	private String weather;
	
	@Column(nullable = false)
	private float price;
	
	@Column(nullable = false)
	private boolean allInclusive;
	
	// Will Create a MySQL table called HolidayBooking with these exact columns
	// TO get all of the data from a database repo.getAll();
	
	
	// When using Request bodies with Spring you need to include a default constructor
	// Generate constructors from superclass() > generate
	public HolidayBooking() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// One constructor WITHOUT ID (posting data into the database)
	public HolidayBooking(String country, String weather, float price, boolean allInclusive) {
		super();
		this.country = country;
		this.weather = weather;
		this.price = price;
		this.allInclusive = allInclusive;
	}

	// One constructor WITH id (retrieving data from the database)
	public HolidayBooking(long id, String country, String weather, float price, boolean allInclusive) {
		super();
		this.id = id;
		this.country = country;
		this.weather = weather;
		this.price = price;
		this.allInclusive = allInclusive;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isAllInclusive() {
		return allInclusive;
	}

	public void setAllInclusive(boolean allInclusive) {
		this.allInclusive = allInclusive;
	}

	// Objects already have a built in hashcode method, hashcodes are generated 
	// randomly and equal this objects memory reference
	// hashcode - com.qa.car@26vbfhf
	
	// override hashcode makes two objects with the same values have the same hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (allInclusive ? 1231 : 1237);
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((weather == null) ? 0 : weather.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HolidayBooking other = (HolidayBooking) obj;
		if (allInclusive != other.allInclusive)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id != other.id)
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (weather == null) {
			if (other.weather != null)
				return false;
		} else if (!weather.equals(other.weather))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HolidayBooking [id=" + id + ", country=" + country + ", weather=" + weather + ", price=" + price
				+ ", allInclusive=" + allInclusive + "]";
	}
}
