package com.myproject.demomyproject.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.demomyproject.model.Solution;
import com.myproject.demomyproject.repository.KnowledgeRepository;
import com.myproject.demomyproject.service.KnowledgeBaseService;

@Service
@Qualifier("KnowledgeBaseServiceImpl")
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService{

	@Autowired
	private KnowledgeRepository knowledgeRepository;
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	@Transactional
	@Override
	public Solution saveSolution(Solution solution) {
		Solution savedSolution = knowledgeRepository.insert(solution);
		return savedSolution;
	}
	
	@Transactional
    @Override
    public void deleteSolution(Long id) {
		knowledgeRepository.deleteById(id);
    }

	@Override
	public Solution findById(Long id) {
		Optional<Solution> solution = knowledgeRepository.findById(id);
		return solution.get();
	}

	@Override
	public List<Solution> findAll() {
		return knowledgeRepository.findAll();
	}

	@Override
	public Solution updateSolution(Solution solution) {
		Solution savedSolution = knowledgeRepository.save(solution);
		return savedSolution;
	}

	@Override
	public List<String> findAllCategory() {

		//System.out.println("All category from mongoTemplate distinct category ---------------------");
		List<String> categories = mongoTemplate.query(Solution.class).distinct("category").as(String.class).all();

	    Collections.sort(categories);
		categories.forEach(System.out::println);
		return categories;
	}
	

}
