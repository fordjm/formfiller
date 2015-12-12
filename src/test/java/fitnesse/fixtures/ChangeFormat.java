package fitnesse.fixtures;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.utilities.StringUtilities;

public class ChangeFormat {
	private ConsoleEventManager stringEventManager;
	
	public ChangeFormat() {
		stringEventManager = new ConsoleEventManager();
	}
	
	public void whenTheUserChangesTheFormatTo(String componentId, String format){
		String eventString = StringUtilities.makeSpacedString("ChgFmt", componentId, format);
		stringEventManager.updateHandler(eventString);
	}

	public String componentFormat(String componentId){
		FormComponent found = Context.formComponentGateway.find(componentId);
		if (found == NullFormComponents.NULL) 
			throw new IllegalArgumentException("Requested component not found at gateway.");
		return found.format.getName();
	}
	
}
