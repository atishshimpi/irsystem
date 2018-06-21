package com.org.irsystem.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.org.irsystem.model.UserDocument;

@Component
public class ValueComparators {

	
	public static Map<UserDocument, Float> sortByComparator(Map<UserDocument, Float> unsortMap) {

		// Convert Map to List
		List<Map.Entry<UserDocument, Float>> list = 
			new LinkedList<Map.Entry<UserDocument, Float>>(unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<UserDocument, Float>>() {
			public int compare(Map.Entry<UserDocument, Float> o1,
                                           Map.Entry<UserDocument, Float> o2) {
				int val = -1;
				System.out.println("o1.getValue() "+o1.getValue());
				System.out.println("o2.getValue() "+o2.getValue());
				if(o1.getValue() == null || o2.getValue()==null)
					val = -1;
				else 
					val = (o2.getValue()).compareTo(o1.getValue());
				return val;
			}
		});

		// Convert sorted map back to a Map
		Map<UserDocument, Float> sortedMap = new LinkedHashMap<UserDocument, Float>();
		for (Iterator<Map.Entry<UserDocument, Float>> it = list.iterator(); it.hasNext();) {
			Map.Entry<UserDocument, Float> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	public static Map<String, Float> sortByStringComparator(Map<String, Float> unsortMap) {

		// Convert Map to List
		List<Map.Entry<String, Float>> list = 
			new LinkedList<Map.Entry<String, Float>>(unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, Float>>() {
			public int compare(Map.Entry<String, Float> o1,
                                           Map.Entry<String, Float> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// Convert sorted map back to a Map
		Map<String, Float> sortedMap = new LinkedHashMap<String, Float>();
		for (Iterator<Map.Entry<String, Float>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Float> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	public static void printMap(Map<UserDocument, Float> map) {
		for (Map.Entry<UserDocument, Float> entry : map.entrySet()) {
			System.out.println("[Key] : " + entry.getKey() 
                                      + " [Value] : " + entry.getValue());
		}
	}

	
}
