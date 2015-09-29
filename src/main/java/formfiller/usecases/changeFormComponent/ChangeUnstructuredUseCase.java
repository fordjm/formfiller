package formfiller.usecases.changeFormComponent;

import formfiller.entities.answerFormat.Unstructured;

public class ChangeUnstructuredUseCase extends ChangeFormatUseCase {	
	public ChangeUnstructuredUseCase() {
		newFormat = new Unstructured();
	}
	
}
