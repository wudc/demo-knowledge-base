package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.TestDataRepository;
import com.myproject.demomyproject.model.Solution;
import com.myproject.demomyproject.repository.KnowledgeRepository;
import com.myproject.demomyproject.service.KnowledgeBaseService;
import com.myproject.demomyproject.service.impl.KnowledgeBaseServiceImpl;

@RunWith(SpringRunner.class)
public class KnowledgeBaseServiceTest {

	@TestConfiguration
	static class KnowledgeBaseServiceImplTestContextConfiguration {
		@Bean
		public KnowledgeBaseService knowledgeBaseService() {
			return new KnowledgeBaseServiceImpl();
		}
	}
	
	@Autowired
	private KnowledgeBaseService knowledgeBaseService;
	
	@MockBean
	private KnowledgeRepository knowledgeRepository;
	
	private Solution solution;
	private List<Solution> solutions;
	private TestDataRepository testDataRepository;
	
	@Before
	public void setUp() {
		testDataRepository = new TestDataRepository();
		solution = testDataRepository.getSolution();
		solutions = testDataRepository.getSolutions();
		
		Mockito.when(knowledgeRepository.save(solution)).thenReturn(solution);
		Mockito.when(knowledgeRepository.saveAll(solutions)).thenReturn(solutions);		
	}
	
	@Test
	public void testSaveSolution( ) {
		Solution savedSolution = knowledgeBaseService.saveSolution(solution);
		
		assertNotNull(savedSolution);
		assertEquals(savedSolution.getProjectName(), solution.getProjectName());
		assertEquals(savedSolution.getSolutionType(), solution.getSolutionType());
	}
	
	@Test
	public void testFindAllCategories() {
		List<String> categories = knowledgeBaseService.findAllCategory();
	}
}
