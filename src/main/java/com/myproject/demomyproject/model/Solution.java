package com.myproject.demomyproject.model;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
//@Document
@Document(indexName = "knowledge", type = "solution")
public class Solution {

	@Id
    private String id;
	private String projectName;
    private String category;
    private String categoryDescription; 
	private String solutionType;  //solution types: Implementation, Compensating Control, and False Positive
    private String solutionInformation; //soltion data
    private String comment;
    
	public Solution(String projectName, String category, String categoryDescription, 
			String soltionType, String soltionInformation, String comment) {
		this.projectName = projectName;
		this.category = category;
		this.categoryDescription = categoryDescription;
		this.solutionType = soltionType;
		this.solutionInformation = soltionInformation;
		this.comment = comment;
	}
}
