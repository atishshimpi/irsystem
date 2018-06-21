package com.org.irsystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="content_attribute")
public class ContentAttribute  implements Comparable<ContentAttribute> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	private String name;
	
	private String keyword;

	private Float probability;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "QUERY_TEXT_ID")
	private QueryText queryText;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getProbability() {
		return probability;
	}

	public void setProbability(Float probability) {
		this.probability = probability;
	}

	public QueryText getQueryText() {
		return queryText;
	}

	public void setQueryText(QueryText queryText) {
		this.queryText = queryText;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}	
	
	
	
	@Override
	public int compareTo(ContentAttribute o) {
		if(o.getProbability()>this.getProbability())
			return 1;
		return 0;
	}
	
}
