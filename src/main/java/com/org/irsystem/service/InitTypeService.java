package com.org.irsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.org.irsystem.model.Type;
@Service
public class InitTypeService implements ApplicationListener<ContextRefreshedEvent>{

	@Value(value="${hibernate_hbm2ddl_auto}")
	private String hbm2ddlauto;
	
	@Autowired
	private TypeService typeService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		
		System.out.println(hbm2ddlauto); 
		
		List<Type> types = new ArrayList<Type>();
		Type typevalue = null;
		if("create".equalsIgnoreCase(hbm2ddlauto)){
			
			
			typevalue = new Type("Electronic");
			types.add(typevalue);
			
			typevalue = new Type("Home Furniture");
			types.add(typevalue);
			
			typevalue = new Type("Home Kitchen");
			types.add(typevalue);
			
			typevalue = new Type("Mens T-Shirt");
			types.add(typevalue);
						
			typevalue = new Type("Mens Shirt");
			types.add(typevalue);
			
			typevalue = new Type("Mens Watches");
			types.add(typevalue);
			
			typevalue = new Type("Mobile 2G 3G Smart Phone");
			types.add(typevalue);
			
			typevalue = new Type("Mobile 4G Smart Phone");
			types.add(typevalue);
			
			typevalue = new Type("Women Anarkali");
			types.add(typevalue);
			
			typevalue = new Type("Women Kurtis");
			types.add(typevalue);
			
			typevalue = new Type("Women Lehenga");
			types.add(typevalue);
			
			typevalue = new Type("Women Sarees");
			types.add(typevalue);
		
			typevalue = new Type("Women Footwear");
			types.add(typevalue);
			
			typevalue = new Type("Women Handbags");
			types.add(typevalue);
			
			typevalue = new Type("Women Westnerwear");
			types.add(typevalue);
			
			for (Type type : types) {
				typeService.add(type);
			}
		}
	}
}
