package com.myproject.demomyproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.myproject.demomyproject.model.Solution;
import com.myproject.demomyproject.model.SolutionType;
import com.myproject.demomyproject.model.elasticsearch.EsSolution;
import com.myproject.demomyproject.repository.KnowledgeRepository;
import com.myproject.demomyproject.repository.elasticsearch.KnowledgeElasticSearchRepository;

@SpringBootApplication
public class DemoMyprojectApplication implements CommandLineRunner {
	
	@Autowired
	private KnowledgeRepository krepo;
	
	@Autowired
	private KnowledgeElasticSearchRepository esrepo;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoMyprojectApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
		Solution sol1 = new Solution ("Cross-site injection", SolutionType.IMPLEMENTATION, "SanitizerHtml(input)");
		Solution sol2 = new Solution ("Data access: database", SolutionType.COMPENSATING_CONTROL, "Using the Struts interceptor.");
		Solution sol3 = new Solution ("Key Management", SolutionType.FALSE_POSITIVE, "This is detecting the user keyboard actions.");
		
		//insert solution objects into Mongo
//		krepo.save(sol1);
//		krepo.save(sol2);
//		krepo.save(sol3);
		
		
		// fetch all articles from Mongo
        System.out.println("Solutions found in MongoDB with findAll():");
        System.out.println("-----------------------------------------");
        Iterable<Solution> solutions = krepo.findAll();
        solutions.forEach(System.out::println);
        System.out.println();
        
//        esrepo.save(new EsSolution(sol1));
//        esrepo.save(new EsSolution(sol2));
//        esrepo.save(new EsSolution(sol3));

     // fetch all articles from Elastisearch
        System.out.println("Solutions found in Elasticsearch with findAll():");
        System.out.println("-----------------------------------------------");
        Iterable<EsSolution> esSolutions = esrepo.findAll();
        esSolutions.forEach(System.out::println);
        System.out.println();        
	}
}
