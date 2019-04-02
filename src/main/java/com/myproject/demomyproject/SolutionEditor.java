package com.myproject.demomyproject;

import com.myproject.demomyproject.model.Solution;
import com.myproject.demomyproject.viewer.Banner;
import com.myproject.demomyproject.viewer.SolutionFormView;
import com.myproject.demomyproject.viewer.SolutionInformationEditor;
import com.myproject.demomyproject.viewer.SolutionSearch;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
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
	
	private SolutionFormView categoryForm = new SolutionFormView();
	
	private SolutionInformationEditor solutionInformationEditor = new SolutionInformationEditor();
		
    public SolutionEditor() {
        setId("container");
        
        //top banner
        this.getElement().appendChild(new Element("link-banner"));

        Banner banner = new Banner(categoryForm, solutionInformationEditor);
        Div controlsLine = banner.setupBanner();

        // Input parts layout
        Board board = new SolutionFormView();

        //input form
        Label separator = new Label();
        separator.setClassName("horizontal-separator");
        separator.setHeight("1px");
        
        //Grid Result 
        SolutionSearch search = new SolutionSearch();
        GridPro<Solution> grid = search.getSearchResult(board);
        Board gridBoard = new Board();
        Label gridLabel = new Label("Vulnerability Solution");
        gridLabel.setClassName("label-text");
        gridBoard.add(gridLabel, grid);
        
		add(controlsLine, board, gridBoard);
    }

	public SolutionFormView getCategoryForm() {
		return categoryForm;
	}

	public void setCategoryForm(SolutionFormView categoryForm) {
		this.categoryForm = categoryForm;
	}
   
}
