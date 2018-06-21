package com.org.irsystem.service;

import java.util.List;

import com.org.irsystem.model.ContentAttribute;
import com.org.irsystem.model.QueryAttribute;
import com.org.irsystem.model.QueryText;

public interface QueryContentValueService {

	public void getQueryContentValueDocuments(QueryText queryText);
	
	public List<QueryAttribute> getQueryValueAttributes(QueryText queryText);
	
	public List<ContentAttribute> getContentValueAttributes(QueryText queryText);
	
	public Float getContentValueBar(QueryText queryText);
	
	
	
}
