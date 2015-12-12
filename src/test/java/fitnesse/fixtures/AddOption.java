package fitnesse.fixtures;

import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.OptionVariable;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.StringUtilities;

public class AddOption {
	private ConsoleEventManager stringEventManager;

	public AddOption() {
		stringEventManager = new ConsoleEventManager();
	}
	
	public void addOption(String componentId, String option){
		String eventString = StringUtilities.makeSpacedString("AddOpt", 
				componentId, option);
		stringEventManager.updateHandler(eventString);
	}

	public boolean hasOption(String componentId, String option){
		FormComponent component = FormComponentUtilities.find(componentId);
		OptionVariable format = (OptionVariable) component.format;
		return format.options.contains(option);
	}
	
}
