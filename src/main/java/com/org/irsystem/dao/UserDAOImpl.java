package com.org.irsystem.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.irsystem.model.User;
 
@Repository
public class UserDAOImpl extends AbstractDao<Integer, User> implements UserDAO {
     
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
 
    @Autowired
    private SessionFactory sessionFactory;
     
    /*public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }*/
 
    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(user);
        logger.info("Person saved successfully, Person Details="+user);
    }
 
    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("Person updated successfully, Person Details="+user);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();
        for(User user : userList){
            logger.info("User List::"+user);
        }
        return userList;
    }
 
    @Override
    public User getUserById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();      
        User user = (User) session.load(User.class, new Long(id));
        logger.info("Person loaded successfully, Person details="+user);
        return user;
    }
 
    @Override
    public void removeUser(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Long(id));
        if(null != user){
            session.delete(user);
        }
        logger.info("Person deleted successfully, person details="+user);
    }

	@Override
	public User findById(Long id) {
		User user = getByKey(id);
		return user;
	}

	@Override
    @SuppressWarnings("unchecked")
	public List<User> listUserRoleUsers() {
        Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = (List<User>) session.createQuery("from User where role =:user")
		.setParameter("user","user")
		.list();
        for(User user : userList){
            logger.info("User List::"+user);
        }
        return userList;
    }
}