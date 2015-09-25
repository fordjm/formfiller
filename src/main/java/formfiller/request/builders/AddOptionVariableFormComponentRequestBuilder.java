package formfiller.request.builders;

import java.util.ArrayList;
import java.util.Arrays;

import formfiller.entities.answerFormat.OptionVariable;
import formfiller.request.models.AddOptionVariableFormComponentRequest;

public class AddOptionVariableFormComponentRequestBuilder extends AddFormComponentRequestBuilder {
	private String options;

	public AddOptionVariableFormComponentRequestBuilder(String options){
		request = new AddOptionVariableFormComponentRequest();
		this.options = options;
	}
	
	public void buildName() {
		request.name = "AddOptionVariableFormComponent";
	}

	public void buildAnswerFormat() {
		OptionVariable format = new OptionVariable();
		format.options = new ArrayList<Object>(Arrays.asList(options.split(",")));
		request.format = format;
	}
}
