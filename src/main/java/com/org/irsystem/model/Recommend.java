package com.org.irsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="recommend", uniqueConstraints = { @UniqueConstraint( columnNames = { "USER_ID", "DOCUMENT_ID" } ) })
public class Recommend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	Long id;
	
	@Column(name="USER_ID")
	Long userId;
	
	@Column(name="DOCUMENT_ID")
	Long documentId;
	
	@Column(name="NO_oF_VISITS")
	Integer noOfVisits;
	
	@Column(name="RATING")
	Integer rating;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Integer getNoOfVisits() {
		return noOfVisits;
	}

	public void setNoOfVisits(Integer noOfVisits) {
		this.noOfVisits = noOfVisits;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	
}
