package com.myproject.demomyproject.model.elasticsearch;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(indexName = "knowledge", type = "solution")
public class EsSolution implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5752926460899989168L;

	@Id
	private Long id;
	
	private String solutionId;
	private String projectName;
    private String category;
    private String categoryDescription; 
	private String solutionType;  //solution types: Implementation, Compensating Control, and False Positive
    private String solutionInformation; //soltion data
    private String comment;
    
	public EsSolution(String solutionId, String projectName, String category, String categoryDescription, 
			String soltionType, String soltionInformation, String comment) {
		this.solutionId = solutionId;
		this.projectName = projectName;
		this.category = category;
		this.categoryDescription = categoryDescription;
		this.solutionType = soltionType;
		this.solutionInformation = soltionInformation;
		this.comment = comment;
	}
    
}
