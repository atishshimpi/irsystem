package com.org.irsystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.irsystem.model.ContentAttribute;
import com.org.irsystem.model.QueryAttribute;
import com.org.irsystem.model.QueryDetail;
import com.org.irsystem.model.QueryText;
import com.org.irsystem.model.UserDocument;
import com.org.irsystem.util.ValueComparators;


@Service(value="queryContentValueService")
public class QueryContentValueServiceImpl implements QueryContentValueService {
	
	@Autowired
	private QueryTextService queryTextService;

	@Autowired
	private ValueComparators valueComparators;

	@Resource
	private UserDocumentService userDocumentService;
	
	@Autowired
	private LocationService locationService;
	
	QueryDetail queryDetails = null;
	
	Map<UserDocument,Float> sortedMap = null;
	
	@Override
	public void getQueryContentValueDocuments(QueryText queryText) {
		// get query value attributes
		List<QueryAttribute> queryAttributes = getQueryValueAttributes(queryText);
		
		List<ContentAttribute> contentAttributes = getContentValueAttributes(queryText);
		
		
		queryText.setQueryAttributes(queryAttributes);
		queryText.setContentAttributes(contentAttributes);
		queryTextService.add(queryText);
	}
	
	
	@Override
	public List<QueryAttribute> getQueryValueAttributes(QueryText queryText) {


		// global declaration
		List<QueryAttribute> attributes = new ArrayList<>();

		// Natural sorting order by comparator
		Map<String, QueryDetail> map = new TreeMap<>();


		try {

			// get all queries
			List<QueryText> queryTextList = queryTextService.getAll();


			// flush data from query text and child tables
			for (QueryText queryText2 : queryTextList) {
				queryTextService.delete(queryText2.getId());
			}

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
								if (null != map && null != map.get(attributeArr[0])) {
									queryDetails = new QueryDetail();
									queryDetails = map.get(attributeArr[0]); // 
									queryDetails.setProbability(queryDetails.getProbability() + 1);
									queryDetails.getValues().add(attributeArr[1]);
									map.put(attributeArr[0], queryDetails);
								} else {
									
									if(attributeArr.length>1){
										
										if(attributeArr[1]!=null && !attributeArr[1].isEmpty()){
											queryDetails = new QueryDetail();
											queryDetails.setProbability(1.0f);
											queryDetails.getValues().add(attributeArr[1]);
											map.put(attributeArr[0], queryDetails);	
										}
										
									}
								}
							}
									
						}
					}
				}
			}

			// add query attribute in list
			for (Map.Entry<String, QueryDetail> entry : map.entrySet()) {
				QueryDetail queryDetail = entry.getValue();

				// formula to calculate probability
				float priority = entry.getValue().getProbability() + 1 / queryTexts.length;

				// create object which hold attribute information
				QueryAttribute attribute = new QueryAttribute();
				attribute.setName(entry.getKey());
				attribute.setProbability(priority);
				attribute.setKeyword(StringUtils.join(queryDetail.getValues(), ","));
				attribute.setQueryText(queryText);
				attributes.add(attribute);
			}

			return attributes;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<ContentAttribute> getContentValueAttributes(QueryText queryText) {

		try {
			
			List<UserDocument> documents = userDocumentService.findAll();

			Map<UserDocument,Float> map = new TreeMap<>();
			Map<String,ContentAttribute> attributesMap = new HashMap<>();   

			for(UserDocument document : documents){

				String[] queryTexts = queryText.getText().split("\n");

				if(queryTexts!=null && queryTexts.length>0){

					for (String text : queryTexts) {
						String[] attributeValues = text.split(",");

						for(String attributeValue : attributeValues){

							String[] attributeArr = attributeValue.split(":");

							if(attributeArr[0].trim().equalsIgnoreCase("location")){
								//if(document.getDealerLocation().contains(attributeArr[1])){
								if(document.getDealerLocation().toLowerCase().contains(attributeArr[1].toLowerCase())){
									List<UserDocument> attributePresentInDocuments = userDocumentService.findAllByAttributes(attributeArr[0], attributeArr[1]);
									List<UserDocument> allDocuments = userDocumentService.findAll();
									int wordCount = StringUtils.countMatches(document.getDealerLocation().toLowerCase(), attributeArr[1].toLowerCase());

									Float avg = (float) ((attributePresentInDocuments.size()+1)/(allDocuments.size()+allDocuments.size()+1));
									map.put(document, avg);
									System.out.println("------------------- Location -----------------------------");
									System.out.println("all documents having keyword: "+attributePresentInDocuments.size());
									System.out.println("document count "+allDocuments.size());
									System.out.println("wordCount : "+wordCount);
									System.out.println("Dealer Location : "+document.getDealerLocation().toLowerCase());
									System.out.println("Formula result : "+((attributePresentInDocuments.size()+1)/(allDocuments.size()+allDocuments.size()+1)));
									System.out.println("Name : "+document.getProductName());

									ContentAttribute contentAttribute = new ContentAttribute();
									contentAttribute.setKeyword("location");
									contentAttribute.setName("location");
									contentAttribute.setQueryText(queryText);
									
									if(null!=attributesMap && attributesMap.containsKey("location")){
										contentAttribute.setProbability(attributesMap.get("location").getProbability()+ avg);
									}else{
										contentAttribute.setProbability(avg);
									}
									attributesMap.put("location",contentAttribute);
									
								}
							}else if(attributeArr[0].trim().equalsIgnoreCase("type")){
								if(document.getProductType().toLowerCase().contains(attributeArr[1].toLowerCase())){
									List<UserDocument> attributePresentInDocuments = userDocumentService.findAllByAttributes(attributeArr[0], attributeArr[1]);
									List<UserDocument> allDocuments = userDocumentService.findAll();
									int wordCount = StringUtils.countMatches(document.getProductType().toLowerCase(), attributeArr[1].toLowerCase());
									
									//attributePresentInDocuments.size()+1/allDocuments+allDocuments+1
									Float avg = (float) ((attributePresentInDocuments.size()+1)/(allDocuments.size()+allDocuments.size()+1));
									map.put(document, avg);
									
									System.out.println("------------------- Type -----------------------------");
									System.out.println("attributePresentInDocuments : "+attributePresentInDocuments.size());
									System.out.println("attributePresentInDocuments : "+allDocuments.size());
									System.out.println("wordCount : "+wordCount);
									System.out.println("document.getDealerLocation().toLowerCase() : "+document.getDealerLocation().toLowerCase());
									System.out.println("Formula result : "+((attributePresentInDocuments.size()+1)/(allDocuments.size()+allDocuments.size()+1)));
									System.out.println("Name : "+document.getProductName());
									
									ContentAttribute contentAttribute = new ContentAttribute();
									contentAttribute.setKeyword("type");
									contentAttribute.setName("type");
									contentAttribute.setQueryText(queryText);
									
									if(null!=attributesMap && attributesMap.containsKey("type")){
										contentAttribute.setProbability(attributesMap.get("type").getProbability()+ avg);
									}else{
										contentAttribute.setProbability(avg);
									}
									
									attributesMap.put("type",contentAttribute);

								}
								
							}else if(attributeArr[0].trim().equalsIgnoreCase("name")){
								if(document.getProductName().toLowerCase().contains(attributeArr[1].toLowerCase())){
									List<UserDocument> attributePresentInDocuments = userDocumentService.findAllByAttributes(attributeArr[0], attributeArr[1]);
									List<UserDocument> allDocuments = userDocumentService.findAll();
									int wordCount = StringUtils.countMatches(document.getProductName().toLowerCase(), attributeArr[1].toLowerCase());

									Float avg = (float) ((attributePresentInDocuments.size()+1)/(allDocuments.size()+allDocuments.size()+1));
									map.put(document, avg);
									
									ContentAttribute contentAttribute = new ContentAttribute();
									contentAttribute.setKeyword("name");
									contentAttribute.setName("name");
									contentAttribute.setQueryText(queryText);
									
									if(null!=attributesMap && attributesMap.containsKey("name")){
										contentAttribute.setProbability(attributesMap.get("name").getProbability()+ avg);
									}else{
										contentAttribute.setProbability(avg);
									}
									
									attributesMap.put("name",contentAttribute);

								}
							}else if(attributeArr[0].trim().equalsIgnoreCase("price")){
								if(document.getProductPrice() == Float.valueOf(attributeArr[1])){
									List<UserDocument> attributePresentInDocuments = userDocumentService.findAllByAttributes(attributeArr[0], attributeArr[1]);
									List<UserDocument> allDocuments = userDocumentService.findAll();
									int wordCount = StringUtils.countMatches(document.getProductPrice().toString().toLowerCase(), attributeArr[1].toLowerCase());

									Float avg = (float) ((attributePresentInDocuments.size()+1)/(allDocuments.size()+allDocuments.size()+1));
									map.put(document, avg);
									
									ContentAttribute contentAttribute = new ContentAttribute();
									contentAttribute.setKeyword("price");
									contentAttribute.setName("price");
									contentAttribute.setQueryText(queryText);
									
									if(null!=attributesMap && attributesMap.containsKey("price")){
										contentAttribute.setProbability(attributesMap.get("price").getProbability()+ avg);
									}else{
										contentAttribute.setProbability(avg);
									}
									
									attributesMap.put("price",contentAttribute);

								}
							}else if(attributeArr[0].trim().equalsIgnoreCase("noofvisit")){
								if(document.getNoOfVisit() == Integer.valueOf(attributeArr[1])){
									List<UserDocument> attributePresentInDocuments = userDocumentService.findAllByAttributes(attributeArr[0], attributeArr[1]);
									List<UserDocument> allDocuments = userDocumentService.findAll();
									int wordCount = StringUtils.countMatches(document.getNoOfVisit().toString().toLowerCase(), attributeArr[1].toLowerCase());

									Float avg = (float) ((attributePresentInDocuments.size()+1)/(allDocuments.size()+allDocuments.size()+1));
									map.put(document, avg);
									
									ContentAttribute contentAttribute = new ContentAttribute();
									contentAttribute.setKeyword("noofvisit");
									contentAttribute.setName("noofvisit");
									contentAttribute.setQueryText(queryText);
									
									if(null!=attributesMap && attributesMap.containsKey("noofvisit")){
										contentAttribute.setProbability(attributesMap.get("noofvisit").getProbability()+ avg);
									}else{
										contentAttribute.setProbability(avg);
									}
									
									attributesMap.put("noofvisit",contentAttribute);
									
								}
							}else if(attributeArr[0].trim().equalsIgnoreCase("rating")){
								if(document.getProductRating() == Integer.valueOf(attributeArr[1])){
									List<UserDocument> attributePresentInDocuments = userDocumentService.findAllByAttributes(attributeArr[0], attributeArr[1]);
									List<UserDocument> allDocuments = userDocumentService.findAll();
									int wordCount = StringUtils.countMatches(document.getProductRating().toString().toLowerCase(), attributeArr[1].toLowerCase());

									Float avg = (float) ((attributePresentInDocuments.size()+1)/(allDocuments.size()+allDocuments.size()+1));
									map.put(document, avg);
									
									ContentAttribute contentAttribute = new ContentAttribute();
									contentAttribute.setKeyword("rating");
									contentAttribute.setName("rating");
									contentAttribute.setQueryText(queryText);
									
									if(null!=attributesMap && attributesMap.containsKey("rating")){
										contentAttribute.setProbability(attributesMap.get("rating").getProbability()+ avg);
									}else{
										contentAttribute.setProbability(avg);
									}
									
									attributesMap.put("rating",contentAttribute);
								}
							}
						}
					}	
				}
			}

			return new ArrayList<ContentAttribute>(attributesMap.values());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Float getContentValueBar(QueryText queryText) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
}
