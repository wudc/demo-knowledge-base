package com.myproject.demomyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.myproject.demomyproject.repository.elasticsearch.KnowledgeElasticSearchRepository;
import com.myproject.demomyproject.service.impl.SearchServiceImpl;

@SpringBootApplication
public class DemoMyprojectApplication { 

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoMyprojectApplication.class, args);
		SearchServiceImpl service = context.getBean(SearchServiceImpl.class);
		System.out.println("DemoMyprojectApplication->SearchServiceImpl: " + service);
		KnowledgeElasticSearchRepository repo = context.getBean(KnowledgeElasticSearchRepository.class);
		System.out.println("DemoMyprojectApplication->KnowledgeElasticSearchRepository: " + repo);
		
	}

}
