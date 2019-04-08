package com.myproject.demomyproject.view;

import com.myproject.demomyproject.model.elasticsearch.EsSolution;
import com.myproject.demomyproject.viewer.Banner;
import com.myproject.demomyproject.viewer.SolutionFormView;
import com.myproject.demomyproject.viewer.SolutionSearchGrid;
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
@Route("solution")
@HtmlImport("frontend://styles/shared-styles.html")
@HtmlImport("frontend://src/link-banner.html")
public class SolutionEditor extends Div {
	private static final long serialVersionUID = 1L;
    public SolutionEditor(SolutionFormView categoryForm, SolutionSearchGrid search) {
    	System.out.println("SolutionEditor->categoryForm" + categoryForm);
        setId("container");
        
        //top banner
        this.getElement().appendChild(new Element("link-banner"));

        Banner banner = new Banner();
        Div controlsLine = banner.setupBanner();
        
        // Input parts layout
        Board board = categoryForm;
        categoryForm.setupFormView();
        //categoryForm.fetchCategory();
        categoryForm.setCategoryType();
        categoryForm.setSolutionType();

        //input form
        Label separator = new Label();
        separator.setClassName("horizontal-separator");
        separator.setHeight("1px");
        
        //Grid Result 
        GridPro<EsSolution> grid = search.getSearchResult(board);
        Board gridBoard = new Board();
        Label gridLabel = new Label("Vulnerability Solution");
        gridLabel.setClassName("label-text");
        gridBoard.add(gridLabel, grid);
        
		add(controlsLine, board, gridBoard);
    }   
}
