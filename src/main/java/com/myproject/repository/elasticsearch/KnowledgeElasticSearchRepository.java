package com.myproject.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.myproject.model.elasticsearch.EsSolution;

public interface KnowledgeElasticSearchRepository extends ElasticsearchRepository<EsSolution,String>{

}
