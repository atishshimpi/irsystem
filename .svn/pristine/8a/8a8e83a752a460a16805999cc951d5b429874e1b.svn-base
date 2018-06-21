package com.org.irsystem.dao;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.irsystem.model.QueryAttribute;
import com.org.irsystem.model.QueryText;
import com.org.irsystem.model.User;
 
@Repository
public class QueryTextDAOImpl extends AbstractDao<Integer, User> implements QueryTextDAO {
     
    private static final Logger logger = LoggerFactory.getLogger(QueryTextDAOImpl.class);
 
    @Autowired
    private SessionFactory sessionFactory;
     
    /*public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }*/
 
    @Override
    public void addQueryText(QueryText queryText) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(queryText);
        logger.info("Person saved successfully, Person Details="+queryText);
    }
 
    @Override
    public void updateQueryText(QueryText queryText) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(queryText);
        logger.info("Person updated successfully, Person Details="+queryText);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<QueryText> listQueryTexts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<QueryText> queryTextList = session.createQuery("from QueryText").list();
        for(QueryText queryText : queryTextList){
            logger.info("User List::"+queryText);
        }
        return queryTextList;
    }
 
    @Override
    public QueryText getQueryTextById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();      
        QueryText queryText = (QueryText) session.load(QueryText.class, new Long(id));
        logger.info("Person loaded successfully, Person details="+queryText);
        return queryText;
    }
 
    @Override
    public void removeQueryText(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        QueryText queryText = (QueryText) session.load(QueryText.class, new Long(id));
        if(null != queryText){
            session.delete(queryText);
        }
        logger.info("Person deleted successfully, person details="+queryText);
    }

	@Override
	public QueryText findById(Long id) {
		//QueryText queryText = getByKey(id);
		QueryText queryText = null;
		return queryText ;
	}

	@Override
	public List<QueryAttribute> getQueryAttributesByQueryText(QueryText queryText) {
		
		Session session = this.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(QueryAttribute.class);
		criteria.add(Restrictions.eq("queryText",queryText));
		criteria.add(Restrictions.eq("keyword",queryText));
		criteria.addOrder(Order.desc("probability"));
		List<QueryAttribute> attributes = (List<QueryAttribute>) criteria.list(); 
		return attributes;
	}
}