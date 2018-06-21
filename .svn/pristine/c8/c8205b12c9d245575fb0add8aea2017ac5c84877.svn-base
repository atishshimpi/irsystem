package com.org.irsystem.service;

import java.util.List;

import com.org.irsystem.model.User;

public interface UserService {

	/**
	 * Retrieves all users
	 * 
	 * @return list of users
	 */
	public List<User> getAll();

	/**
	 * Retrieves a single user based on id
	 * 
	 * @param id the id of the user
	 * @return the user
	 */
	public User get(long id);

	/**
	 * Add a new user
	 * 
	 * @param user the new user
	 * @return true if successful
	 */
	public Boolean add(User user);

	/**
	 * Delete an existing user
	 * 
	 * @param user the existing user
	 * @return true if successful
	 */
	public Boolean delete(long id);

	/**
	 * Edit an existing user
	 * 
	 * @param user the existing user
	 * @return true if successful
	 */
	public Boolean edit(User user);
	
	
	public User isValidUser(User user);
	
	
	User findById(Long id);


}