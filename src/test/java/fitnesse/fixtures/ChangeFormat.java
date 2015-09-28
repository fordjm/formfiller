package fitnesse.fixtures;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.utilities.StringUtilities;

public class ChangeFormat {
	private StringEventManager stringEventManager;
	
	public ChangeFormat() {
		stringEventManager = new StringEventManager();
	}
	
	public void whenTheUserChangesTheFormatTo(String componentId, String format){
		String command = getCommandString(format);
		String eventString = StringUtilities.makeSpacedString(command, componentId);
		stringEventManager.updateHandler(eventString);
	}
	
	private String getCommandString(String format) {
		if (format.equalsIgnoreCase("Unstructured"))
			return "ChgFmtU";
		else if (format.equalsIgnoreCase("OptionVariable"))
			return "ChgFmtV";
		throw new IllegalArgumentException(
				"Cannot change to unknown format " + format);
	}

	public String componentFormat(String componentId){
		FormComponent found = FormFillerContext.formComponentGateway.find(componentId);
		if (found == NullFormComponents.NULL) 
			throw new IllegalArgumentException("Requested component not found at gateway.");
		return found.format.getName();
	}
	
}
