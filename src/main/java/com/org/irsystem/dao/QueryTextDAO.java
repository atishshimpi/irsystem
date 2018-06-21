package com.org.irsystem.dao;

import java.util.List;

import com.org.irsystem.model.QueryAttribute;
import com.org.irsystem.model.QueryText;

public interface QueryTextDAO {
 
	public QueryText findById(Long id);
	public void addQueryText(QueryText queryText);
    public void updateQueryText(QueryText queryText);
    public List<QueryText> listQueryTexts();
    public QueryText getQueryTextById(Long id);
    public void removeQueryText(Long id);
	public List<QueryAttribute> getQueryAttributesByQueryText(QueryText queryText);
    
}