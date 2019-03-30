package com.myproject.demomyproject.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.myproject.demomyproject.model.elasticsearch.EsSolution;

public interface KnowledgeElasticSearchRepository extends ElasticsearchRepository<EsSolution,String>{

}
