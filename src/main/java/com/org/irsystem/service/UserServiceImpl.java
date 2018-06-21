/**
 * 
 */
package com.org.irsystem.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.irsystem.dao.UserDAO;
import com.org.irsystem.model.User;

/**
 * Handles CRUD services for users
 * 
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService   {
 	
	@Autowired
	private UserDAO dao;
	
	protected static Logger logger = Logger.getLogger("service");

	public UserServiceImpl() {

	}
	
	public List<User> getAll() {
		logger.debug("Retrieving all users");

		List<User> users = dao.listUsers();
		return users;
	}
	
	public User get(long id) {
		logger.debug("Retrieving an existing user");
		
		return  dao.getUserById(Long.valueOf(id));
		
	}

	public Boolean add(User user) {
		logger.debug("Adding a new user");
		
		try {
			dao.addUser(user);
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
			dao.removeUser(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
 	
	public Boolean edit(User user) {
		logger.debug("Editing an existing user");
		
		try {
			dao.updateUser(user);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public User isValidUser(User user) {

		List<User> users = dao.listUsers();
		
		for (User user2 : users) {
			if(user2.getEmail().equalsIgnoreCase(user.getEmail()) && user2.getPassword().equalsIgnoreCase(user.getPassword()) && user2.getRole().equalsIgnoreCase(user.getRole())){
				return user2;
			}
		}
		
		return null;
	}

	@Override
	public User findById(Long id) {
		return dao.findById(id);
	}
}
