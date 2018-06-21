package com.org.irsystem.service;

import java.util.List;

import com.org.irsystem.model.UserDocument;

public interface UserDocumentService {

	UserDocument findById(Long id);

	List<UserDocument> findAll();
	
	List<UserDocument> findAllByUserId(Long id);
	
	void saveDocument(UserDocument document);
	
	void deleteById(Long id);

	List<UserDocument> findSharedFilesByUserId(Long userId);
	
	public List<UserDocument> findAllByAttributes(String attributeKey, String attributeValue);
	
	public List<UserDocument> getRecommendedDocuments(Long userId);
}
