package formfiller.usecases.addFormComponent;

import formfiller.entities.format.Format;
import formfiller.entities.format.Unstructured;

public class AddUnstructuredFormComponentUseCase extends AddFormComponentUseCase {
	protected Format makeAnswerFormat() {
		return new Unstructured();
	}
	
}
