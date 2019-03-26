package com.myproject.model.elasticsearch;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;

import com.myproject.model.Solution;

import org.springframework.data.elasticsearch.annotations.Document;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(indexName = "solution", type = "solution")
public class EsSolution implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5752926460899989168L;

	@Id
	private String id;
	
	@Field
	private String solutionId;
	
	@Field
	private String type;
    private String category;
    private String content;
    
    public EsSolution(Solution solution ) {
    	this.update(solution);
    }
    
    public void update(Solution solution) {
    	this.solutionId = solution.getId();
    	this.type = solution.getType();
    	this.category = solution.getCategory();
    	this.content = solution.getContent();
    }
}
