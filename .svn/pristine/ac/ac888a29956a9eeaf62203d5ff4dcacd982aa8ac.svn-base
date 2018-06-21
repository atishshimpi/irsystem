package com.org.irsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="csp", uniqueConstraints = { @UniqueConstraint( columnNames = { "FIRST_NAME", "LAST_NAME", "EMAIL" } ) })
public class CSP{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;
	
	
	@Column(name="FIRST_NAME", length=100)
	private String firstName;
	
	@Column(name="LAST_NAME", length=100)
	private String lastName;
	
	@Column(name="PASSWORD", length=100)
	private String password;

	@Column(name="EMAIL", length=200)
	private String email;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
