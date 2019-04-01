package com.myproject.demomyproject.viewer;

import org.springframework.beans.factory.annotation.Autowired;

import com.myproject.demomyproject.model.Solution;
import com.myproject.demomyproject.service.SolutionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class Banner {
	@Autowired
	private SolutionService solutionService;
	
	private SolutionForm categoryForm;
	private SolutionInformationEditor solutionInformation;
	
	public Banner(SolutionForm form, SolutionInformationEditor editor) {
		this.categoryForm = form;
		this.solutionInformation = editor;
	}
	
	public Div setupBanner() {
        // Controls part
        Div controlsLine = new Div();
        controlsLine.addClassName("controls-line");

        // Content
        HorizontalLayout detailsWrapper = new HorizontalLayout();
        detailsWrapper.getThemeList().add("padding");
        detailsWrapper.getThemeList().remove("spacing");
        detailsWrapper.setClassName("invoice-details");

        Span solutionNameHeader = new Span("Web Application Vulnerability Knowledge Repository");
        solutionNameHeader.setClassName("large");
        detailsWrapper.add(solutionNameHeader);

        // Buttons
//        HorizontalLayout buttonsWrapper = new HorizontalLayout();
//        buttonsWrapper.getThemeList().remove("spacing");
//        buttonsWrapper.addClassName("controls-line-buttons");
//        
//        Button discardBtn = new Button("Discard changes", e -> Notification.show("Changes were discarded!"));
//        discardBtn.setThemeName("error tertiary");
//
//        Button saveDraftBtn = new Button("Save draft", e -> Notification.show("Changes were saved!"));
//        saveDraftBtn.setThemeName("tertiary");
//
//        Button sendBtn = new Button("Save", e -> Notification.show("Entry was saved!"));
//        sendBtn.setThemeName("primary");
//        sendBtn.addClickListener(click -> {
//			saveSolution();
//		});
//        
//        buttonsWrapper.add(discardBtn, saveDraftBtn, sendBtn);

        controlsLine.add(detailsWrapper);
        
        return controlsLine;
	}
	
	private void saveSolution() {
		TextField project = categoryForm.getProjectName();
		TextField category = categoryForm.getCategory();
		TextField categoryDescription = categoryForm.getCategoryDescription();
		Select<String> solutionType = categoryForm.getSolutionType();
		TextField comment = categoryForm.getComment();

		Solution solution = 
				new Solution(project.getValue(), 
						category.getValue(),
						categoryDescription.getValue(),
						solutionType.getValue(),
						solutionInformation.getSolutionInformation(),
						comment.getValue());
		
		//solutionService.save(solution);
	}

	public SolutionForm getCategoryForm() {
		return categoryForm;
	}

	public void setCategoryForm(SolutionForm categoryForm) {
		this.categoryForm = categoryForm;
	}

	public SolutionInformationEditor getSolutionInformation() {
		return solutionInformation;
	}

	public void setSolutionInformation(SolutionInformationEditor solutionInformation) {
		this.solutionInformation = solutionInformation;
	}
		
}
