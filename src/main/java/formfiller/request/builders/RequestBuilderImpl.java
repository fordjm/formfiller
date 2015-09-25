package formfiller.request.builders;

import formfiller.delivery.controller.Arguments;
import formfiller.enums.QuestionAsked;
import formfiller.request.models.Request;

public class RequestBuilderImpl implements RequestBuilder {
	Arguments args;

	public Request build(String requestName, Arguments args) {
		this.args = args;
		
		if(requestName.equalsIgnoreCase("handleUnfoundUseCase"))
			return buildHandleUnfoundControllerRequest();
		else if(requestName.equalsIgnoreCase("addUnstructuredFormComponent"))
			return buildAddUnstructuredFormComponentRequest();
		else if(requestName.equalsIgnoreCase("addOptionVariableFormComponent"))
			return buildAddOptionVariableFormComponentRequest();
		else if(requestName.equalsIgnoreCase("askQuestion"))
			return buildAskQuestionRequest();
		else
			return Request.NULL;
	}

	private Request buildHandleUnfoundControllerRequest() {
		HandleUnfoundUseCaseRequestBuilder builder = 
				new HandleUnfoundUseCaseRequestBuilder();
		builder.buildMessage((String) args.getById("message"));
		return finishBuildingRequest(builder);
	}

	private Request buildAddUnstructuredFormComponentRequest() {
		AddFormComponentRequestBuilder builder = 
				new AddUnstructuredFormComponentRequestBuilder();

		return buildAddFormComponentRequest(builder);
	}

	private Request buildAddOptionVariableFormComponentRequest() {
		AddFormComponentRequestBuilder builder = 
				new AddOptionVariableFormComponentRequestBuilder(
						(String) args.getById("options"));
		return buildAddFormComponentRequest(builder);
	}

	private Request buildAddFormComponentRequest(AddFormComponentRequestBuilder builder) {
		builder.buildQuestionId((String) args.getById("questionId"));
		builder.buildQuestionContent((String) args.getById("questionContent"));
		builder.buildAnswerFormat();
		return finishBuildingRequest(builder);
	}
	
	private Request buildAskQuestionRequest() {
		AskQuestionRequestBuilder builder = new AskQuestionRequestBuilder();
		builder.buildWhichQuestion((QuestionAsked) args.getById("which")); 
		return finishBuildingRequest(builder);
	}
	
	private Request finishBuildingRequest(RequestBuilderFunctions builder){
		builder.buildName();
		return builder.getRequest();
	}
}
