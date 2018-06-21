package com.org.irsystem.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USER_DOCUMENT")
public class UserDocument implements Comparable<UserDocument> {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name="name", length=500, nullable=false)
	private String name;
	
	@Column(name="description", length=4000)
	private String productDescription;
	
	@Column(name="type", length=500, nullable=false)
	private String type;
	
	@Column(name="product_name", length=500)
	private String productName;

	@Column(name="product_type", length=500)
	private String productType;

	@Column(name="product_price", length=500)
	private Float productPrice;

	@Column(name="dealer_location", length=500)
	private String dealerLocation;

	@Column(name="noOfVisit", length=255)
	private Integer noOfVisit;

	@Column(name="product_rating", length=255)
	private Integer productRating;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(name="content", nullable=false)
	//@Type(type="encryptedBinaryType")
	private byte[] content;

	@JsonIgnore
	//@JsonBackReference(value="document")
	@ManyToOne(optional = false)
	@JoinColumn(name = "USER_ID")
	private User user;
	

	@OneToMany(targetEntity=UserDocumentToken.class,mappedBy = "userDocument", cascade = CascadeType.ALL)
    private Set<UserDocumentToken> documentTokens = new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}

	public String getDealerLocation() {
		return dealerLocation;
	}

	public void setDealerLocation(String dealerLocation) {
		this.dealerLocation = dealerLocation;
	}

	public Integer getNoOfVisit() {
		return noOfVisit;
	}

	public void setNoOfVisit(Integer noOfVisit) {
		this.noOfVisit = noOfVisit;
	}

	public Integer getProductRating() {
		return productRating;
	}

	public void setProductRating(Integer productRating) {
		this.productRating = productRating;
	}

	public Set<UserDocumentToken> getDocumentTokens() {
		return documentTokens;
	}

	public void setDocumentTokens(Set<UserDocumentToken> documentTokens) {
		this.documentTokens = documentTokens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UserDocument))
			return false;
		UserDocument other = (UserDocument) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		
		
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		
		
		if (productPrice == null) {
			if (other.productPrice != null)
				return false;
		} else if (!productPrice.equals(other.productPrice))
			return false;
		
		
		
		if (dealerLocation == null) {
			if (other.dealerLocation != null)
				return false;
		} else if (!dealerLocation.equals(other.dealerLocation))
			return false;
		
		
		if (dealerLocation == null) {
			if (other.dealerLocation != null)
				return false;
		} else if (!dealerLocation.equals(other.dealerLocation))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return getProductDescription()+" "+getProductName()+" "+getProductType()+" "+getProductPrice()+" "+getDealerLocation();
	}

	@Override
	public int compareTo(UserDocument arg0) {
			return getName().compareTo(arg0.getName());
	}


	
}
