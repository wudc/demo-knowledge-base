package com.myproject.demomyproject.repository.elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.myproject.demomyproject.model.elasticsearch.EsSolution;

@Repository
public interface KnowledgeElasticSearchRepository extends ElasticsearchRepository<EsSolution,Long>{

	public List<EsSolution> findByProjectName(String name);
	public List<EsSolution> findByCategory(String category);
	public List<EsSolution> findBySolutionType(String type);
}
