package com.myproject.demomyproject.viewer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myproject.demomyproject.dataprovider.CategoryDataProvider;
import com.myproject.demomyproject.dataprovider.SolutionTypeDataProvider;
import com.myproject.demomyproject.model.Solution;
import com.myproject.demomyproject.model.elasticsearch.EsSolution;
import com.myproject.demomyproject.viewer.eventhandler.FormEventHandler;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
public class SolutionFormView extends Div {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FormEventHandler eventHandler;
	
	@Autowired
	private CategoryDataProvider categoryProvider;
	
	@Autowired
	private SolutionTypeDataProvider solutionTypeProvider;
	
	private TextField projectName;
	private ComboBox<String> category;
	private TextField categoryDescription;
	private ComboBox<String> solutionType;
	private TextField comment;
	private TextArea editor;
	private Long esId; 
	private String dbId;
	
	public void setupFormView( ) {
		Div form = buildForm();
		form.setWidth("50%");
		Div editor = buildEditor();
		editor.setWidth("50%");
		HorizontalLayout layout = new HorizontalLayout();
		layout.setPadding(true);
		layout.add(form, editor);
		add(layout);
		this.setWidthFull();
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
		//projectName.setClassName("large");
		projectName.setRequiredIndicatorVisible(isVisible());
		projectName.setAutofocus(true);

		//category should read from the db
		//List<String> categoryList = getCategoryList();
		category = new ComboBox<String>("Select or Add a Category");
		category.setAllowCustomValue(true);
		//category.setItems(categoryList);
		category.setId("category");
		category.getElement().setAttribute("colspan", "2");
		category.setLabel("Category");
		category.setRequired(true);
		category.addCustomValueSetListener(e -> {
			category.setValue(e.getDetail());
		});

		categoryDescription = new TextField();
		categoryDescription.setId("category-description");
		categoryDescription.getElement().setAttribute("colspan", "2");
		categoryDescription.setLabel("Category Description");
		categoryDescription.setRequired(true);

		//List<String> solutionTypeList = getSolutionTypeList();
		solutionType = new ComboBox<String>("Select or Add a Solution Type");
		//solutionType.setItems(solutionTypeList);
		solutionType.setAllowCustomValue(true);
		solutionType.setId("solution-type");
		solutionType.setLabel("Solution Type");
		solutionType.setRequired(true);
		solutionType.addCustomValueSetListener(e -> {
			solutionType.setValue(e.getDetail());
		});
		
		comment = new TextField();
		comment.setId("comment");
		comment.getElement().setAttribute("colspan", "2");
		comment.setLabel("Comment");

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

		editor = new TextArea();
		editor.setLabel("Solution Description");
		editor.setThemeName("compact");
		editor.setRequiredIndicatorVisible(isVisible());
		//String text = "[{\"attributes\":{\"bold\":true},\"insert\":\"Team lunch participants:\"},{\"insert\":\" Manolo, Joonas, and Matti\\nTraveling in Antwerp:\\nMetro from the hotel to the venue\"},{\"attributes\":{\"list\":\"bullet\"},\"insert\":\"\\n\"},{\"insert\":\"Taxi from the airport to the hotel\"},{\"attributes\":{\"list\":\"bullet\"},\"insert\":\"\\n\"}]";
		//editor.setValue(text);

		editor.setHeight("85%");
		editor.setWidthFull();
		VerticalLayout layout = new VerticalLayout();
		layout.add(editor);
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
		discardBtn.addClickListener(click -> {
			clearForm();
		});
		
		Button updateBtn = new Button("Update", e -> Notification.show("Changes were updated!"));
		updateBtn.setThemeName("secondary");
		updateBtn.addClickListener(click -> {updateForm();});
		
		Button saveDraftBtn = new Button("Save New", e -> Notification.show("Changes were saved!"));
		saveDraftBtn.setThemeName("primary");
		saveDraftBtn.addClickListener(click -> {saveForm();});

		btnWrapper.add(discardBtn, updateBtn, saveDraftBtn);
		addsLine.add(btnWrapper);

		return addsLine;
	}
	
	public void setSolutionType() {
		List<String> solutionTypeList = solutionTypeProvider.getSolutionTypes();
		solutionType.setItems(solutionTypeList);
	}
	
	public void setCategoryType() {
		List<String> categoryList = categoryProvider.getCategories();
		category.setItems(categoryList);
	}
		
	private void updateForm() {
		Solution solution = getCurrentFormData();
		eventHandler.updateSolution(solution, esId, dbId);
	}

	private void saveForm() {
		eventHandler.saveSolution(getCurrentFormData());
		
	}
	
	private Solution getCurrentFormData() {
		return new Solution(projectName.getValue(), 
				category.getValue(),
				categoryDescription.getValue(),
				solutionType.getValue(),
				editor.getValue(),
				comment.getValue());
	}

	private void clearForm() {
		projectName.setValue("");
		category.setValue("");
		categoryDescription.setValue("");
		solutionType.setValue("");
		comment.setValue("");
		editor.setValue("");		
	}
	
	public void updateFormUI(EsSolution solution) {
		projectName.setValue(solution.getProjectName());
		projectName.setRequired(true);
		category.setValue(solution.getCategory());
		categoryDescription.setValue(solution.getCategoryDescription());
		solutionType.setValue(solution.getSolutionType());
		comment.setValue(solution.getComment());
		editor.setValue(solution.getSolutionInformation());
		esId = solution.getId();
		dbId = solution.getSolutionId();
	}

}
