package com.myproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document
public class Solution {

	@Id
    private String id;
	private String type;
    private String category;
    private String content;
    
	public Solution(String category, String type, String content) {
		this.type = type;
		this.category = category;
		this.content = content;
	}
}
