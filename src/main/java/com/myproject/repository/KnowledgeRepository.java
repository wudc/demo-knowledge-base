package com.myproject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myproject.model.Solution;

public interface KnowledgeRepository extends MongoRepository<Solution, String>{

	Solution findByType(String type);

}
