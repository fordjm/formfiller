package formfiller.utilities;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;

public class FormComponentUtilities {
	public static FormComponent findFormComponent(String componentId){
		return FormFillerContext.formComponentGateway.find(componentId);
	}
	
	public static boolean isComponentNull(FormComponent component) {
		return component.equals(NullFormComponents.NULL);
	}
	
}
