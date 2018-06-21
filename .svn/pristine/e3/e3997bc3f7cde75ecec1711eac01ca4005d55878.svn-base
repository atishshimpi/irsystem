package com.org.irsystem.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.irsystem.model.FileShare;

@Repository
public class FileShareDaoImpl implements FileShareDao {

	 private static final Logger logger = LoggerFactory.getLogger(FileShareDaoImpl.class);
	 
     @Autowired
     private SessionFactory sessionFactory;
	    
	@Override
	public void addShareFile(FileShare sf) {
        Session session = this.sessionFactory.openSession();
        session.save(sf);
        logger.info("Share file saved successfully, Tpa Details="+sf);
    }

	@Override
	public void updateShareFile(FileShare sf) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(sf);
        logger.info("Share file updated successfully, Person Details="+sf);
    }

	@Override
	public List<FileShare> listShareFiles() {
        Session session = this.sessionFactory.openSession();
        @SuppressWarnings("unchecked")
		List<FileShare> sfList = session.createQuery("from FileShare").list();
        for(FileShare sf : sfList){
            logger.info("CSP List::"+sf);
        }
        return sfList;
    }

	@Override
	public FileShare getShareFileById(Long id) {
        Session session = this.sessionFactory.openSession();
        FileShare sf = (FileShare) session.load(FileShare.class, new Long(id));
        logger.info("Share file loaded successfully, Tpa details="+sf);
        return sf;
    }

	@Override
	public void removeShareFile(Long id) {
        Session session = this.sessionFactory.openSession();
        FileShare sf = (FileShare) session.load(FileShare.class, new Long(id));
        if(null != sf){
            session.delete(sf);
        }
        logger.info("Share file deleted successfully, person details="+sf);
    }

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
