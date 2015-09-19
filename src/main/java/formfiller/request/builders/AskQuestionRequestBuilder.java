package formfiller.request.builders;

import formfiller.enums.QuestionAsked;
import formfiller.request.models.AskQuestionRequest;
import formfiller.request.models.Request;

public class AskQuestionRequestBuilder implements RequestBuilderFunctions {
	AskQuestionRequest request;
	
	public AskQuestionRequestBuilder(){
		request = new AskQuestionRequest();
	}

	public void buildName() {
		request.name = "AskQuestion";
	}
	
	public void buildWhichQuestion(QuestionAsked which){
		request.which = which;
	}

	public Request getRequest() {
		return request;
	}
}
