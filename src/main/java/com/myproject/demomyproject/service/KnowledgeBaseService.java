package com.myproject.demomyproject.service;

import java.util.List;

import com.myproject.demomyproject.model.Solution;

public interface KnowledgeBaseService {
	public Solution saveSolution(Solution solution);
	public Solution updateSolution(Solution solution);
	public Solution findById(Long id);
	public void deleteSolution(Long id);
	public List<Solution> findAll();
	public List<String> findAllCategory();
}
