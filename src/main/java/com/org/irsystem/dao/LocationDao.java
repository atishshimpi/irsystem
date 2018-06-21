package com.org.irsystem.dao;

import java.util.List;

import com.org.irsystem.model.Location;

public interface LocationDao {
 
	public Location getLocationById(Long id);
	public List<Location> listLocations();
	public Location getSubLocationByLocationName(String locationName);
	public void addLocation(Location location);

}