package com.org.irsystem.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.org.irsystem.model.Recommend;
import com.org.irsystem.model.UserDocument;

@Repository("recommendDao")
public class RecommendDaoImpl extends AbstractDao<Integer, Recommend> implements RecommendDao{
	StringBuffer sb= null;
	UserDocumentDao userDocumentDao;
	
	@SuppressWarnings("unchecked")
	public List<Recommend> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<Recommend>)crit.list();
	}


	public void save(Recommend recommend) {
		persist(recommend);
	}
	 
	public Recommend findById(Long id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	public List<Recommend> findAllByUserId(Long userId){
		Criteria recommendCriteria = createEntityCriteria(Recommend.class);
		recommendCriteria.add(Restrictions.eq("userId", userId));
		return (List<Recommend>)recommendCriteria.list();
	}
	
	public void deleteById(Long id) {
		Recommend document =  getByKey(id);
		delete(document);
	}


	@Override
	public List<UserDocument> findRecommendUserDocumentByUserId(Long userId) {

		// get all document id's
		
		List<Recommend> recommends = findAllByUserId(userId);
		List<Long> docIds = new ArrayList<Long>();
		
		for(Recommend recommendObj : recommends){
			docIds.add(recommendObj.getDocumentId());
		}
		
		System.out.println("docIds: "+docIds);
		Session session = getSession();
		Query query = session.createQuery("from UserDocument ud where ud.id in :documentIds");
		query.setParameterList("documentIds", docIds);
		
		@SuppressWarnings("unchecked")
		List<UserDocument> docs = (List<UserDocument>) query.list();
		
		return docs;
	}


	@Override
	public int getRatingByProductId(Long userId, Long productId) {

		Recommend recommend =null;
		
		Session session = getSession();
		Query query = session.createQuery("from Recommend r where r.userId = :userId and r.documentId = :documentId");
		query.setParameter("userId", userId);
		query.setParameter("documentId", productId);
		recommend = (Recommend) query.uniqueResult();
		
		if(recommend!=null)
			return recommend.getRating();
		
		return 0;
	}
	
	@Override
	public int getUserRatingByProductId(Long userId, Long productId) {

		UserDocument document =null;
		
		Session session = getSession();
		Query query = session.createQuery("from UserDocument ud where ud.user.id = :userId and ud.id = :documentId");
		query.setParameter("userId", userId);
		query.setParameter("documentId", productId);
		document = (UserDocument) query.uniqueResult();
		
		if(document!=null)
			return document.getProductRating();
		
		return 0;
	}


	@Override
	public double getAvgRatingByUserId(Long userId) {
		
		double doubleid = 0;
		
		try {
			Criteria criteria = createEntityCriteria(Recommend.class)
					.setProjection(Projections.avg("rating"))
					.add(Restrictions.eq("userId", userId));
			doubleid = (double) criteria.uniqueResult();	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
				
	
		return doubleid;
	}


	@Override
	public void deleteByDocumentId(Long documentId) {
		
		try {
			
			Recommend document =null;
			
			Session session = openSession();
			Query query = session.createQuery("from Recommend r where r.documentId = :documentId");
			query.setParameter("documentId", documentId);
			document = (Recommend) query.uniqueResult();
			
			Transaction tx = session.beginTransaction();
			session.delete(document);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
}
