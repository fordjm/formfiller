package formfiller.delivery.controller;

import java.util.List;

public class AddOptionVariableFormComponentController extends AddFormComponentController{
	private String options;	
	
	protected String getName() {
		return "AddOptionVariableFormComponent";
	}

	protected void assignRequiredParameters(List<String> parameters) {
		super.assignRequiredParameters(parameters);
		options = assignRequiredParameter(parameters, 3);
	}
	
	protected Arguments makeArguments() {
		Arguments result = super.makeArguments();
		result.add("options", options);		
		return result;
	}
}
