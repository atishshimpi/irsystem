package com.org.irsystem.service;

import java.util.List;

import com.org.irsystem.model.Location;
import com.org.irsystem.model.User;

public interface LocationService {

	/**
	 * Retrieves all users
	 * 
	 * @return list of users
	 */
	public List<Location> getAll();

	/**
	 * Retrieves a single user based on id
	 * 
	 * @param id the id of the user
	 * @return the user
	 */
	public Location get(long id);
	
	/**
	 * Add a new location
	 * 
	 * @param user the new user
	 * @return true if successful
	 */
	public Boolean add(Location location);
	
	
	public Location getSubLocationByLocation(String locationName);

}