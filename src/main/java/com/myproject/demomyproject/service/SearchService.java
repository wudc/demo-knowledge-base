package com.myproject.demomyproject.service;

import java.util.List;

import com.myproject.demomyproject.model.Solution;
import com.myproject.demomyproject.model.elasticsearch.EsSolution;

public interface SearchService {
	public List<EsSolution> findByProjectName(String name);
	public List<EsSolution> findByCategory(String category);
	public List<EsSolution> findBySolutionType(String type);
	public void deleteSolution(Long id);
	public EsSolution save(Solution solution);
	public EsSolution update(Solution solution, Long esId);	
	public List<EsSolution> findAll();
	public void refresh();
}
