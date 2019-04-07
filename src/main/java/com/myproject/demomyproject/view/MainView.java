package com.myproject.demomyproject.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.myproject.demomyproject.viewer.SolutionFormView;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

	@Autowired
	private SolutionFormView categoryForm;
	
	public MainView() {
		H1 header = new H1("Hello Ashburn");
		add(header);
	}
}
