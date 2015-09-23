package formfiller.request.builders;

import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.request.models.AddUnstructuredFormComponentRequest;
import formfiller.request.models.Request;

public class AddUnstructuredFormComponentRequestBuilder implements RequestBuilderFunctions {
	AddUnstructuredFormComponentRequest request;
	
	public AddUnstructuredFormComponentRequestBuilder(){
		request = new AddUnstructuredFormComponentRequest();
	}
	
	public void buildName() {
		request.name = "AddUnstructuredFormComponent";
	}
	
	public void buildQuestionId(String questionId) {
		request.questionId = questionId;
	}
	
	public void buildQuestionContent(String questionContent) {
		request.questionContent = questionContent;
	}
	
	public void buildAnswerFormat(AnswerFormat format){
		request.format = format;
	}

	public Request getRequest() {
		return request;
	}
}
