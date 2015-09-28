package fitnesse.fixtures;

import formfiller.FormFillerContext;
import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;

public class ChangeUnstructured {
	private StringEventManager stringEventManager;
	
	public ChangeUnstructured() {
		stringEventManager = new StringEventManager();
	}
	
	public void whenTheUserChangesToUnstructured(String componentId){		
		stringEventManager.updateHandler("ChangeUns " + componentId);
	}
	
	public String componentFormat(String componentId){
		FormComponent found = FormFillerContext.formComponentGateway.find(componentId);
		if (found == NullFormComponents.NULL) 
			throw new IllegalArgumentException("Requested component not found at gateway.");
		return trimmedClassName(found.format);
	}

	private String trimmedClassName(AnswerFormat format) {
		Class<?> formatClass = format.getClass();
		String name = formatClass.getName();
		int startIndex = name.lastIndexOf('.') + 1;
		return name.substring(startIndex).toLowerCase();
	}
}
