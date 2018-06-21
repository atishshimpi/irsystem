package com.org.irsystem.dao;

import java.util.List;

import com.org.irsystem.model.Recommend;
import com.org.irsystem.model.UserDocument;

public interface RecommendDao {

	List<Recommend> findAll();
	
	Recommend findById(Long id);
	
	void save(Recommend document);
	
	void deleteById(Long id);
	
	void deleteByDocumentId(Long id);
	
	public List<Recommend> findAllByUserId(Long userId);
	
	public List<UserDocument> findRecommendUserDocumentByUserId(Long userId);

	public int getRatingByProductId(Long userId, Long productId);
	
	public double getAvgRatingByUserId(Long userId);

	int getUserRatingByProductId(Long userId, Long productId);
	
}
