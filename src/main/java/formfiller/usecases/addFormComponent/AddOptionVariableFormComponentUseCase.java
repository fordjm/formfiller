package formfiller.usecases.addFormComponent;

import formfiller.entities.format.Format;
import formfiller.entities.format.OptionVariable;

public class AddOptionVariableFormComponentUseCase extends AddFormComponentUseCase {	
	protected Format makeAnswerFormat() {
		return new OptionVariable();
	}
	
}
