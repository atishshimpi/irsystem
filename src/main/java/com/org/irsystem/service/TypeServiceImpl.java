/**
 * 
 */
package com.org.irsystem.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.irsystem.dao.TypeDao;
import com.org.irsystem.model.Type;

/**
 * Handles CRUD services for users
 * 
 */
@Service("typeService")
@Transactional
public class TypeServiceImpl implements TypeService{
 	
	@Autowired
	private TypeDao dao;
	
	protected static Logger logger = Logger.getLogger("service");

	public TypeServiceImpl() {

	}
	
	public List<Type> getAll() {
		logger.debug("Retrieving all users");

		List<Type> locations = dao.listTypes();
		return locations;
	}
	
	public Type get(long id) {
		logger.debug("Retrieving an existing user");
		
		return  dao.getTypeById(Long.valueOf(id));
		
	}
	
	
	@Override
	public Boolean add(Type type) {
		logger.debug("Adding a new user");
		
		try {
			dao.addType(type);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
