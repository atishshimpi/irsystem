package com.org.irsystem.service;

import java.util.List;

import com.org.irsystem.model.Type;

public interface TypeService {

	/**
	 * Retrieves all users
	 * 
	 * @return list of users
	 */
	public List<Type> getAll();

	/**
	 * Retrieves a single user based on id
	 * 
	 * @param id the id of the user
	 * @return the user
	 */
	public Type get(long id);
	
	/**
	 * Add a new location
	 * 
	 * @param user the new user
	 * @return true if successful
	 */
	public Boolean add(Type type);

}