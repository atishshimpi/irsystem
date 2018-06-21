package com.org.irsystem.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="query_text")
public class QueryText {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="QUERY_TEXT_ID")
	private long id;
	
	@Column(length=4000)
	private String text;


	@OneToMany(mappedBy = "queryText", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<QueryAttribute> queryAttributes;
	
	@OneToMany(mappedBy = "queryText", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ContentAttribute> contentAttributes;
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public List<QueryAttribute> getQueryAttributes() {
		return queryAttributes;
	}


	public void setQueryAttributes(List<QueryAttribute> queryAttributes) {
		this.queryAttributes = queryAttributes;
	}


	public List<ContentAttribute> getContentAttributes() {
		return contentAttributes;
	}


	public void setContentAttributes(List<ContentAttribute> contentAttributes) {
		this.contentAttributes = contentAttributes;
	}	
	
	
}
