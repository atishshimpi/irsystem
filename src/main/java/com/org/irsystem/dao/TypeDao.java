package com.org.irsystem.dao;

import java.util.List;

import com.org.irsystem.model.Type;

public interface TypeDao {
 
	public Type getTypeById(Long id);
	public List<Type> listTypes();
	public void addType(Type type);

}