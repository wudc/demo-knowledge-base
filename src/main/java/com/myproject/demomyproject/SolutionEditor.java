package com.myproject.demomyproject;

import org.springframework.beans.factory.annotation.Autowired;

import com.myproject.demomyproject.viewer.Banner;
import com.myproject.demomyproject.viewer.SolutionForm;
import com.myproject.demomyproject.viewer.SolutionInformationEditor;
import com.myproject.demomyproject.viewer.SolutionSearch;
import com.myproject.demomyproject.model.Solution;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@SuppressWarnings("serial")
@Route("solution")
@HtmlImport("frontend://styles/shared-styles.html")
@HtmlImport("frontend://src/link-banner.html")
public class SolutionEditor extends Div {
	
	private SolutionForm categoryForm = new SolutionForm();
	
	private SolutionInformationEditor solutionInformationEditor = new SolutionInformationEditor();
		
    public SolutionEditor() {
        setId("container");
        
        //top banner
        this.getElement().appendChild(new Element("link-banner"));

        Banner banner = new Banner(categoryForm, solutionInformationEditor);
        Div controlsLine = banner.setupBanner();

        // Input parts layout
        Board board = new Board();

        //input form
        Div inputsFormWrapper = categoryForm.buildForm();

        //input texteditor
        Label editorLabel = new Label("Solution Description");
        editorLabel.setClassName("label-text");

        RichTextEditor solutionInformation = solutionInformationEditor.setSolutionInformationEditor();
        Div rteWrapper = new Div();
        rteWrapper.add(editorLabel, solutionInformation);

        board.addRow(inputsFormWrapper, rteWrapper);
        
        // Adds line
        Div addsLine = new Div();
        addsLine.setClassName("controls-line");

        // Buttons
        Div btnWrapper = new Div();
        btnWrapper.setClassName("flex-1");

//        Span cardTransactionText = new Span("Add credit card transaction");
//        Button addCardTransactionBtn = new Button(cardTransactionText);
//        addCardTransactionBtn.setThemeName("tertiary");
//        addCardTransactionBtn.setId("add-transaction");

        
        Button discardBtn = new Button("Discard changes", e -> Notification.show("Changes were discarded!"));
        discardBtn.setThemeName("error tertiary");

        Button saveDraftBtn = new Button("Save", e -> Notification.show("Changes were saved!"));
        saveDraftBtn.setThemeName("tertiary");

        btnWrapper.add(discardBtn, saveDraftBtn);
        addsLine.add(btnWrapper);

        //Result
        SolutionSearch search = new SolutionSearch();
        GridPro<Solution> grid = search.getSearchResult();
        
        Board gridBoard = new Board();
        Label gridLabel = new Label("Vulnerability Solution");
        gridLabel.setClassName("label-text");
       
        gridBoard.add(gridLabel, grid);
        
		add(controlsLine, board, addsLine, gridBoard);
    }

	public SolutionForm getCategoryForm() {
		return categoryForm;
	}

	public void setCategoryForm(SolutionForm categoryForm) {
		this.categoryForm = categoryForm;
	}
   
}
