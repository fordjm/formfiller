package formfiller.gateways;

import formfiller.entities.FormComponent;

public interface FormComponentGateway extends Gateway<FormComponent> {	
	FormComponent find(String id);
	
	void save(FormComponent formComponent);
}