package com.myproject.demomyproject.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.demomyproject.model.Solution;
import com.myproject.demomyproject.model.elasticsearch.EsSolution;
import com.myproject.demomyproject.repository.KnowledgeRepository;
import com.myproject.demomyproject.repository.elasticsearch.KnowledgeElasticSearchRepository;
import com.myproject.demomyproject.service.KnowledgeBaseService;

public class KnowledgeBaseServiceImpl implements KnowledgeBaseService{

	@Autowired
	private KnowledgeRepository knowledgeRepository;
	
	@Autowired
	private KnowledgeElasticSearchRepository knowledgeElasticSearchRepository;
	
	@Transactional
	@Override
	public Solution saveSolution(Solution solution) {
		Solution savedSolution = knowledgeRepository.insert(solution);
		knowledgeElasticSearchRepository.save(new EsSolution(solution));
		return savedSolution;
	}
	
	@Transactional
    @Override
    public void deleteSolution(String id) {
		knowledgeRepository.deleteById(id);
		knowledgeElasticSearchRepository.deleteById(id);
    }

	@Override
	public Solution findById(String id) {
		Optional<Solution> solution = knowledgeRepository.findById(id);
		return solution.get();
	}

	@Override
	public Solution findByType(String type) {
		return knowledgeRepository.findByType(type);
	}

}
