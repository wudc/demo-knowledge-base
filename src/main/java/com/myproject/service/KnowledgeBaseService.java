package com.myproject.service;

import com.myproject.model.Solution;

public interface KnowledgeBaseService {
	public Solution saveSolution(Solution solution);
	public Solution findById(String id);
	public Solution findByType(String type);
	public void deleteSolution(String id);
}
