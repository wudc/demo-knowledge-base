package com.myproject.demomyproject.viewer;

import com.myproject.demomyproject.model.Solution;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

@SuppressWarnings("serial")
public class SolutionFormView extends Board {

	private TextField projectName;
	private TextField category;
	private TextField categoryDescription;
	private Select<String> solutionType;
	private TextField comment;
	//private RichTextEditor editor;
	private TextArea editor;
	
	public SolutionFormView() {
		Div form = buildForm();
		Div editor = buildEditor();
		addRow(form, editor);
	}

	private Div buildForm() {
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
				new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
				new FormLayout.ResponsiveStep("30em", 2));
		inputsFormLayout.setId("inputs");
		inputsFormLayout.add(projectName, category, categoryDescription, solutionType, comment);

		return inputsFormWrapper;
	}

	private Div buildEditor() {
		Div editorWrapper = new Div();
		Label editorLabel = new Label("Solution Description");
		editorLabel.setClassName("label-text");

		//editor = new RichTextEditor();
		editor = new TextArea();
		editor.setLabel("Solution Description");
		editor.setThemeName("compact");
		String text = "[{\"attributes\":{\"bold\":true},\"insert\":\"Team lunch participants:\"},{\"insert\":\" Manolo, Joonas, and Matti\\nTraveling in Antwerp:\\nMetro from the hotel to the venue\"},{\"attributes\":{\"list\":\"bullet\"},\"insert\":\"\\n\"},{\"insert\":\"Taxi from the airport to the hotel\"},{\"attributes\":{\"list\":\"bullet\"},\"insert\":\"\\n\"}]";
		editor.setValue(text);

		editor.setHeight("85%");
		editor.setWidthFull();

		Div actionPanel = buildActionPanel();
		editorWrapper.add(editor, actionPanel);
		return editorWrapper;
	}

	private Div buildActionPanel() {
		// Adds line
		Div addsLine = new Div();
		addsLine.setClassName("controls-line");

		// Buttons
		Div btnWrapper = new Div();
		btnWrapper.setClassName("flex-1");

		Button discardBtn = new Button("Discard changes", e -> Notification.show("Changes were discarded!"));
		discardBtn.setThemeName("error tertiary");

		Button saveDraftBtn = new Button("Save", e -> Notification.show("Changes were saved!"));
		saveDraftBtn.setThemeName("tertiary");

		btnWrapper.add(discardBtn, saveDraftBtn);
		addsLine.add(btnWrapper);

		return addsLine;
	}
	
	public void updateFormUI(Solution solution) {
		projectName.setValue(solution.getProjectName());
		category.setValue(solution.getCategory());
		categoryDescription.setValue(solution.getCategoryDescription());
		solutionType.setValue(solution.getSolutionType());
		comment.setValue(solution.getComment());
		editor.setValue(solution.getSolutionInformation());
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

//	public RichTextEditor getEditor() {
//		return editor;
//	}
//
//	public void setEditor(RichTextEditor editor) {
//		this.editor = editor;
//	}
}
