package com.myproject.demomyproject.view;

import com.myproject.demomyproject.model.elasticsearch.EsSolution;
import com.myproject.demomyproject.viewer.Banner;
import com.myproject.demomyproject.viewer.SolutionFormView;
import com.myproject.demomyproject.viewer.SolutionSearchGrid;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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

        setId("container");
        
        //top banner
        this.getElement().appendChild(new Element("link-banner"));

        Banner banner = new Banner();
        Div controlsLine = banner.setupBanner();
        
        // Input parts layout
        //Board board = categoryForm;
        categoryForm.setupFormView();
        //categoryForm.fetchCategory();
        categoryForm.setCategoryType();
        categoryForm.setSolutionType();

        //input form
        Label separator = new Label();
        separator.setClassName("horizontal-separator");
        separator.setHeight("1px");
        
        //Grid Result 
        VerticalLayout layout = new VerticalLayout();
        Grid<EsSolution> grid = search.getSearchResult(categoryForm);
        //Div gridBoard = new Div();
        Label gridLabel = new Label("Vulnerability Solution");
        gridLabel.setClassName("label-text");
        //gridBoard.add(gridLabel, grid);
        layout.add(gridLabel, grid);
        layout.setWidthFull();
        layout.setPadding(true);
        
		add(controlsLine, categoryForm, layout);
    }   
}
