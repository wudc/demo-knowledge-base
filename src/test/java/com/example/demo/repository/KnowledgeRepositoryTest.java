package com.example.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.TestDataRepository;
import com.myproject.demomyproject.DemoMyprojectApplication;
import com.myproject.demomyproject.model.Solution;
import com.myproject.demomyproject.repository.KnowledgeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {DemoMyprojectApplication.class})
public class KnowledgeRepositoryTest {
	
	@Autowired
	private KnowledgeRepository knowledgeRepository;
	
	private Solution solution;
	private List<Solution> solutions;
	private TestDataRepository testDataRepository;
	
	@Before
	public void setUp() {
		testDataRepository = new TestDataRepository();
		solution = testDataRepository.getSolution();
		solutions = testDataRepository.getSolutions();
	}

	@Test
	public void testSaveSolution() {
		Solution savedSolution = knowledgeRepository.insert(solution);
		assertNotNull(savedSolution);
		assertEquals(savedSolution.getProjectName(), solution.getProjectName());
		assertEquals(savedSolution.getSolutionType(), solution.getSolutionType());
	}
	
	@Test
	public void testSaveAllSolutions() {
		Iterable<Solution> savedSolutions = knowledgeRepository.saveAll(solutions);
		
		ArrayList<Solution> items = new ArrayList<>();
		savedSolutions.forEach(items::add);
		
		assertNotNull(items.get(0));
		assertEquals(items.get(1).getProjectName(), solutions.get(1).getProjectName());
		assertEquals(items.get(2).getSolutionType(), solutions.get(2).getSolutionType());
	}
	

}
