package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.entities.FormComponent;

public class FormComponentMocker {

	public static FormComponent makeMockFormComponent(String id) {
		FormComponent result = Mockito.mock(FormComponent.class);
		result.id = id;
		return result;
	}
}
