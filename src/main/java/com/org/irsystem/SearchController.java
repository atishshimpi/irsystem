package com.org.irsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.org.irsystem.model.QueryAttribute;
import com.org.irsystem.model.QueryDetail;
import com.org.irsystem.model.QueryText;
import com.org.irsystem.model.User;
import com.org.irsystem.model.UserDocument;
import com.org.irsystem.service.QueryContentValueService;
import com.org.irsystem.service.QueryTextService;
import com.org.irsystem.service.SearchService;
import com.org.irsystem.service.UserDocumentService;
import com.org.irsystem.service.UserService;
import com.org.irsystem.util.ValueComparators;


@Controller
public class SearchController {

	@Resource(name = "userService")
	private UserService userService;
	
	@Resource
	private UserDocumentService userDocumentService;
	
	@Autowired
	private SearchService searchService;	
	
	@Autowired
	private QueryContentValueService queryContentValueService;
	
	QueryDetail queryDetails = null;
	
	@Autowired
	private QueryTextService queryTextService;
	
	@RequestMapping(value = "/search/displayQueryValue", method = RequestMethod.GET)
    public String displayQueryValue(HttpServletRequest request, ModelMap model) {
    	
		QueryText queryText = new QueryText();
		model.addAttribute("queryText", queryText);
		User user = (User)request.getSession().getAttribute("user");
 	    List<UserDocument> userDocuments = userDocumentService.getRecommendedDocuments(user.getId());
    	model.addAttribute("userDocuments", userDocuments);
    	model.addAttribute("user", user);
    	return "user_query_value";

    }
	
	@RequestMapping(value = "/search/queryvalue", method = RequestMethod.POST)
	public String serchQueryValue(@ModelAttribute("queryText") QueryText queryText, ModelMap model){
		long st = System.currentTimeMillis();
		Map<UserDocument,Float> userDocuments = searchService.getQueryValueDocuments(queryText);
		model.addAttribute("userDocuments", userDocuments.keySet());
		long et = System.currentTimeMillis();
		System.out.println("#### Time for Query Value "+(et-st));
		return "user_query_value_result";
	}
	
	
	@RequestMapping(value = "/search/displayContentValue", method = RequestMethod.GET)
    public String displayContentValue(HttpServletRequest request, ModelMap model) {
		
		QueryText queryText = new QueryText();
		model.addAttribute("queryText", queryText);
		
		User user = (User)request.getSession().getAttribute("user");
 	    List<UserDocument> userDocuments = userDocumentService.getRecommendedDocuments(user.getId());
    	model.addAttribute("userDocuments", userDocuments);
    	model.addAttribute("user", user);
    	return "user_content_value";

    }
	
	@RequestMapping(value = "/search/contentvalue", method = RequestMethod.POST)
	public String serchContentValue(@ModelAttribute("queryText") QueryText queryText, ModelMap model){
		long st = System.currentTimeMillis();
		Map<UserDocument,Float> sortedMap = searchService.getContentValueDocuments(queryText);
		System.out.println("--------------------- content value ------------------");
		for(Map.Entry<UserDocument, Float> entry : sortedMap.entrySet()){
			System.out.println("Name : "+entry.getKey().getProductName()+" Formula Result :  "+entry.getValue());
		}
		
		long et = System.currentTimeMillis();
		System.out.println("#### Time for Content Value "+(et-st));
		List<UserDocument> userDocument = new ArrayList<>(sortedMap.keySet());
		Collections.reverse(userDocument);
		model.addAttribute("userDocuments",userDocument);
		
		return "user_content_value_result";
	}
	
	
	@RequestMapping(value = "/search/displayQueryContentValue", method = RequestMethod.GET)
    public String displayLoginUser(HttpServletRequest request, ModelMap model) {
 
		QueryText queryText = new QueryText();
		model.addAttribute("queryText", queryText);
		
		User user = (User)request.getSession().getAttribute("user");
 	    List<UserDocument> userDocuments = userDocumentService.getRecommendedDocuments(user.getId());
    	model.addAttribute("userDocuments", userDocuments);
    	model.addAttribute("user", user);
    	return "user_qc_value";

    }
	
