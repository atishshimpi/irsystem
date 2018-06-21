package com.org.irsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.org.irsystem.model.Location;
@Service
public class InitLocationService implements ApplicationListener<ContextRefreshedEvent>{

	@Value(value="${hibernate_hbm2ddl_auto}")
	private String hbm2ddlauto;
	
	@Autowired
	private LocationService locationService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		
		System.out.println(hbm2ddlauto); 
		
		List<Location> locations = new ArrayList<Location>();
		Location location = null;
		if("create".equalsIgnoreCase(hbm2ddlauto)){
			
			
			//pune
			location = new Location("pune","pune");
			locations.add(location);
			
			location = new Location("pune","balewadi");
			locations.add(location);
			
			location = new Location("pune","kalewadi");
			locations.add(location);
			
			location = new Location("pune","kasarwadi");
			locations.add(location);
						
			location = new Location("pune","pimpri");
			locations.add(location);
			
			location = new Location("pune","hinjewadi");
			locations.add(location);
			
			location = new Location("pune","chinchwad");
			locations.add(location);
			
			location = new Location("pune","dapodi");
			locations.add(location);
			
			location = new Location("pune","sangvi");
			locations.add(location);
			
			location = new Location("pune","wakad");
			locations.add(location);
			
			location = new Location("pune","nigdi");
			locations.add(location);
			
			location = new Location("pune","akurdi");
			locations.add(location);
			
			
			//Mumbai
			location = new Location("mumbai","mumbai");
			locations.add(location);
			
			location = new Location("mumbai","chandanwadi");
			locations.add(location);
			
			location = new Location("mumbai","parel");
			locations.add(location);
			
			location = new Location("mumbai","santacruz");
			locations.add(location);
			
			location = new Location("mumbai","bandra");
			locations.add(location);
			
			location = new Location("mumbai","andheri");
			locations.add(location);
			
			location = new Location("mumbai","chembur");
			locations.add(location);
			
			location = new Location("mumbai","malad");
			locations.add(location);
			
			location = new Location("mumbai","kandivali");
			locations.add(location);
			
			location = new Location("mumbai","mulund");
			locations.add(location);
			
			
			for (Location location1 : locations) {
				locationService.add(location1);
			}
		}
	}
}
