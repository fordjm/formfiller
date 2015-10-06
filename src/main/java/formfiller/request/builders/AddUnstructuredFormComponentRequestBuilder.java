package formfiller.request.builders;

import formfiller.entities.format.Unstructured;
import formfiller.request.models.AddFormComponentRequest;

public class AddUnstructuredFormComponentRequestBuilder extends AddFormComponentRequestBuilder {
	public AddUnstructuredFormComponentRequestBuilder(){
		request = new AddFormComponentRequest();
	}
	
	public void buildName() {
		request.name = "AddUnstructuredFormComponent";
	}
	
	public void buildAnswerFormat(){
		request.format = new Unstructured();
	}
	
}
