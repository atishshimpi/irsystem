package com.org.irsystem.util;

import java.util.TreeMap;

import com.org.irsystem.model.UserDocument;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserDocument document1 = new UserDocument();
		document1.setProductPrice(1.0f);
		
		UserDocument document2 = new UserDocument();
		document2.setProductPrice(2.0f);
		
		UserDocument document3 = new UserDocument();
		document3.setProductPrice(3.0f);
		
		
		TreeMap<UserDocument, Float> tm = new TreeMap<>(new SortUserDocumentByPrice());
		tm.put(document3, 3f);
		tm.put(document2, 2f);
		tm.put(document1, 1f);		
		
		
		System.out.println(tm);
		

	}

}
