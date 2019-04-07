package com.myproject.demomyproject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
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
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "category"));
		List<String> categories = (List<String>) query.fields().include("category");
		return null;
	}
	

}
