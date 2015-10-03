package formfiller.utilities;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;

public class FormComponentUtilities {
	public static FormComponent find(String componentId){
		return Context.formComponentGateway.find(componentId);
	}
	
	public static boolean isComponentNull(FormComponent component) {
		return component.equals(NullFormComponents.NULL);
	}
	
}
