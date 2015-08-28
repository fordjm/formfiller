package formfiller.gateways;

import java.util.HashMap;
import java.util.Map;

import formfiller.entities.FormComponent;

public class InMemoryFormComponentGateway implements Gateway<FormComponent> {
	Map<String, FormComponent> formComponents = new HashMap<String, FormComponent>();

	public FormComponent find(String id) {
		if (id == null) throw new NullFind();
		
		return formComponents.get(id);
	}

	public void save(FormComponent formComponent) {
		if (formComponent == null) throw new NullSave();
		
		formComponents.put(formComponent.id, formComponent);
	}

	public class NullFind extends RuntimeException { }

	public class NullSave extends RuntimeException { }
}
