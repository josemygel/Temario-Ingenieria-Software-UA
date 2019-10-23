////////////////////////////////////////////////////////////////////////////////
// Hotel: $Source$
// TODO Class summary goes here
//
// Created : 15 nov. 2005 by jfsmart
// Last modified $Date$ by $Author$
// Revision: $Revision$
// Version : $ID$
// Copyright (c) 2005
////////////////////////////////////////////////////////////////////////////////

package ppss.businessobjects;

/**
 * @author jfsmart
 *
 * A simplified hotel business class.
 * A hotel, for this tutorial, has a name, an address, 
 * a city, and a number of stars.
 */
public class Hotel {
	private String name;
	private String address;
	private String city;
	private int stars;
	
	
	/**
	 * Default constructor;
	 */
	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Bean Constructor using field values. 
	 * @param name
	 * @param address
	 * @param city
	 * @param stars
	 */
	public Hotel(String name, String address, String city, int stars) {
		super();
		// TODO Auto-generated constructor stub
		this.name = name;
		this.address = address;
		this.city = city;
		this.stars = stars;
	}
	/**
	 * @return Returns the address.
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address The address to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the stars.
	 */
	public int getStars() {
		return stars;
	}
	/**
	 * @param stars The stars to set.
	 */
	public void setStars(int stars) {
		this.stars = stars;
	}
	    
}
