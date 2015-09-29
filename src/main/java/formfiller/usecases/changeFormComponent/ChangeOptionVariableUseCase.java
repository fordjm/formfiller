package formfiller.usecases.changeFormComponent;

import formfiller.entities.answerFormat.OptionVariable;

public class ChangeOptionVariableUseCase extends ChangeFormatUseCase {	
	public ChangeOptionVariableUseCase() {
		newFormat = new OptionVariable();
	}

}
