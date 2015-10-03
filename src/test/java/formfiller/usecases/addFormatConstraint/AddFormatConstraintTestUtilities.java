package formfiller.usecases.addFormatConstraint;

import formfiller.Context;
import formfiller.entities.answerFormat.OptionVariable;
import formfiller.entities.formComponent.FormComponent;

public class AddFormatConstraintTestUtilities {
	public static void addFormComponentToChange() {
		FormComponent toChange = new FormComponent();
		toChange.id = "toChange";
		toChange.format = new OptionVariable();
		Context.formComponentGateway.save(toChange);
	}
	
}
