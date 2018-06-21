/**
 * 
 */
package com.org.irsystem.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.irsystem.dao.LocationDao;
import com.org.irsystem.model.Location;

/**
 * Handles CRUD services for users
 * 
 */
@Service("locationService")
@Transactional
public class LocationServiceImpl implements LocationService{
 	
	@Autowired
	private LocationDao dao;
	
	protected static Logger logger = Logger.getLogger("service");

	public LocationServiceImpl() {

	}
	
	public List<Location> getAll() {
		logger.debug("Retrieving all users");

		List<Location> locations = dao.listLocations();
		return locations;
	}
	
	public Location get(long id) {
		logger.debug("Retrieving an existing user");
		
		return  dao.getLocationById(Long.valueOf(id));
		
	}
	
	public Location getSubLocationByLocation(String locationName) {
		logger.debug("Retrieving an existing user");
		return  dao.getSubLocationByLocationName(locationName);
		
	}

	@Override
	public Boolean add(Location location) {
		logger.debug("Adding a new user");
		
		try {
			dao.addLocation(location);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
