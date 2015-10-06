package formfiller.usecases.changeFormComponent;

import formfiller.entities.format.Unstructured;

public class ChangeUnstructuredUseCase extends ChangeFormatUseCase {	
	public ChangeUnstructuredUseCase() {
		newFormat = new Unstructured();
	}
	
}
