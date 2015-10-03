package formfiller.request.builders;

import formfiller.delivery.controller.Arguments;
import formfiller.enums.QuestionAsked;
import formfiller.request.models.Request;

//	TODO:	Move all these functions into one Builder interface.
public class RequestBuilderImpl implements RequestBuilder {
	private Arguments args;

	public Request build(String requestName, Arguments args) {
		this.args = args;
		
		if(requestName.equalsIgnoreCase("HandleUnfoundUseCase"))
			return buildHandleUnfoundControllerRequest();
		else if(requestName.equalsIgnoreCase("AddAnswerCountBoundary"))
			return buildAddAnswerCountBoundaryRequest();
		else if(requestName.equalsIgnoreCase("AddOption"))
			return buildAddOptionRequest();
		else if(requestName.equalsIgnoreCase("AddOptionVariableFormComponent"))
			return buildAddOptionVariableFormComponentRequest();
		else if(requestName.equalsIgnoreCase("AddUnstructuredFormComponent"))
			return buildAddUnstructuredFormComponentRequest();
		else if(requestName.equalsIgnoreCase("AskQuestion"))
			return buildAskQuestionRequest();
		else if(requestName.equalsIgnoreCase("ChangeId"))
			return buildChangeIdRequest();
		else if(requestName.equalsIgnoreCase("ChangeUnstructured"))
			return buildChangeUnstructuredRequest();
		else if(requestName.equalsIgnoreCase("ChangeOptionVariable"))
			return buildChangeOptionVariableRequest();
		else if(requestName.equalsIgnoreCase("DeleteFormComponent"))
			return buildDeleteFormComponentRequest();
		else
			return Request.NULL;
	}

	private Request buildAddAnswerCountBoundaryRequest() {
		AddAnswerCountBoundaryRequestBuilder builder = 
				new AddAnswerCountBoundaryRequestBuilder();
		builder.buildComponentId((String) args.getById("componentId"));
		builder.buildBoundary((String) args.getById("boundary"));
		builder.buildCount((Integer) args.getById("count"));
		
		return finishBuildingRequest(builder);
	}

	private Request buildAddOptionRequest() {
		AddOptionRequestBuilder builder = new AddOptionRequestBuilder();
		builder.buildComponentId((String) args.getById("componentId"));
		builder.buildOption((String) args.getById("option"));		
		return finishBuildingRequest(builder);
	}
	
	private Request finishBuildingRequest(RequestBuilderFunctions builder){
		builder.buildName();
		return builder.getRequest();
	}

	private Request buildAddUnstructuredFormComponentRequest() {
		AddFormComponentRequestBuilder builder = 
				new AddUnstructuredFormComponentRequestBuilder();

		return buildAddFormComponentRequest(builder);
	}

	private Request buildAddOptionVariableFormComponentRequest() {
		AddFormComponentRequestBuilder builder = 
				new AddOptionVariableFormComponentRequestBuilder();
		return buildAddFormComponentRequest(builder);
	}

	private Request buildAddFormComponentRequest(AddFormComponentRequestBuilder builder) {
		builder.buildQuestionId(getArgumentAsString("questionId"));
		builder.buildQuestionContent(getArgumentAsString("questionContent"));
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
		builder.buildOldId(getArgumentAsString("oldId"));
		builder.buildNewId(getArgumentAsString("newId"));
		return finishBuildingRequest(builder);
	}

	private Request buildChangeOptionVariableRequest() {
		ChangeOptionVariableRequestBuilder builder = 
				new ChangeOptionVariableRequestBuilder();
		builder.buildComponentId(getArgumentAsString("componentId"));
		return finishBuildingRequest(builder);
	}

	private String getArgumentAsString(String key) {
		return (String) args.getById(key);
	}

	private Request buildChangeUnstructuredRequest() {
		ChangeUnstructuredRequestBuilder builder = 
				new ChangeUnstructuredRequestBuilder();
		builder.buildComponentId(getArgumentAsString("componentId"));
		return finishBuildingRequest(builder);
	}

	private Request buildDeleteFormComponentRequest() {
		DeleteFormComponentRequestBuilder builder = 
				new DeleteFormComponentRequestBuilder();
		builder.buildComponentId(getArgumentAsString("componentId"));
		return finishBuildingRequest(builder);
	}

	private Request buildHandleUnfoundControllerRequest() {
		HandleUnfoundUseCaseRequestBuilder builder = 
				new HandleUnfoundUseCaseRequestBuilder();
		builder.buildMessage((String) args.getById("message"));
		return finishBuildingRequest(builder);
	}
	
}
