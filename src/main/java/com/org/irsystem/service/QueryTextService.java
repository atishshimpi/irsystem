package com.org.irsystem.service;

import java.util.List;

import com.org.irsystem.model.QueryAttribute;
import com.org.irsystem.model.QueryText;
import com.org.irsystem.model.User;

public interface QueryTextService {

	/**
	 * Retrieves all users
	 * 
	 * @return list of users
	 */
	public List<QueryText> getAll();

	/**
	 * Retrieves a single user based on id
	 * 
	 * @param id the id of the user
	 * @return the user
	 */
	public QueryText get(long id);

	/**
	 * Add a new user
	 * 
	 * @param user the new user
	 * @return true if successful
	 */
	public Boolean add(QueryText queryText);

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
	public Boolean edit(QueryText queryText);
	
	
	public QueryText isValidQueryText(QueryText queryText);
	
	
	QueryText findById(Long id);
	
	public List<QueryAttribute> getQueryAttributesByQueryText(QueryText queryText) ;


}