package com.myproject.demomyproject.viewer;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;

@Component
@UIScope
public class Banner {
	
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
			
}
