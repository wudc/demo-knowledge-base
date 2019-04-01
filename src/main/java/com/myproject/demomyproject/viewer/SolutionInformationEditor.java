package com.myproject.demomyproject.viewer;

import com.vaadin.flow.component.richtexteditor.RichTextEditor;

public class SolutionInformationEditor {
	private RichTextEditor solutionInformation;
	
	public SolutionInformationEditor() {}
	
	public RichTextEditor setSolutionInformationEditor() {
        solutionInformation = new RichTextEditor();
        solutionInformation.setThemeName("compact");
        String text = "[{\"attributes\":{\"bold\":true},\"insert\":\"Team lunch participants:\"},{\"insert\":\" Manolo, Joonas, and Matti\\nTraveling in Antwerp:\\nMetro from the hotel to the venue\"},{\"attributes\":{\"list\":\"bullet\"},\"insert\":\"\\n\"},{\"insert\":\"Taxi from the airport to the hotel\"},{\"attributes\":{\"list\":\"bullet\"},\"insert\":\"\\n\"}]";
        solutionInformation.setValue(text);

        solutionInformation.setHeight("90%");
        return solutionInformation;
	}
	
	public String getSolutionInformation() {
		if ( solutionInformation != null ) {
			return solutionInformation.getValue();
		}
		else {
			return "";
		}
	}
}