	@RequestMapping(value = "/search/querycontentvalue", method = RequestMethod.POST)
	public String serchQueryContentValue(@ModelAttribute("queryText") QueryText queryText, ModelMap model){
		
		long st = System.currentTimeMillis();
		///----------------------------------------------------------------- QV
		// Natural sorting order by comparator
		Map<String, QueryDetail> QVMap = new TreeMap<>();
		Map<String,Integer> CVCountMap = new LinkedHashMap<>();
		Map<String,Float> CVMap = new LinkedHashMap<>();
		Map<String,Integer> CVBarCountMap = new LinkedHashMap<>();
		Map<String,Float> CVBarMap = new LinkedHashMap<>();
		
		
		Map<UserDocument, Float> userDocuments = new LinkedHashMap<>();
		List<QueryAttribute> attributes = new ArrayList<>();
		// global declaration
		List<UserDocument> result = new ArrayList<UserDocument>();
		List<UserDocument> allDocuments = userDocumentService.findAll();

		// QueryText 
		// Location:Pune, Type:Mobile
		// Price:1000, Location:Mumbai

		// split the query text line by new line \n
		String[] queryTexts = queryText.getText().split("\n");

		if (queryTexts != null && queryTexts.length > 0) {

			for (String text : queryTexts) {

				// get all attributes from query text splitting by comma
				// Location:Pune
				String[] attributeValues = text.split(",");

				// Iterate over each attribute
				for (String attributeValue : attributeValues) {
					
					if(!attributeValue.isEmpty() && attributeValue.length()>0){
						// split attribute into array [Location,Pune]
						String[] attributeArr = attributeValue.split(":");
						
						if(attributeArr.length>0 && attributeArr[0]!=null && !attributeArr[0].isEmpty() ){
							// count the number of occurrences of each attribute
							// e.g Location:Pune and Location:Mumbai so location occurrences are 2
							if (null != QVMap && null != QVMap.get(attributeArr[0])) {
								queryDetails = new QueryDetail();
								queryDetails = QVMap.get(attributeArr[0]); // 
								queryDetails.setProbability(queryDetails.getProbability() + 1);
								queryDetails.getValues().add(attributeArr[1]);
								QVMap.put(attributeArr[0], queryDetails);
							} else {
								
								if(attributeArr.length>1){
									
									if(attributeArr[1]!=null && !attributeArr[1].isEmpty()){
										queryDetails = new QueryDetail();
										queryDetails.setProbability(1.0f);
										queryDetails.getValues().add(attributeArr[1]);
										QVMap.put(attributeArr[0], queryDetails);	
									}
									
								}
							}
						}

						// Content value
						List<UserDocument> attributePresentInDocuments = userDocumentService.findAllByAttributes(attributeArr[0], attributeArr[1]);//total number of documents containing location value
						Float avg = (float) ((Float.valueOf(attributePresentInDocuments.size())+1f)/(Float.valueOf(allDocuments.size())+Float.valueOf(allDocuments.size())+1f));
						// allDocuments.size() Attribute (location) size
						
						if(CVCountMap!=null){
							if(CVCountMap.containsKey(attributeArr[0])){
								int cnt = CVCountMap.get(attributeArr[0]);
								CVCountMap.put(attributeArr[0], cnt+1);
								Float temp = CVMap.get(attributeArr[0]);
								
								CVMap.put(attributeArr[0], (temp+avg));

							}else{
								CVCountMap.put(attributeArr[0], 1);
								CVMap.put(attributeArr[0], avg);

							}
						}
						
						
						// cv bar
						
						Float cvbarAvg = (float) (((Float.valueOf(allDocuments.size())-Float.valueOf(attributePresentInDocuments.size()))+1f)/((Float.valueOf(allDocuments.size()) +Float.valueOf(allDocuments.size()))+1f));
						
						if(CVBarCountMap!=null){
							if(CVBarCountMap.containsKey(attributeArr[0])){
								int cnt = CVBarCountMap.get(attributeArr[0]);
								CVBarCountMap.put(attributeArr[0], cnt+1);
								Float temp = CVBarMap.get(attributeArr[0]);
								
								CVBarMap.put(attributeArr[0], (temp+cvbarAvg));

							}else{
								CVBarCountMap.put(attributeArr[0], 1);
								CVBarMap.put(attributeArr[0], cvbarAvg);
							}
						}
				
					}	
				}	
			}		
		}

		// add query attribute in list - calculate probability for QV
		for (Map.Entry<String, QueryDetail> entry : QVMap.entrySet()) {
			QueryDetail queryDetail = entry.getValue();

			// formula to calculate probability
			float priority = ((Float.valueOf(entry.getValue().getProbability()) + 1f) / (Float.valueOf(queryTexts.length)+1f));

			// create object which hold attribute information
			QueryAttribute attribute = new QueryAttribute();
			attribute.setName(entry.getKey());
			attribute.setProbability(priority);
			attribute.setKeyword(StringUtils.join(queryDetail.getValues(), ","));
			attribute.setQueryText(queryText);
			attributes.add(attribute);
			
			// add priority
			queryDetail.setPriority(priority);
			QVMap.put(entry.getKey(), queryDetail);
		}
		
		// update cv map
		
		for (Entry<String, Float> cvmap : CVMap.entrySet()) {
			int count = CVCountMap.get(cvmap.getKey());
			float avg = cvmap.getValue()/count;
			CVMap.put(cvmap.getKey(), avg);
		}
		
		// update cv bar map
		for (Entry<String, Float> cvmap : CVBarMap.entrySet()) {
			int count = CVBarCountMap.get(cvmap.getKey());
			float avg = cvmap.getValue()/count;
			CVBarMap.put(cvmap.getKey(), avg);
		}
		
		System.out.println("CVMap map :::::::::::"+CVMap);

		System.out.println("QVMap map :::::::::::"+QVMap);
		
		System.out.println("CVBarMap map :::::::::::"+CVBarMap);
			
		
		// Probabilistic Information
		
		// multiply all cv values
		
		Float cvMulti = 1f;
		
		for( Entry<String, Float>  cvmap :CVMap.entrySet()){
			cvMulti = cvMulti * cvmap.getValue();
		}
	
		// multiply all cv bar values
		
		Float cvbarMulti = 1f;
		
		for( Entry<String, Float>  cvbarmap :CVBarMap.entrySet()){
			cvbarMulti = cvbarMulti * cvbarmap.getValue();
		}
		
		// Threshold using QV and CV
		Map<String,Float> thresholdMap = new LinkedHashMap<>();
		
		for(Entry<String, Float> map :CVBarMap.entrySet()){
			QueryDetail qd = QVMap.get(map.getKey());
			Float total = map.getValue()*qd.getPriority();
			System.out.println("QV * CV = total= "+total);
			thresholdMap.put(map.getKey(), total);
		}
		
		System.out.println("Threshold  :::::::::: "+thresholdMap);
		
		// Score
		Map<String,Float> scoreMap = new HashMap<>();
		for(Entry<String, QueryDetail> qvMap : QVMap.entrySet()){
		
			Float score = (Float.valueOf(qvMap.getValue().getPriority())/((1f- Float.valueOf(qvMap.getValue().getPriority()))+1f))* (Float.valueOf(cvMulti/cvbarMulti));
			scoreMap.put(qvMap.getKey(), score);
		}
		
		System.out.println("Score  :::::::::: "+scoreMap);
		
		// R set
		Map<String,Float> rSetMap = new LinkedHashMap<>();
		for(Entry<String, Float> sMap : scoreMap.entrySet()){
			if(sMap.getValue()>=thresholdMap.get(sMap.getKey())){
				rSetMap.put(sMap.getKey(), sMap.getValue());
			}
		}
		
		rSetMap = ValueComparators.sortByStringComparator(rSetMap);
		System.out.println("Sorted R set map : "+rSetMap);
		
		Set<UserDocument> documents = new LinkedHashSet<>();
		
		if(null != rSetMap){
			for (Entry<String, Float> entry : rSetMap.entrySet()) {
				
				if (queryTexts != null && queryTexts.length > 0) {

					for (String text : queryTexts) {

						// get all attributes from query text splitting by comma
						// Location:Pune
						String[] attributeValues = text.split(",");

						// Iterate over each attribute
						for (String attributeValue : attributeValues) {
							
							if(!attributeValue.isEmpty() && attributeValue.length()>0){
								// split attribute into array [Location,Pune]
								String[] attributeArr = attributeValue.split(":");
				
								if(entry.getKey().equalsIgnoreCase(attributeArr[0])){
									List<UserDocument> attributePresentInDocuments = userDocumentService.findAllByAttributes(attributeArr[0], attributeArr[1]);//total number of documents containing location value
									documents.addAll(attributePresentInDocuments);
								}
							}
						}
					}
				}
			}	
		}
		if(rSetMap.size()==0) {
			scoreMap = ValueComparators.sortByStringComparator(scoreMap);
			System.out.println("sort S");
			for (Entry<String, Float> entry : scoreMap.entrySet()) {
				rSetMap.put(entry.getKey(), entry.getValue());
				if (queryTexts != null && queryTexts.length > 0) {

					for (String text : queryTexts) {
						
						// get all attributes from query text splitting by comma
						// Location:Pune
						String[] attributeValues = text.split(",");

						// Iterate over each attribute
						for (String attributeValue : attributeValues) {
							
							if(!attributeValue.isEmpty() && attributeValue.length()>0){
								// split attribute into array [Location,Pune]
								String[] attributeArr = attributeValue.split(":");
								
								if(entry.getKey().equalsIgnoreCase(attributeArr[0])){
									System.out.println("entry.getKey() "+entry.getKey()+" attributeArr[0]"+attributeArr[0]);
									List<UserDocument> attributePresentInDocuments = userDocumentService.findAllByAttributes(attributeArr[0], attributeArr[1]);//total number of documents containing location value
									documents.addAll(attributePresentInDocuments);
								}
							}
						}
					}
				}
				break;
			}	
		}
		System.out.println("R set  :::::::::: "+rSetMap);
		
		 model.addAttribute("userDocuments", documents);
		 long et = System.currentTimeMillis();
		System.out.println("#### Total Time for Combination "+(et-st));
		/////////////////////////////////////////////////////////////////////////////////////////////
		return "user_qc_value";
	}
	
