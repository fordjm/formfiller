package formfiller.gateways;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import formfiller.entities.FormComponent;

public class InMemoryFormComponentGateway implements FormComponentGateway {
	public final InMemoryTransporter transporter = new InMemoryTransporter();	
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
			return FormComponent.START;
		else
			return FormComponent.END;
	}

	private FormComponent getFormComponentAtIndex(int index) {
		String id = orderedElements.get(index);
		return formComponents.get(id);
	}

	public Transporter getTransporter(){
		return transporter;
	}
	
	public FormComponent find(String id) {
		if (id == null) id = "";
		
		return formComponents.get(id);
	}

	public void save(FormComponent formComponent) {
		if (formComponent == null) return;
		
		formComponents.put(formComponent.id, formComponent);
		orderedElements.add(formComponent.id);
	}
}
