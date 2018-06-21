package com.org.irsystem;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.irsystem.model.Recommend;
import com.org.irsystem.model.User;
import com.org.irsystem.model.UserDocument;

@RestController
@RequestMapping("/ajax")
public class AjaxController {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@RequestMapping(value = { "/update-rating" }, consumes="application/json")
	public void updateDocumentRating(@RequestBody Recommend recommend, HttpServletRequest request){
		
		try {
			
				Long userId = (Long) request.getSession().getAttribute("userId");
				recommend.setUserId(userId);
				System.out.println("User iD "+userId);
				Session session = sessionFactory.openSession();
				Transaction transaction = session.beginTransaction();
				
				Recommend recommend2 = (Recommend) session.createQuery("from Recommend where userId =:userId and documentId=:documentId")
						.setParameter("userId", userId)
						.setParameter("documentId", recommend.getDocumentId())
						.uniqueResult();
				
				if(null != recommend2 ){
					recommend2.setRating(recommend.getRating());
					session.update(recommend2);	
				}else{
					session.save(recommend);
				}
				transaction.commit();
				
				
				
				// get all document with given document id
				Long ratingSum = (Long) session.createQuery("select sum(rating) from Recommend where documentId=:documentId group by documentId")
						.setParameter("documentId", recommend.getDocumentId())
						.uniqueResult();
				
				// get all documents
				@SuppressWarnings("unchecked")
				List<User> users = (List<User>) session.createQuery("from User where role=:userrole")
				.setParameter("userrole", "user")
				.list();
				
				// calculate avg
				Long avgRating = ratingSum/users.size();
				
				// update master table document
				 UserDocument document = (UserDocument) session.createQuery("from UserDocument where id=:documentId")
				.setParameter("documentId", recommend.getDocumentId())
				.uniqueResult();
				document.setProductRating(avgRating.intValue());
				
				// update document rating in master table "user_document"
				Transaction transaction2 = session.beginTransaction();
				session.update(document);
				transaction2.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = { "/update-visits" }, consumes="application/json")
	public void updateNoOfVisits(@RequestBody Recommend recommend, HttpServletRequest request){
		
		try {
				
				Long userId = (Long) request.getSession().getAttribute("userId");
				recommend.setUserId(userId);
				Session session = sessionFactory.openSession();
				Transaction transaction = session.beginTransaction();
				
				Recommend recommend2 = (Recommend) session.createQuery("from Recommend where userId =:userId and documentId=:documentId")
						.setParameter("userId", userId)
						.setParameter("documentId", recommend.getDocumentId())
						.uniqueResult();
				
				int noOfVisits;
				
				if(null != recommend2 ){
					if(null != recommend2.getNoOfVisits()){
						noOfVisits = recommend2.getNoOfVisits()+1;
						recommend2.setNoOfVisits(noOfVisits);
					}else{
						noOfVisits = 1;
						recommend2.setNoOfVisits(noOfVisits);
					}
					session.update(recommend2);	
				}else{
					userId = (long) request.getSession().getAttribute("userId");
					noOfVisits = 1;
					recommend.setNoOfVisits(noOfVisits);
					recommend.setUserId(userId);
					session.save(recommend);
				}
				transaction.commit();
				
				
				// update master table document
				 UserDocument document = (UserDocument) session.createQuery("from UserDocument where id=:documentId")
				.setParameter("documentId", recommend.getDocumentId())
				.uniqueResult();
				document.setNoOfVisit(noOfVisits);
				
				// update document rating in master table "user_document"
				Transaction transaction2 = session.beginTransaction();
				session.update(document);
				transaction2.commit();
				
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
