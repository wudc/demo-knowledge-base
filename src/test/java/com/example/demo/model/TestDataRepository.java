package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.myproject.demomyproject.model.Solution;

public class TestDataRepository {
	public Solution getSolution() {
		return new Solution("Project1", "Path Manipulation", "Attach on path", "Implementation",
				"Fixed path function", "Test only with rollback");
	}
	
	public List<Solution> getSolutions() {
		ArrayList<Solution> solutions = new ArrayList<>();
		solutions.add(new Solution("Project1", "Path Manipulation", "Attach on path", "Implementation",
				"Fixed path function", "Test only with rollback"));
		solutions.add(new Solution("Project2", "Password Manipulation", "Attach on password", "False Positive",
				"Fixed password function", "Test only with rollback"));
		solutions.add(new Solution("Project3", "JSON Manipulation", "Attach on JSON data", "Compensating Control",
				"Fixed JSON function", "Test only with rollback"));
		
		return solutions;
		
	}
}
