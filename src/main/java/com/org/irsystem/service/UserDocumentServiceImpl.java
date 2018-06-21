package com.org.irsystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.irsystem.dao.RecommendDao;
import com.org.irsystem.dao.UserDAO;
import com.org.irsystem.dao.UserDocumentDao;
import com.org.irsystem.model.Recommend;
import com.org.irsystem.model.RecommendPair;
import com.org.irsystem.model.User;
import com.org.irsystem.model.UserDocument;
import com.org.irsystem.model.UserDocumentPair;
import com.org.irsystem.model.UserDocumentToken;
import com.org.irsystem.util.SortRecommendByValue;

@Service("userDocumentService")
@Transactional
public class UserDocumentServiceImpl implements UserDocumentService{

	@Autowired
	UserDocumentDao dao;
	
	@Autowired
	private RecommendDao recommendDao;
	
	@Autowired
	UserDAO userDAO;

	public UserDocument findById(Long id) {
		return dao.findById(id);
	}

	public List<UserDocument> findAll() {
		return dao.findAll();
	}

	public List<UserDocument> findAllByUserId(Long userId) {
		return dao.findAllByUserId(userId);
	}
	
	@Override
	public List<UserDocument> findSharedFilesByUserId(Long userId) {
		return dao.findSharedFilesByUserId(userId);
	}
	
	public void saveDocument(UserDocument document){
		
		// content value
		
		String[] udArray = document.toString().split(" ");
		Map<String,Integer> map = new HashMap<>(); 
		
		for (String udString : udArray) {
			
			if(map!=null){
				
				if(map.containsKey(udString)){
					map.put(udString, map.get(udString)+1);
				}else{
					if(null != udString && ! udString.isEmpty())
						map.put(udString, 1);			
				}
				
			}
		}
		
		
		UserDocumentToken token = null;
		Set<UserDocumentToken> documentTokens = new HashSet<>();
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			token = new UserDocumentToken();
			token.setToken(entry.getKey());
			token.setCount(entry.getValue());
			token.setUserDocument(document);
			documentTokens.add(token);
		}
		
