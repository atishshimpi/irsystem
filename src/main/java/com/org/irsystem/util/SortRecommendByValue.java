package com.org.irsystem.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.org.irsystem.model.Recommend;
import com.org.irsystem.model.UserDocument;

@Component
public class SortRecommendByValue {

	
	public static Map<Recommend, Double> sortByComparator(Map<Recommend, Double> unsortMap) {

		// Convert Map to List
		List<Map.Entry<Recommend, Double>> list = 
			new LinkedList<Map.Entry<Recommend, Double>>(unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<Recommend, Double>>() {
			public int compare(Map.Entry<Recommend, Double> o1,
                                           Map.Entry<Recommend, Double> o2) {
				
				if(o1.getValue()<=o2.getValue()){
					return 1;
				}else{
					return -1;
				}
				
				//return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// Convert sorted map back to a Map
		Map<Recommend, Double> sortedMap = new LinkedHashMap<Recommend, Double>();
		for (Iterator<Map.Entry<Recommend, Double>> it = list.iterator(); it.hasNext();) {
			Map.Entry<Recommend, Double> entry = it.next();
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
