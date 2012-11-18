package com.github.susannebraun.gulasch.park.data;

public class MyParkingLot {
	
	public long id;

	public String name;
	
	public String street;
	
	public String streetNumber;
	
	public String zip;
	
	public String city;
	
	public boolean parked;

	public MyParkingLot(long id, String name, String street, String streetNumber,
			String zip, String city) {
		super();
		this.id = id;
		this.name = name;
		this.street = street;
		this.streetNumber = streetNumber;
		this.zip = zip;
		this.city = city;
	}

	public String getAdress() {
		StringBuilder buffer = new StringBuilder();
		
		if(street != null && streetNumber != null) {
			buffer.append(street).append(" ").append(streetNumber).append(", ");
		}
		if(zip != null && city != null) {
			buffer.append(zip).append(" ").append(city);
		}
		
		return buffer.toString();
	}
	
	
	

}
