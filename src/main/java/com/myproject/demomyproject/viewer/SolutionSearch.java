package com.myproject.demomyproject.viewer;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.myproject.demomyproject.model.Solution;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.notification.Notification;

public class SolutionSearch {

	public GridPro<Solution> getSearchResult() {
		// Grid Pro
        GridPro<Solution> grid = new GridPro<>();
        grid.setItems(createItems());
        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_COMPACT);

        //populate the grid table for the default search result
        grid.addEditColumn(Solution::getProjectName).text((item, newValue) -> displayNotification("ProjectName", item, newValue)).setHeader("Project");
        grid.addEditColumn(Solution::getCategory).text((item, newValue) -> displayNotification("Category", item, newValue)).setHeader("Category");
        grid.addEditColumn(Solution::getCategoryDescription).text((item, newValue) -> displayNotification("CategoryDescription", item, newValue)).setHeader("Description").setWidth("250px");
        grid.addEditColumn(Solution::getSolutionType).text((item, newValue) -> displayNotification("SoltionType", item, newValue)).setHeader("Type");
        grid.addEditColumn(Solution::getSolutionInformation).select((item, newValue) -> displayNotification("SoltionInformation", item, newValue)).setHeader("Soltion Information");
        grid.addEditColumn(Solution::getComment).text((item, newValue) -> displayNotification("Comment", item, newValue)).setHeader("Comment");
        //grid.addComponentColumn(item -> createRemoveButton(grid, item)).setWidth("70px").setFlexGrow(0).setTextAlign(ColumnTextAlign.CENTER);
        grid.getColumns().forEach(column -> column.setResizable(true).setSortable(true));

        return grid;
	}
    
    private static void displayNotification(String propertyName, Solution item, String newValue) {
        Notification.show(
                propertyName + " was changed to " + newValue + " for item: " + item.toString());
    }

    private static List<Solution> createItems() {
        Random random = new Random(0);
        return IntStream.range(1, 5)
                .mapToObj(index -> createSolution(index, random))
                .collect(Collectors.toList());
    }
    
    private static Solution createSolution(int index, Random random) {
        Solution solution = new Solution();
        solution.setId(new Long(index));
        solution.setProjectName("MyProject");
        solution.setCategory("SQL Injection");
        solution.setCategoryDescription("Attack against input from user can be executed via the Web Browser.");
        solution.setSolutionType("Compensating Control");
        solution.setSolutionInformation("Via database restriction remediation");
        solution.setComment("Comment from others");

        return solution;
    }
}
