package com.org.irsystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="User_Document_Token")
@Scope("session")
public class UserDocumentToken{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long tokenId;

	@NotEmpty
	@Column(name="token", nullable=false)
	private String token;

	@NotNull
	@Column(name="count", nullable=false)
	private Integer count;
	
	@JsonIgnore
	//@JsonBackReference(value="document")
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "document_id")  // it is column name to store document id
	private UserDocument userDocument;
		
	public Long getTokenId() {
		return tokenId;
	}

	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public UserDocument getUserDocument() {
		return userDocument;
	}

	public void setUserDocument(UserDocument userDocument) {
		this.userDocument = userDocument;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tokenId == null) ? 0 : tokenId.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UserDocumentToken))
			return false;
		UserDocumentToken other = (UserDocumentToken) obj;
		if (tokenId == null) {
			if (other.tokenId != null)
				return false;
		} else if (!tokenId.equals(other.tokenId))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + tokenId + ", token=" + token + ", count=" + count+"]";
	}
}
