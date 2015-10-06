package formfiller.usecases.changeFormComponent;

import formfiller.entities.format.OptionVariable;

public class ChangeOptionVariableUseCase extends ChangeFormatUseCase {	
	public ChangeOptionVariableUseCase() {
		newFormat = new OptionVariable();
	}

}
