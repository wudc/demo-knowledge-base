package com.myproject.demomyproject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myproject.demomyproject.model.Solution;

@Repository
public interface KnowledgeRepository extends MongoRepository<Solution, Long>{
}
