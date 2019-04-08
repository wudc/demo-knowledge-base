package com.myproject.demomyproject.dataprovider;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class SolutionTypeDataProvider {
	private List<String> solutionTypes;
	
	public List<String> getSolutionTypes() {
		solutionTypes = getSolutionTypeList();
		return solutionTypes;
	}

	public void setSolutionTypes(List<String> solutionTypes) {
		this.solutionTypes = solutionTypes;
	}

	private List<String> getSolutionTypeList() {
		List<String> solutionTypeList = new ArrayList<>();
		solutionTypeList.add("Compensating Control");
		solutionTypeList.add("Implementation");
		solutionTypeList.add("False Positive");
		//To Do. get the list from MongoDB
		return solutionTypeList;
	}

}
