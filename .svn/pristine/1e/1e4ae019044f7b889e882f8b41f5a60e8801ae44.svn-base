package com.org.irsystem.model;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

public class FileBucket {

	MultipartFile file;
	
	@Column(name="description", length=255)
	private String productDescription;
	
	@Column(name="type", length=100, nullable=false)
	private String type;
	
	@Column(name="product_name", length=255)
	private String productName;

	@Column(name="product_type", length=255)
	private String productType;

	@Column(name="product_price", length=255)
	private Float productPrice;

	@Column(name="dealer_location", length=255)
	private String dealerLocation;

	@Column(name="noOfVisit", length=255)
	private Integer noOfVisit;

	@Column(name="product_rating", length=255)
	private Integer productRating;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
}