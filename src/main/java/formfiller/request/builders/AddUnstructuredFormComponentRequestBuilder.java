package formfiller.request.builders;

import formfiller.entities.answerFormat.Unstructured;
import formfiller.request.models.AddUnstructuredFormComponentRequest;

public class AddUnstructuredFormComponentRequestBuilder extends AddFormComponentRequestBuilder {
	public AddUnstructuredFormComponentRequestBuilder(){
		request = new AddUnstructuredFormComponentRequest();
	}
	
	public void buildName() {
		request.name = "AddUnstructuredFormComponent";
	}
	
	public void buildAnswerFormat(){
		request.format = new Unstructured();
	}
	
}
