package com.qa.qaHoliday.model;

public class HolidayBooking {
	
	private long id;
	private String country;
	private String weather;
	private float price;
	private boolean allInclusive;
	
	// One constructor WITH id (retrieving data from the database)
	public HolidayBooking(long id, String country, String weather, float price, boolean allInclusive) {
		super();
		this.id = id;
		this.country = country;
		this.weather = weather;
		this.price = price;
		this.allInclusive = allInclusive;
	}

	// One constructor WITHOUT ID (posting data into the database)
	public HolidayBooking(String country, String weather, float price, boolean allInclusive) {
		super();
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
