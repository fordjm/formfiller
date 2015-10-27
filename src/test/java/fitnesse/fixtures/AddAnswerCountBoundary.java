package fitnesse.fixtures;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.Format;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.StringUtilities;

public class AddAnswerCountBoundary {	
	private final int DEFAULT_MINIMUM = 0;
	private final int DEFAULT_MAXIMUM = 1;
	private StringEventManager stringEventManager;

	public AddAnswerCountBoundary() {
		stringEventManager = new StringEventManager();
	}

	//	TODO:	Change to ...HasExistingBoundary()
	//			Refactor to fix duplication.
	public void givenThatTheComponentWithIdHasExistingMinimum(String componentId, 
			int count){
		if (count == DEFAULT_MINIMUM) return;
		FormComponent found = Context.formComponentGateway.find(componentId);
		if (!FormComponentUtilities.isComponentNull(found))
			found.format.setMinAnswers(count);
	}
	
	public void givenThatTheComponentWithIdHasExistingMaximum(String componentId, 
			int count){
		if (count == DEFAULT_MAXIMUM) return;
		FormComponent found = Context.formComponentGateway.find(componentId);
		if (!FormComponentUtilities.isComponentNull(found))
			found.format.setMaxAnswers(count);
	}
	
	public void addAnswerBoundary(int count, String boundary, String componentId){
		String countString = Integer.toString(count);
		String eventString = StringUtilities.makeSpacedString("AddAnsBnd", 
				componentId, boundary, countString);
		stringEventManager.updateHandler(eventString);
	}
	
	public boolean isBoundaryEqualTo(String componentId, String boundary, int count){
		FormComponent component = FormComponentUtilities.find(componentId);
		int boundaryValue = getBoundaryValue(component.format, boundary);
		return boundaryValue == count;
	}

	private int getBoundaryValue(Format format, String boundary) {
		if (Context.stringMatcher.matches(boundary, "minimum"))
			return format.getMinAnswers();
		else if (Context.stringMatcher.matches(boundary, "maximum"))
			return format.getMaxAnswers();
		else
			throw new IllegalArgumentException(
					"Could not match boundary string " + boundary);
	}
	
}
