package formfiller.usecases.addFormatConstraint;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.Format;

public class UnitTestSetupUtilities {
	public static void addFormComponentToChange(Format format) {
		FormComponent toChange = new FormComponent();
		toChange.id = "toChange";
		toChange.format = format;
		Context.formComponentGateway.save(toChange);
	}
	
}
