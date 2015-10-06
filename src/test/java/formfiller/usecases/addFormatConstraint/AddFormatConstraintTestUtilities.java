package formfiller.usecases.addFormatConstraint;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.OptionVariable;

public class AddFormatConstraintTestUtilities {
	public static void addFormComponentToChange() {
		FormComponent toChange = new FormComponent();
		toChange.id = "toChange";
		toChange.format = new OptionVariable();
		Context.formComponentGateway.save(toChange);
	}
	
}
