package com.org.irsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="TYPE_ID")
	private Integer typeId;

	@Column(name = "TYPE_NAME")
	private String typeName;

	public Type() {
		super();
	}

	public Type(String typeName) {
		this.typeName = typeName;
	}

	public Integer getLocationId() {
		return typeId;
	}

	public void setLocationId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
