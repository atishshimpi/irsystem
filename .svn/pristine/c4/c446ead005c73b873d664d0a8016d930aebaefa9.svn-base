package com.org.irsystem.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.irsystem.model.Location;
 
@Repository
public class LocationDaoImpl extends AbstractDao<Integer, Location> implements LocationDao {
     
    private static final Logger logger = LoggerFactory.getLogger(LocationDaoImpl.class);
 
    @Autowired
    private SessionFactory sessionFactory;
      
    @SuppressWarnings("unchecked")
    @Override
    public List<Location> listLocations() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Location> locationList = session.createQuery("from Location").list();
        for(Location location : locationList){
            logger.info("Location List::"+location);
        }
        return locationList;
    }
 
    @Override
    public Location getLocationById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();      
        Location location = (Location) session.load(Location.class, new Long(id));
        logger.info("Person loaded successfully, Person details="+location);
        return location;
    }

	@Override
	public Location getSubLocationByLocationName(String locationName) {
		 Session session = this.sessionFactory.getCurrentSession();      
	     		 
		 Location location = (Location) session.createQuery("from Location where subLocationName=:sub_location_name")
				 .setParameter("sub_location_name", locationName)
				 .uniqueResult();
		 return location;
	}

	@Override
	public void addLocation(Location location) {
		Session session = this.sessionFactory.getCurrentSession();
        session.save(location);
        logger.info("Location saved successfully, Location Details="+location);
		
	}
}