package com.org.irsystem.dao;

import java.util.List;

import com.org.irsystem.model.UserDocument;
import com.org.irsystem.model.UserDocumentToken;

public interface UserDocumentDao {

	List<UserDocument> findAll();
	
	UserDocument findById(Long id);
	
	void save(UserDocument document);
	
	List<UserDocument> findAllByUserId(Long userId);
	
	void deleteById(Long id);
	
	public List<UserDocument> findSharedFilesByUserId(Long userId);
	
	public void saveUDT(UserDocumentToken documentToken);

	public List<UserDocument> findAllByAttributes(String attributeKey, String attributeValue);
	
}
