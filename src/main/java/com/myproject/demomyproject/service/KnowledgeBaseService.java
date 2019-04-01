package com.myproject.demomyproject.service;

import com.myproject.demomyproject.model.Solution;

public interface KnowledgeBaseService {
	public Solution saveSolution(Solution solution);
	public Solution findById(Long id);
	public Solution findByType(String type);
	public void deleteSolution(Long id);
}
