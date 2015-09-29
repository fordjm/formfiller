package formfiller.request.builders;

import formfiller.entities.answerFormat.OptionVariable;
import formfiller.request.models.AddFormComponentRequest;

public class AddOptionVariableFormComponentRequestBuilder extends AddFormComponentRequestBuilder {
	public AddOptionVariableFormComponentRequestBuilder(){
		request = new AddFormComponentRequest();
	}
	
	public void buildName() {
		request.name = "AddOptionVariableFormComponent";
	}

	public void buildAnswerFormat() {
		OptionVariable format = new OptionVariable();
		request.format = format;
	}
	
}
