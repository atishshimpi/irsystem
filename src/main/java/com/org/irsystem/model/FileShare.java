package com.org.irsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FileShare")
public class FileShare {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="LOGIN_USER_ID")
	private Long loginUserId;
	
	@Column(name="SHARED_FILE_USER_ID")
	private Long sharedFileUserId;
	
	@Column(name="DOCUMENT_ID")
	private Long documentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	}

	public Long getSharedFileUserId() {
		return sharedFileUserId;
	}

	public void setSharedFileUserId(Long sharedFileUserId) {
		this.sharedFileUserId = sharedFileUserId;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
}
