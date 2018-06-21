package com.org.irsystem.model;

public class UserDocumentPair {

	private UserDocument document1;
	private UserDocument document2;
	
	public UserDocumentPair(UserDocument document1, UserDocument document2) {
		super();
		this.document1 = document1;
		this.document2 = document2;
	}
	public UserDocument getDocument1() {
		return document1;
	}
	public void setDocument1(UserDocument document1) {
		this.document1 = document1;
	}
	public UserDocument getDocument2() {
		return document2;
	}
	public void setDocument2(UserDocument document2) {
		this.document2 = document2;
	}
	
	
	

	

}
