package fitnesse.fixtures;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.Format;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.StringUtilities;

public class AddAnswerCountBoundary {	
	private StringEventManager stringEventManager;

	public AddAnswerCountBoundary() {
		stringEventManager = new StringEventManager();
	}

	//	TODO:	Change to ...HasExistingBoundary()
	public void givenThatTheComponentWithIdHasExistingMaximum(String componentId, 
			int count){
		FormComponent found = Context.formComponentGateway.find(componentId);
		if (!FormComponentUtilities.isComponentNull(found))
			found.format.maxAnswers = count;
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
			return format.minAnswers;
		else if (Context.stringMatcher.matches(boundary, "maximum"))
			return format.maxAnswers;
		else
			throw new IllegalArgumentException(
					"Could not match boundary string " + boundary);
	}
	
}
