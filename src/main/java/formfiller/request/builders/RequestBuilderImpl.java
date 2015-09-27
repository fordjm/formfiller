package formfiller.request.builders;

import formfiller.delivery.controller.Arguments;
import formfiller.enums.QuestionAsked;
import formfiller.request.models.Request;

public class RequestBuilderImpl implements RequestBuilder {
	Arguments args;

	public Request build(String requestName, Arguments args) {
		this.args = args;
		
		if(requestName.equalsIgnoreCase("HandleUnfoundUseCase"))
			return buildHandleUnfoundControllerRequest();
		else if(requestName.equalsIgnoreCase("AddUnstructuredFormComponent"))
			return buildAddUnstructuredFormComponentRequest();
		else if(requestName.equalsIgnoreCase("AddOptionVariableFormComponent"))
			return buildAddOptionVariableFormComponentRequest();
		else if(requestName.equalsIgnoreCase("AskQuestion"))
			return buildAskQuestionRequest();
		else if(requestName.equalsIgnoreCase("ChangeId"))
			return buildChangeIdRequest();
		else if(requestName.equalsIgnoreCase("ChangeUnstructured"))
			return buildChangeUnstructuredRequest();
		else if(requestName.equalsIgnoreCase("DeleteFormComponent"))
			return buildDeleteFormComponentRequest();
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

	private Request buildChangeIdRequest() {
		ChangeIdRequestBuilder builder = new ChangeIdRequestBuilder();
		builder.buildOldId((String) args.getById("oldId"));
		builder.buildNewId((String) args.getById("newId"));
		return finishBuildingRequest(builder);
	}

	private Request buildChangeUnstructuredRequest() {
		ChangeUnstructuredRequestBuilder builder = 
				new ChangeUnstructuredRequestBuilder();
		builder.buildComponentId((String) args.getById("componentId"));
		return finishBuildingRequest(builder);
	}

	private Request buildDeleteFormComponentRequest() {
		DeleteFormComponentRequestBuilder builder = new DeleteFormComponentRequestBuilder();
		builder.buildComponentId((String) args.getById("componentId"));
		return finishBuildingRequest(builder);
	}
	
	private Request finishBuildingRequest(RequestBuilderFunctions builder){
		builder.buildName();
		return builder.getRequest();
	}
}
