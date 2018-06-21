/**
 * 
 */
package com.org.irsystem.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.irsystem.dao.QueryTextDAO;
import com.org.irsystem.model.QueryAttribute;
import com.org.irsystem.model.QueryText;
import com.org.irsystem.model.User;

/**
 * Handles CRUD services for users
 * 
 */
@Service("queryTextService")
@Transactional
public class QueryTextServiceImpl implements QueryTextService   {
 	
	@Autowired
	private QueryTextDAO dao;
	
	protected static Logger logger = Logger.getLogger("service");

	public QueryTextServiceImpl() {

	}
	
	public List<QueryText> getAll() {
		logger.debug("Retrieving all users");

		List<QueryText> users = dao.listQueryTexts();
		return users;
	}
	
	public QueryText get(long id) {
		logger.debug("Retrieving an existing user");
		
		return  dao.getQueryTextById(id);
		
	}

	public Boolean add(QueryText queryText) {
		logger.debug("Adding a new user");
		
		try {
			dao.addQueryText(queryText); 
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean delete(long id) {
		logger.debug("Deleting an existing user");
		
		try {
			// Retrieve id to delete
			dao.removeQueryText(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
 	
	public Boolean edit(QueryText queryText) {
		logger.debug("Editing an existing user");
		
		try {
			dao.updateQueryText(queryText);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public QueryText isValidQueryText(QueryText queryText) {

		/*List<User> users = dao.listUsers();
		
		for (User user2 : users) {
			if(user2.getEmail().equalsIgnoreCase(user.getEmail()) && user2.getPassword().equalsIgnoreCase(user.getPassword()) && user2.getRole().equalsIgnoreCase(user.getRole())){
				return user2;
			}
		}*/
		
		return null;
	}

	@Override
	public QueryText findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<QueryAttribute> getQueryAttributesByQueryText(QueryText queryText) {
		
		return dao.getQueryAttributesByQueryText(queryText);
	}
}
