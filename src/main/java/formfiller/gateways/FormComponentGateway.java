package formfiller.gateways;

import formfiller.entities.formComponent.FormComponent;

public interface FormComponentGateway extends Gateway<FormComponent> {	
	FormComponent find(String id);

	FormComponent remove(String componentId);
	
	void removeAll();
	
	void save(FormComponent formComponent);
}