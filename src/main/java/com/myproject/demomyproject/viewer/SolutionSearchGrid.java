package com.myproject.demomyproject.viewer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.myproject.demomyproject.model.elasticsearch.EsSolution;
import com.myproject.demomyproject.service.KnowledgeBaseService;
import com.myproject.demomyproject.service.SearchService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
public class SolutionSearchGrid extends Div {

	@Autowired
	@Qualifier("SearchServiceImpl")
	private SearchService searchService;

	@Autowired
	@Qualifier("KnowledgeBaseServiceImpl")
	private KnowledgeBaseService storageService;

	public Grid<EsSolution> getSearchResult(Div form) {
		// Grid
		Grid<EsSolution> grid = new Grid<>();

		// populate the grid table for the default search result
		grid.setItems(getAllSearchResults());
		grid.addColumn(EsSolution::getProjectName).setHeader("Project");
		grid.addColumn(EsSolution::getCategory).setHeader("Category");
		grid.addColumn(EsSolution::getCategoryDescription).setHeader("Description").setWidth("250px");
		grid.addColumn(EsSolution::getSolutionType).setHeader("Type");
		grid.addColumn(EsSolution::getSolutionInformation).setHeader("Solution Information");
		grid.addColumn(EsSolution::getComment).setHeader("Comment");
		
		grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_COMPACT);
		grid.getColumns().forEach(column -> column.setResizable(true).setSortable(true));

		// update form data with selected row
		grid.addSelectionListener(event -> {
			Optional<EsSolution> selected = event.getFirstSelectedItem();
			//System.out.println("SolutionSearchGrid->getSearchResult->selected EsSolution: " + selected);
			if (selected.isPresent()) {
				EsSolution solution = selected.get();
				Notification.show("Project name: " + solution.getProjectName() + " record selected.");
				SolutionFormView formView = (SolutionFormView) form;
				formView.updateFormUI(solution);
			}
		});

		return grid;
	}

	public void updateSearchResult() {

	}

	private List<EsSolution> getAllSearchResults() {
		List<EsSolution> esSolutions = searchService.findAll();
		return esSolutions;
	}
}
