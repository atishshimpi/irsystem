package com.org.irsystem.model;

import java.util.HashSet;
import java.util.Set;

public class QueryDetail {

	Float probability;
	Float priority;
	Set<String> values = new HashSet<String>();

	public Float getProbability() {
		return probability;
	}

	public void setProbability(Float probability) {
		this.probability = probability;
	}

	public Set<String> getValues() {
		return values;
	}

	public void setValues(Set<String> values) {
		this.values = values;
	}
	
	public Float getPriority() {
		return priority;
	}

	public void setPriority(Float priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Prob : "+getProbability()+" Priority : "+getPriority()+" Values : "+getValues();
	}

}
