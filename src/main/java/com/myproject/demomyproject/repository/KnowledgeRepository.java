package com.myproject.demomyproject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myproject.demomyproject.model.Solution;

public interface KnowledgeRepository extends MongoRepository<Solution, Long>{

	Solution findBySolutionType(String type);

}
