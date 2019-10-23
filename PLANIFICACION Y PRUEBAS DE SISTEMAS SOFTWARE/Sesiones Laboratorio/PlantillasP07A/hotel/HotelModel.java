////////////////////////////////////////////////////////////////////////////////
// HotelModel: $Source$
// TODO Class summary goes here
//
// Created : 15 nov. 2005 by jfsmart
// Last modified $Date$ by $Author$
// Revision: $Revision$
// Version : $ID$
// Copyright (c) 2005
////////////////////////////////////////////////////////////////////////////////

package ppss.model;

import java.util.ArrayList;
import java.util.List;
import ppss.businessobjects.Hotel;

/**
 * 
 * @author jfsmart
 *
 * A simple class providing lookup services for a Hotel database.
 */

public class HotelModel {
 
	/**
	 * The list of all known cities in the database.
	 */
	private static String[] cities =
	{
		"Paris",
		"London",
	};
	
	/**
	 * The list of all hotels in the database.
	 */
	private static Hotel[] hotels = {
		new Hotel("Hotel Latin","Quartier latin","Paris",3),
		new Hotel("Hotel Etoile","Place de l'Etoile","Paris",4),
		new Hotel("Hotel Vendome","Place Vendome","Paris",5),
		new Hotel("Hotel Hilton","Trafalgar Square","London",4),
		new Hotel("Hotel Ibis","The City","London",3),
	};
	
	/**
	 * Returns the hotels in a given city.
	 * @param city the name of the city
	 * @return a list of Hotel objects
	 */
	public List<Hotel> findHotelsByCity(String city){
		List<Hotel> hotelsFound = new ArrayList<Hotel>();
		
		for(Hotel hotel : hotels) {
			if (hotel.getCity().equalsIgnoreCase(city)) {
				hotelsFound.add(hotel);
			}
		}
		
		return hotelsFound;
	}	
	
	/**
	 * Returns the list of cities in the database which have a hotel. 
	 * @return a list of city names
	 */
	public String[] findAvailableCities() {
		return cities;
	}
   
}
