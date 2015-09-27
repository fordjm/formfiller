package formfiller.utilities;

import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;

public class FormComponentUtilities {
	public static boolean isComponentNull(FormComponent component) {
		return component.equals(NullFormComponents.NULL);
	}
}
