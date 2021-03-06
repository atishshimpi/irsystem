package com.org.irsystem.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.org.irsystem.model.FileShare;
import com.org.irsystem.model.UserDocument;
import com.org.irsystem.model.UserDocumentToken;

@Repository("userDocumentDao")
public class UserDocumentDaoImpl extends AbstractDao<Integer, UserDocument> implements UserDocumentDao{
	StringBuffer sb= null;
	@SuppressWarnings("unchecked")
	public List<UserDocument> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<UserDocument>)crit.list();
	}
	
	@Override
	public List<UserDocument> findAllByAttributes(String attributeKey, String attributeValue) {
		
		String columnName = null;
		if(attributeKey.equalsIgnoreCase("location"))
			columnName = "dealer_location";

		if(attributeKey.equalsIgnoreCase("type"))
			columnName = "product_type";

		if(attributeKey.equalsIgnoreCase("name"))
			columnName = "product_name";

		if(attributeKey.equalsIgnoreCase("price"))
			columnName = "product_price";

		if(attributeKey.equalsIgnoreCase("noofvisit"))
			columnName = "noOfVisit";

		if(attributeKey.equalsIgnoreCase("rating"))
			columnName = "product_rating";
		
		String queryString = "from UserDocument where "+columnName+" = :attribute_value";
		Query query = getSession().createQuery(queryString);
		query.setParameter("attribute_value",attributeValue);
		List<UserDocument> results = query.list();
		
		return results;
	}

	public void save(UserDocument document) {
		persist(document);
	}
	 
	public void saveUDT(UserDocumentToken documentToken) {
		getSession().save(documentToken);
	}

	
	public UserDocument findById(Long id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	public List<UserDocument> findAllByUserId(Long userId){
		Criteria crit = createEntityCriteria();
		Criteria userCriteria = crit.createCriteria("user");
		userCriteria.add(Restrictions.eq("id", userId));
		return (List<UserDocument>)crit.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UserDocument> findSharedFilesByUserId(Long userId){
		
		Criteria fileShareCriteria = createEntityCriteria(FileShare.class);
		fileShareCriteria.add(Restrictions.eq("sharedFileUserId", userId));
		List<FileShare> fileShares = fileShareCriteria.list();
		List<UserDocument> userDocuments = new ArrayList<>();

		for(FileShare fileShare : fileShares){
			Criteria userCriteria = createEntityCriteria();
			userCriteria.add(Restrictions.eq("user.id", fileShare.getLoginUserId())) ;	
			//userCriteria.add(Restrictions.eq("user.documentId", fileShare.getDocumentId())) ;
			userDocuments.addAll(userCriteria.list());
		}
		
		System.out.println("userDocuments"+userDocuments);
		return userDocuments;
	}

	
	public void deleteById(Long id) {
		UserDocument document =  getByKey(id);
		delete(document);
	}
}
