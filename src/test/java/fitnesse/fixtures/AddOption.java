package fitnesse.fixtures;

import formfiller.entities.answerFormat.OptionVariable;
import formfiller.entities.formComponent.FormComponent;
import formfiller.utilities.FormComponentUtilities;

public class AddOption {
	//	TODO:	Move functionality to proper classes.
	public void addOption(String componentId, String option){
		executeTemporaryAddOptionCode(componentId, option);
	}
	
	private void executeTemporaryAddOptionCode(String componentId, String option) {
		FormComponent component = FormComponentUtilities.findFormComponent(componentId);
		OptionVariable format = (OptionVariable) component.format;
		format.addOption(option);
	}

	public boolean hasOption(String componentId, String option){
		FormComponent component = FormComponentUtilities.findFormComponent(componentId);
		OptionVariable format = (OptionVariable) component.format;
		return format.options.contains(option);
	}
	
}
