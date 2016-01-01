package formfiller.gateways;

import formfiller.entities.formComponent.FormComponent;
/**
 * FormComponentGateway defines the interface for Form Component storage.  
 * Implement this interface to add a persistence mechanism (i.e. a database.)
 */
public interface FormComponentGateway extends Gateway<FormComponent> {	
	FormComponent find(String id);

	FormComponent remove(String componentId);
	
	void removeAll();
	
	void save(FormComponent formComponent);
}