package com.org.irsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="LOCATION_ID")
	private Integer locationId;

	@Column(name = "LOCATION_NAME")
	private String locationName;

	@Column(name = "SUB_LOCATION_NAME")
	private String subLocationName;
	
	public Location() {
		super();
	}

	public Location(String locationName, String subLocationName) {
		super();
		this.locationName = locationName;
		this.subLocationName = subLocationName;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getSubLocationName() {
		return subLocationName;
	}

	public void setSubLocationName(String subLocationName) {
		this.subLocationName = subLocationName;
	}
}
