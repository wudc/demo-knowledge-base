package com.myproject.demomyproject.dataprovider;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.myproject.demomyproject.service.KnowledgeBaseService;

@Repository
public class CategoryDataProvider {

	private List<String> categories;
	
	@Autowired
	@Qualifier("KnowledgeBaseServiceImpl")
	private KnowledgeBaseService storageService;
	
	private List<String> getAllCategories() {
		return storageService.findAllCategory();
	}

	public List<String> getCategories() {
		categories = getAllCategories();
		//System.out.println("All category from MongoDB->CategoryDataProvider ---------------------");
		//categories.forEach(System.out::println);
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	
}
