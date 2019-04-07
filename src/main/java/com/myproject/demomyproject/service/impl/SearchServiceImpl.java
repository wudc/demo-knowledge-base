package com.myproject.demomyproject.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.demomyproject.model.Solution;
import com.myproject.demomyproject.model.elasticsearch.EsSolution;
import com.myproject.demomyproject.repository.elasticsearch.KnowledgeElasticSearchRepository;
import com.myproject.demomyproject.service.SearchService;

@Service
@Qualifier("SearchServiceImpl")
public class SearchServiceImpl implements SearchService {

	@Autowired
	private KnowledgeElasticSearchRepository knowledgeElasticSearchRepository;
	
	@Override
	public List<EsSolution> findByProjectName(String name) {
		return knowledgeElasticSearchRepository.findByProjectName(name);
	}

	@Override
	public List<EsSolution> findByCategory(String category) {
		return knowledgeElasticSearchRepository.findByCategory(category);
	}

	@Override
	public List<EsSolution> findBySolutionType(String type) {
		return knowledgeElasticSearchRepository.findBySolutionType(type);
	}

	@Transactional
	@Override
	public void deleteSolution(Long id) {
		knowledgeElasticSearchRepository.deleteById(id);
	}

	@Transactional
	@Override
	public EsSolution save(Solution solution) {
    	System.out.println("SearchServiceImpl->solutionRepository: " + knowledgeElasticSearchRepository);
    	System.out.println("Solution id: " + solution.getId());
    	EsSolution saveSolution = createNewSolution(solution);
    	Long id = new BigInteger(solution.getId(), 16).longValue();
    	saveSolution.setId(id);
    	
		knowledgeElasticSearchRepository.save(saveSolution);
		return saveSolution;
	}
	
	@Transactional
	@Override
	public EsSolution update(Solution solution, Long id) {
		EsSolution updateSolution = createNewSolution(solution);
		updateSolution.setId(id);
		knowledgeElasticSearchRepository.save(updateSolution);
		return updateSolution;
	}

	private EsSolution createNewSolution(Solution solution) {
    	return new EsSolution(
    			solution.getId(),
    			solution.getProjectName(),
    			solution.getCategory(),
    			solution.getCategoryDescription(),
    			solution.getSolutionType(),
    			solution.getSolutionInformation(),
    			solution.getComment()
    			);
	}
	
	@Override
	public List<EsSolution> findAll() {
		Iterable<EsSolution> iterable = knowledgeElasticSearchRepository.findAll();
		List<EsSolution> esSolutions = StreamSupport
			    .stream(iterable.spliterator(), false)
			    .collect(Collectors.toList());
		return esSolutions;
	}

	@Override
	public void refresh() {
		knowledgeElasticSearchRepository.refresh();
	}

	
}
