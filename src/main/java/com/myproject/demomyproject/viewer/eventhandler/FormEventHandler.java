package com.myproject.demomyproject.viewer.eventhandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.myproject.demomyproject.model.Solution;
import com.myproject.demomyproject.service.KnowledgeBaseService;
import com.myproject.demomyproject.service.SearchService;

@Component
public class FormEventHandler {

	@Autowired
	@Qualifier("SearchServiceImpl")
	private SearchService searchService;

	@Autowired
	@Qualifier("KnowledgeBaseServiceImpl")
	private KnowledgeBaseService storageService;

	public void saveSolution(Solution solution) {

		System.out.println(
				"Calling service to save solution. FormEventHandler->saveSolution->searchService: " + searchService);
		Solution saveSolution = storageService.saveSolution(solution);
		System.out.println("Saved Solution id: " + saveSolution.getId());

		searchService.save(solution);
	}

	public void solutionFormInit() {
		searchService.refresh();
	}

	public void updateSolution(Solution solution, Long esId, String dbId) {
		solution.setId(dbId);
		storageService.updateSolution(solution);
		searchService.update(solution, esId);
	}

	public void getAllCategories() {
		//List<String> categories = storageService.findAllCategory();
		List<Solution> solutions = storageService.findAll();
		System.out.println("All Solution objects from MongoDB ---------------------");
		solutions.forEach(System.out::println);
		ArrayList<String> categories = new ArrayList<>();
		for (Solution solution : solutions) {
			categories.add(solution.getCategory());
		}
		
		System.out.println("All category from MongoDB ---------------------");
		categories.forEach(System.out::println);
	}
}
