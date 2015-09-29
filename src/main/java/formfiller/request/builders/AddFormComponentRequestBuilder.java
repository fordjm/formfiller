package formfiller.request.builders;

import formfiller.request.models.AddFormComponentRequest;
import formfiller.request.models.Request;

public abstract class AddFormComponentRequestBuilder implements RequestBuilderFunctions {
	protected AddFormComponentRequest request;
	
	public abstract void buildName();
	
	public abstract void buildAnswerFormat();
	
	public void buildQuestionId(String questionId) {
		request.questionId = questionId;
	}
	
	public void buildQuestionContent(String questionContent) {
		request.questionContent = questionContent;
	}

	public Request getRequest() {
		return request;
	}
	
}
