package com.example.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.model.TestDataRepository;
import com.example.demo.mongo.config.SpringMongoConfiguration;
import com.myproject.demomyproject.model.Solution;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {SpringMongoConfiguration.class})
public class KnowledgeRepositoryEmbedTest {
	
	private static Solution solution;
	private static List<Solution> solutions;
	private static TestDataRepository testDataRepository;
	
	@BeforeAll
	public static void init() {
		//given
		testDataRepository = new TestDataRepository();
		solution = testDataRepository.getSolution();
		solutions = testDataRepository.getSolutions();
	}
	
	
	@DisplayName("Given solution object to save"
			+ " when save object using MongoDB template"
			+ " Then object is saved")	
	@Test
	public void test(@Autowired MongoTemplate mongoTemplate) {
		//when
		Solution savedSolution = mongoTemplate.save(solution);
		
		//then
		assertNotNull(savedSolution);
		assertEquals(savedSolution.getProjectName(), solution.getProjectName());
		assertEquals(savedSolution.getSolutionType(), solution.getSolutionType());
	}
	
	@DisplayName("Given a list of solutions object to save"
			+ " when insertAll object using MongoDB template"
			+ " Then the objects list is saved")		
	@Test
	public void testSaveAllSolutions(@Autowired MongoTemplate mongoTemplate) {
		//when
		Iterable<Solution> savedSolutions = mongoTemplate.insertAll(solutions);
		
		//then
		ArrayList<Solution> items = new ArrayList<>();
		savedSolutions.forEach(items::add);
		
		assertNotNull(items.get(0));
		assertEquals(items.get(1).getProjectName(), solutions.get(1).getProjectName());
		assertEquals(items.get(2).getSolutionType(), solutions.get(2).getSolutionType());
	}

	
}
