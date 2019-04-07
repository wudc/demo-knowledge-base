package com.myproject.demomyproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

//import com.myproject.demomyproject.model.elasticsearch.EsSolution;
//import com.myproject.demomyproject.service.impl.SearchServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoMyprojectApplicationTests {

//	@Autowired
//    private SearchServiceImpl searchServiceImpl;
//
//    @Autowired
//    private ElasticsearchTemplate esTemplate;
//    
//    @Before
//    public void before() {
//        esTemplate.deleteIndex(EsSolution.class);
//        esTemplate.createIndex(EsSolution.class);
//        esTemplate.putMapping(EsSolution.class);
//        esTemplate.refresh(EsSolution.class);
//    }   
//    
//    @Test
//    public void testSave() {
//    	EsSolution solution = new EsSolution("1001", "Knowledge base project",
//    			"SQL Injection", "Data Injection to database",
//    			"Implementation", "Validation user input", "Valid implementation"
//    			);
//    	EsSolution savedSolution = searchServiceImpl.save(solution);
//    	
//        assertNotNull(savedSolution.getId());
//        assertEquals(savedSolution.getProjectName(), solution.getProjectName());
//        assertEquals(savedSolution.getCategory(), solution.getCategory());
//        assertEquals(savedSolution.getCategoryDescription(), solution.getCategoryDescription());
//        assertEquals(savedSolution.getSolutionType(), solution.getSolutionType());
//        assertEquals(savedSolution.getSolutionInformation(), solution.getSolutionInformation());
//
//    }
//    
    
	@Test
	public void contextLoads() {
	}

}