		System.out.println(map);
		document.setDocumentTokens(documentTokens);
		dao.save(document);
		
		
			
	}

	public void deleteById(Long id){
		dao.deleteById(id);
	}

	@Override
	public List<UserDocument> findAllByAttributes(String attributeKey, String attributeValue) {
		return dao.findAllByAttributes(attributeKey, attributeValue);
	}

	@Override
	public List<UserDocument> getRecommendedDocuments(Long userId) {
		
		// get the list of all document for which user provided rating
		List<Recommend> recommends = recommendDao.findAllByUserId(userId);
		
		// recommend pair
		List<RecommendPair> recommendPairs = getRecommendPairs(recommends);

		// get all products form database
		List<UserDocument> documents2 = dao.findAll();
		
		// product pair
		List<UserDocumentPair> documentPairs = getUserDocumentPair(documents2);

		// map of pair and it's average
		
		Map<RecommendPair, Double> adjCorSimMap = new LinkedHashMap<RecommendPair, Double>();
		Map<RecommendPair, Double> personCorSimMap = new LinkedHashMap<RecommendPair, Double>();
		Map<RecommendPair, Double> linearCombinationMap = new LinkedHashMap<RecommendPair, Double>();

		
		
		for(RecommendPair pair : recommendPairs){
			
			double adjCorSim = getAdjCorSim(recommends,recommendPairs,documents2,documentPairs, userId,pair);
			adjCorSimMap.put(pair, adjCorSim);
			
			double personCorSim = getPersonCorSim(recommends,recommendPairs,documents2,documentPairs,userId,pair);	
			personCorSimMap.put(pair, personCorSim);
			
			// sim(k,l) = sim(k,l)item * (1-c) + sim(K,l)grop * c
			
			double linearCom = adjCorSim*(1-0.4)+personCorSim*0.4;
			linearCombinationMap.put(pair, linearCom);
			
		}
		
		// 
		Map<Recommend,Double> recpredictions = getPredictions(recommends, adjCorSimMap);
		
		// get user documents
		List<UserDocument>  documents = new ArrayList<>();
		
		for(Map.Entry<Recommend,Double> predict : recpredictions.entrySet()){
			
			UserDocument document = dao.findById(predict.getKey().getDocumentId());
			documents.add(document);
		}
		
		
		return documents;
	}

	private Map<Recommend, Double> getPredictions(List<Recommend> recommends, Map<RecommendPair, Double> adjCorSimMap) {

		Map<Recommend, Double> prediction = new LinkedHashMap<>();

		for(Recommend recommend : recommends){
			
			double sum  = 0;
			
			for(Map.Entry<RecommendPair, Double> entry : adjCorSimMap.entrySet()){
				
				// (1,2) (1,3) (1,4)
				if(entry.getKey().getRecommend1().equals(recommend)){
					
					// get user rating
					int userrating =0;
					if(null != entry.getKey().getRecommend2() && null != entry.getKey().getRecommend2().getRating() )
						userrating = entry.getKey().getRecommend2().getRating();
					
					// get rating
					int rating = recommendDao.getUserRatingByProductId(entry.getKey().getRecommend2().getUserId(), entry.getKey().getRecommend2().getDocumentId());
					
					// get similarity
					double similarity = entry.getValue();
					
					sum= (sum + (userrating - (rating * similarity)));
					
				}
				
				
			}
			
			prediction.put(recommend, sum);
			
		}
		
		
		// sort map by value
		Map<Recommend,Double> predictions = new LinkedHashMap<>(SortRecommendByValue.sortByComparator(prediction));
		
		return predictions;
	}

	private double getPersonCorSim(List<Recommend> recommends, List<RecommendPair> recommendPairs, List<UserDocument> documents, List<UserDocumentPair> documentPairs, long userId, RecommendPair pair) {
		
		double numerator1 = getPersonCorSimNumerator1(recommendPairs,documents,userId,pair);
		long numerator2 = getPersonCorSimNumerator2(documentPairs,documents,pair);
		
		//Adjacent correlation similarity 
		double personCorSim = numerator1/Math.sqrt(numerator2);
		
		System.out.println("personCorSim : "+personCorSim);
		
		return personCorSim;
	}

	private long getPersonCorSimNumerator2(List<UserDocumentPair> documentPairs, List<UserDocument> documents, RecommendPair pair) {
		
		long p1Sum=0;
		long p2Sum=0;
		long numerator2=0;
		
		// get list of users
		List<User> users = userDAO.listUserRoleUsers();

			
		// iterate over all users
		for(User user : users){
			
			// product 1 square
			// check if user associated with document or not, if not it will return 0
			long p1UserRating = 0;
			if(null != pair.getRecommend1() && null != pair.getRecommend1().getRating())
				p1UserRating = pair.getRecommend1().getRating();
			
			long p1AvgRating = recommendDao.getUserRatingByProductId(user.getId(), pair.getRecommend1().getDocumentId());
			
			p1Sum += p1UserRating-p1AvgRating;
			
			// product 2 square	
			// check if user associated with document or not, if not it will return 0
			long p2UserRating =0;
			if(null != pair.getRecommend2() && null != pair.getRecommend2().getRating() )
				p2UserRating = pair.getRecommend2().getRating();
			
			long p2AvgRating = recommendDao.getUserRatingByProductId(user.getId(), pair.getRecommend2().getDocumentId());
		
			p2Sum += p2UserRating-p2AvgRating;
			
		}

		numerator2 = (long) (Math.pow(p1Sum, 2) * Math.pow(p2Sum, 2));

		return numerator2;
	}

	private double getPersonCorSimNumerator1(List<RecommendPair> recommendPairs, List<UserDocument> documents, long userId, RecommendPair pair) {
		
		double numerator1 = 0;
		
		// iterate over pair of products
		//for(RecommendPair pair : recommendPairs){
			
			// p1 user rating
			int p1UserRating = 0;
			
			if(null != pair.getRecommend1() && null != pair.getRecommend1().getRating())
				p1UserRating = pair.getRecommend1().getRating();
			
			// p1 avg rating
			double p1AvgRating = recommendDao.getAvgRatingByUserId(userId);
			
			
			// p2 user rating
			int p2UserRating = 0;
			if(null != pair.getRecommend2() && null != pair.getRecommend2().getRating())
				p2UserRating = pair.getRecommend2().getRating();
			
			// p2 avg rating
			double p2AvgRating = recommendDao.getAvgRatingByUserId(userId);;
			
			// formula
			numerator1 = ((p1UserRating-p1AvgRating)*(p2UserRating-p2AvgRating));
			
		//}
		return numerator1;
	}

	private double getAdjCorSim(List<Recommend> recommends, List<RecommendPair> recommendPairs,List<UserDocument> documents, List<UserDocumentPair> documentPairs, long userId, RecommendPair pair) {
			
			long numerator1 = getAdjCorSimNumerator1(recommendPairs,documents,pair);
			long numerator2 = getAdjCorSimNumerator2(documentPairs,documents, pair);
			
			//Adjacent correlation similarity 
			double adjCorSim = numerator1/Math.sqrt(numerator2);
			
			System.out.println("adjCorSim : "+adjCorSim);
			
		return adjCorSim;
	}

	

	private long getAdjCorSimNumerator1(List<RecommendPair> recommendPairs, List<UserDocument> documents, RecommendPair pair) {
		
			UserDocument document = null;
			long numerator1 = 0;
		
			// p1 user rating
			int p1UserRating = 0;
			
			if(null != pair && null != pair.getRecommend1() && null != pair.getRecommend1().getRating())		
				p1UserRating = pair.getRecommend1().getRating();
			
			document = getUserDocumentByRecommendId(pair.getRecommend1().getDocumentId(),documents);
		
			// p1 avg rating
			int p1AvgRating = 0;
			
			if(document!=null && document.getProductRating() != null){
				p1AvgRating = document.getProductRating();
			}
			
			
			// p2 user rating
			int p2UserRating = 0;
			if(null != pair && null != pair.getRecommend2() && null != pair.getRecommend2().getRating())
				pair.getRecommend2().getRating();
			
			document = getUserDocumentByRecommendId(pair.getRecommend2().getDocumentId(),documents);
			
			// p2 avg rating
			int p2AvgRating = 0;
			
			if(document!=null && document.getProductRating() != null){
				p2AvgRating = document.getProductRating();	
			}
			
			// formula
			numerator1 = ((p1UserRating-p1AvgRating)*(p2UserRating-p2AvgRating));
	
			return numerator1;
	}
	
	private long getAdjCorSimNumerator2(List<UserDocumentPair> documentPairs, List<UserDocument> documents, RecommendPair pair) {
		
		long p1Sum=0;
		long p2Sum=0;
		long numerator2=0;
		
		// get list of users
		List<User> users = userDAO.listUserRoleUsers();

			
		// iterate over all users
		for(User user : users){
			
			// product 1 square
			// check if user associated with document or not, if not it will return 0
			long p1UserRating = 0;
			
			if(null != pair.getRecommend1() && null != pair.getRecommend1().getRating())
				p1UserRating = pair.getRecommend1().getRating();
			
			long p1AvgRating = recommendDao.getUserRatingByProductId(user.getId(), pair.getRecommend1().getDocumentId());
			
			p1Sum += p1UserRating-p1AvgRating;
			
			// product 2 square	
			// check if user associated with document or not, if not it will return 0
			long p2UserRating = 0;
			if(null != pair && null != pair.getRecommend2() && null != pair.getRecommend2().getRating())
				p2UserRating = pair.getRecommend2().getRating();
			
			long p2AvgRating = recommendDao.getUserRatingByProductId(user.getId(), pair.getRecommend2().getDocumentId());
		
			p2Sum += p2UserRating-p2AvgRating;
			
		}

		numerator2 = (long) (Math.pow(p1Sum, 2) * Math.pow(p2Sum, 2));

		return numerator2;
	}

	private List<UserDocumentPair> getUserDocumentPair(List<UserDocument> documents) {
		// pair the recommends
		List<UserDocumentPair> userDocumentPairs = new ArrayList<>();
		
		for(int i=0;i<documents.size();i++){
			for(int j=i+1;j<documents.size();j++){
				userDocumentPairs.add(new UserDocumentPair(documents.get(i), documents.get(j)));
			}
		}		
		return userDocumentPairs;
	}

	private List<RecommendPair> getRecommendPairs(List<Recommend> recommends) {
		// pair the recommends
		List<RecommendPair> recommendPairs = new ArrayList<>();
		
		for(int i=0;i<recommends.size();i++){
			for(int j=i+1;j<recommends.size();j++){
				recommendPairs.add(new RecommendPair(recommends.get(i), recommends.get(j)));
			}
		}		
		return recommendPairs;
	}

	private UserDocument getUserDocumentByRecommendId(Long id, List<UserDocument> documents) {
		for(UserDocument document : documents){
			if(document.getId().equals(id)){
				return document;
			}
		}
		return null;
	}
	
}
