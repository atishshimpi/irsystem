package com.org.irsystem.model;

public class RecommendPair {

	private Recommend recommend1;
	private Recommend recommend2;

	public RecommendPair(Recommend recommend1, Recommend recommend2) {
		super();
		this.recommend1 = recommend1;
		this.recommend2 = recommend2;
	}

	public Recommend getRecommend1() {
		return recommend1;
	}

	public void setRecommend1(Recommend recommend1) {
		this.recommend1 = recommend1;
	}

	public Recommend getRecommend2() {
		return recommend2;
	}

	public void setRecommend2(Recommend recommend2) {
		this.recommend2 = recommend2;
	}
}
