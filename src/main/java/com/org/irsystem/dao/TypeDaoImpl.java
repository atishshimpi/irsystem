package com.org.irsystem.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.irsystem.model.Location;
import com.org.irsystem.model.Type;
 
@Repository
public class TypeDaoImpl extends AbstractDao<Integer, Type> implements TypeDao {
     
    private static final Logger logger = LoggerFactory.getLogger(TypeDaoImpl.class);
 
    @Autowired
    private SessionFactory sessionFactory;
      
    @SuppressWarnings("unchecked")
    @Override
    public List<Type> listTypes() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Type> typeList = session.createQuery("from Type").list();
        for(Type type : typeList){
            logger.info("Type List::"+type);
        }
        return typeList;
    }
 
    @Override
    public Type getTypeById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();      
        Type type = (Type) session.load(Location.class, new Long(id));
        logger.info("Type loaded successfully, Person details="+type);
        return type;
    }

	

	@Override
	public void addType(Type type) {
		Session session = this.sessionFactory.getCurrentSession();
        session.save(type);
        logger.info("Type saved successfully, Location Details="+type);
		
	}
}