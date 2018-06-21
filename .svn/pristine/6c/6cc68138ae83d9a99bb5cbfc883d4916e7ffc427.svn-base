package com.org.irsystem.util;

import java.util.Comparator;

import com.org.irsystem.model.UserDocument;

public class SortUserDocumentByPrice implements Comparator<UserDocument> {

	@Override
	public int compare(UserDocument o1, UserDocument o2) {
		
		if(o1.getProductPrice()>o2.getProductPrice()){
			return 1;
		}else{
			return -1;
		}
	}	
}
