package formfiller.gateways;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;

public class InMemoryFormComponentGateway implements FormComponentGateway {	
	private Map<String, FormComponent> formComponents = new HashMap<String, FormComponent>();
	private List<String> orderedElements = new ArrayList<String>();
	
	public FormComponent findByIndex(int index){
		if (!isLegalIndex(index))
			return getIllegalIndexComponent(index);		
		return getFormComponentAtIndex(index);			
	}
	
	private boolean isLegalIndex(int requestedIndex) {
		return requestedIndex >= 0 && 
				requestedIndex < orderedElements.size();
	}
	
	private FormComponent getIllegalIndexComponent(int index){
		if (index < 0) 
			return NullFormComponents.START;
		else
			return NullFormComponents.END;
	}

	private FormComponent getFormComponentAtIndex(int index) {
		String id = orderedElements.get(index);
		return formComponents.get(id);
	}
	
	public FormComponent find(String id) {
		if (id == null) id = "";		
		return screenNullFindResults(id);
	}
	
	public FormComponent screenNullFindResults(String requestedId){
		FormComponent result = formComponents.get(requestedId);
		if (result == null) result = NullFormComponents.NULL;
		return result;
	}

	public void save(FormComponent formComponent) {
		if (formComponent == null || formComponent == NullFormComponents.NULL) 
			return;
		
		formComponents.put(formComponent.id, formComponent);
		orderedElements.add(formComponent.id);
	}
}
