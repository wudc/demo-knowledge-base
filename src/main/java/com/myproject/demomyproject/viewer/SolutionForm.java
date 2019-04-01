package com.myproject.demomyproject.viewer;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class SolutionForm {

	private TextField projectName;
	private TextField category;
	private TextField categoryDescription;
	private Select<String> solutionType;
	private TextField comment;
	private RichTextEditor solutionInformation;
	
	public SolutionForm() {}
	
	public Div buildForm() {
		FormLayout inputsFormLayout = new FormLayout();
        Div inputsFormWrapper = new Div();
        inputsFormWrapper.add(inputsFormLayout);

        // Inputs
        projectName = new TextField();
        projectName.setId("project-name");
        projectName.getElement().setAttribute("colspan", "2");
        projectName.setLabel("Project Name");
        projectName.setClassName("large");
        projectName.setValue("My Current Project");
        
        category = new TextField();
        category.setId("category");
        category.getElement().setAttribute("colspan", "2");
        category.setLabel("Category");
        category.setValue("SQL Injection");

        categoryDescription = new TextField();
        categoryDescription.setId("category-description");
        categoryDescription.getElement().setAttribute("colspan", "2");
        categoryDescription.setLabel("Category Description");
        categoryDescription.setValue("Attack against input from user can be executed via the Web Browser.");
        
        solutionType = new Select<>("Implementation", "Compensating Control", "False Positive");
        solutionType.setId("solution-type");
        solutionType.setValue("Compensating Control");
        solutionType.setLabel("Solution Type");
        
        comment = new TextField();
        comment.setId("comment");
        comment.getElement().setAttribute("colspan", "2");
        comment.setLabel("Comment");
        comment.setValue("Comment from others.");
                

        inputsFormLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0",1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("30em", 2));
        inputsFormLayout.setId("inputs");
        inputsFormLayout.add(projectName, category, categoryDescription, solutionType, comment);
        
        return inputsFormWrapper;
	}
	
	private RichTextEditor setSolutionInformationEditor() {
        solutionInformation = new RichTextEditor();
        solutionInformation.setThemeName("compact");
        String text = "[{\"attributes\":{\"bold\":true},\"insert\":\"Team lunch participants:\"},{\"insert\":\" Manolo, Joonas, and Matti\\nTraveling in Antwerp:\\nMetro from the hotel to the venue\"},{\"attributes\":{\"list\":\"bullet\"},\"insert\":\"\\n\"},{\"insert\":\"Taxi from the airport to the hotel\"},{\"attributes\":{\"list\":\"bullet\"},\"insert\":\"\\n\"}]";
        solutionInformation.setValue(text);

        solutionInformation.setHeight("90%");
        return solutionInformation;
	}
	

	public TextField getProjectName() {
		return projectName;
	}

	public void setProjectName(TextField projectName) {
		this.projectName = projectName;
	}

	public TextField getCategory() {
		return category;
	}

	public void setCategory(TextField category) {
		this.category = category;
	}

	public TextField getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(TextField categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public Select<String> getSolutionType() {
		return solutionType;
	}

	public void setSolutionType(Select<String> solutionType) {
		this.solutionType = solutionType;
	}

	public TextField getComment() {
		return comment;
	}

	public void setComment(TextField comment) {
		this.comment = comment;
	}
		
}
