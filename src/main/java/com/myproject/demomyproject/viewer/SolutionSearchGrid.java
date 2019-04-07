package com.myproject.demomyproject.viewer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.myproject.demomyproject.model.elasticsearch.EsSolution;
import com.myproject.demomyproject.service.KnowledgeBaseService;
import com.myproject.demomyproject.service.SearchService;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
public class SolutionSearchGrid {

	@Autowired
	@Qualifier("SearchServiceImpl")
	private SearchService searchService;
	
	@Autowired
	@Qualifier("KnowledgeBaseServiceImpl")
	private KnowledgeBaseService storageService;
	
	public GridPro<EsSolution> getSearchResult(Board form) {
		// Grid Pro
		GridPro<EsSolution> grid = new GridPro<>();
		//grid.setItems(createItems());
		grid.setItems(getAllSearchResults());
		grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_COMPACT);

		// populate the grid table for the default search result
		grid.addEditColumn(EsSolution::getProjectName)
				.text((item, newValue) -> displayNotification("ProjectName", item, newValue)).setHeader("Project");
		grid.addEditColumn(EsSolution::getCategory)
				.text((item, newValue) -> displayNotification("Category", item, newValue)).setHeader("Category");
		grid.addEditColumn(EsSolution::getCategoryDescription)
				.text((item, newValue) -> displayNotification("CategoryDescription", item, newValue))
				.setHeader("Description").setWidth("250px");
		grid.addEditColumn(EsSolution::getSolutionType)
				.text((item, newValue) -> displayNotification("SoltionType", item, newValue)).setHeader("Type");
		grid.addEditColumn(EsSolution::getSolutionInformation)
				.select((item, newValue) -> displayNotification("SoltionInformation", item, newValue))
				.setHeader("Soltion Information");
		grid.addEditColumn(EsSolution::getComment)
				.text((item, newValue) -> displayNotification("Comment", item, newValue)).setHeader("Comment");
		// grid.addComponentColumn(item -> createRemoveButton(grid,
		// item)).setWidth("70px").setFlexGrow(0).setTextAlign(ColumnTextAlign.CENTER);
		grid.getColumns().forEach(column -> column.setResizable(true).setSortable(true));

		// update form data with selected row
		grid.addSelectionListener(event -> {
			Optional<EsSolution> selected = event.getFirstSelectedItem();
			System.out.println("SolutionSearchGrid->getSearchResult->selected EsSolution: " + selected);
			if ( selected.isPresent()) {
				EsSolution solution = selected.get();
				Notification.show("Project name: " + solution.getProjectName() + " record selected.");
				SolutionFormView formView = (SolutionFormView) form;
				formView.updateFormUI(solution);
			}
		});

//        grid.addItemClickListener(
//                event -> Notification.show("Clicked Item: " + event.getItem().getSolutionInformation()));

		// You can use any renderer for the item details. By default, the
		// details are opened and closed by clicking the rows.
		grid.setItemDetailsRenderer(TemplateRenderer
				.<EsSolution>of(
						"<div style='border: 1px solid gray; padding: 10px; width: 100%; box-sizing: border-box;'>"
								+ "<div>ProjectName: <b>[[item.ProjectName]]</b></div>"
								+ "<div>Category: <b>[[item.Category]]</b></div>"
								+ "<div>SoltionType: <b>[[item.SoltionType]]</b></div>"
								+ "<div>SoltionInformation: <b>[[item.SoltionInformation]]</b></div>" + "</div>")
				.withProperty("ProjectName", EsSolution::getProjectName).withProperty("Category", EsSolution::getCategory)
				.withProperty("SoltionType", EsSolution::getSolutionType)
				.withProperty("SoltionInformation", EsSolution::getSolutionInformation)
				.withEventHandler("handleClick", solution -> {
					grid.getDataProvider().refreshItem(solution);
				}));
		return grid;
	}

	private static void displayNotification(String propertyName, EsSolution item, String newValue) {
		Notification.show(propertyName + " was changed to " + newValue + " for item: " + item.toString());
	}

	public void updateSearchResult() {
		
	}
//	private static List<Solution> createItems() {
//		Random random = new Random(0);
//		return IntStream.range(1, 5).mapToObj(index -> createSolution(index, random)).collect(Collectors.toList());
//	}
//
//	private static Solution createSolution(int index, Random random) {
//
//		Solution solution = new Solution();
//		//solution.setId(index);
//		solution.setProjectName("MyProject");
//		solution.setCategory("SQL Injection");
//		solution.setCategoryDescription("Attack against input from user can be executed via the Web Browser.");
//		solution.setSolutionType("Compensating Control");
//		solution.setSolutionInformation("Via database restriction remediation");
//		solution.setComment("Comment from others");
//
//		return solution;
//	}
	
	private List<EsSolution> getAllSearchResults() {
		List<EsSolution> esSolutions = searchService.findAll();
		esSolutions.forEach(System.out::println);
		
//		List<Solution> solutions = storageService.findAll();
//		solutions.forEach(System.out::println);
		
		return esSolutions ;
	}
}