	@RequestMapping(value = "/search/user_product_details", method = RequestMethod.GET)
	public String productDetails(HttpServletRequest request, ModelMap model){

 	   QueryText queryText = new QueryText();
 	   model.addAttribute("queryText", queryText);
		
 	   UserDocument userDocuments = userDocumentService.findById(Long.valueOf(request.getParameter("id")));
	   model.addAttribute("userDocuments", userDocuments);
		
		return "user_product_details";
	}
	
	
	@RequestMapping(value = "/search/displayMappingAlgorithm", method = RequestMethod.GET)
    public String displayMappingAlgorithm(HttpServletRequest request, ModelMap model) {
		
		QueryText queryText = new QueryText();
		model.addAttribute("queryText", queryText);
		
		User user = (User)request.getSession().getAttribute("user");
 	    List<UserDocument> userDocuments = userDocumentService.getRecommendedDocuments(user.getId());
 	    
 	    model.addAttribute("userDocuments", userDocuments);
    	model.addAttribute("user", user);
    	return "user_mapping_algo";

    }
	
	@RequestMapping(value = "/search/mapping_algorithm", method = RequestMethod.POST)
	public String serchMappingAlgorithm(@ModelAttribute("queryText") QueryText queryText, ModelMap model){
		
		long st = System.currentTimeMillis();
		Map<UserDocument,Float> sortedMap = searchService.getMappingAlgo(queryText);
		System.out.println("--------------------- content value ------------------");
		for(Map.Entry<UserDocument, Float> entry : sortedMap.entrySet()){
			System.out.println("Name : "+entry.getKey().getProductName()+" Formula Result :  "+entry.getValue());
		}
		
		long et = System.currentTimeMillis();
		System.out.println("#### Time for Mapping "+(et-st));
		List<UserDocument> userDocument = new ArrayList<>(sortedMap.keySet());
		Collections.reverse(userDocument);
		model.addAttribute("userDocuments",userDocument);
		
		return "user_mapping_algo_result";
	}
	
	
}
