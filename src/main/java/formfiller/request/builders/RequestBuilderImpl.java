package formfiller.request.builders;

import formfiller.delivery.controller.Arguments;
import formfiller.enums.QuestionAsked;
import formfiller.request.models.Request;

public class RequestBuilderImpl implements RequestBuilder {
	Arguments args;

	public Request build(String requestName, Arguments args) {
		this.args = args;
		
		if(requestName.equalsIgnoreCase("handleUnfoundUseCase"))
			return buildHandleUnfoundControllerRequest(args);
		else if(requestName.equalsIgnoreCase("addFormComponent"))
			return buildAddFormComponentRequest(args);
		else if(requestName.equalsIgnoreCase("askQuestion"))
			return buildAskQuestionRequest(args);
		else
			return Request.NULL;
	}

	private Request buildHandleUnfoundControllerRequest(Arguments args) {
		HandleUnfoundUseCaseRequestBuilder builder = 
				new HandleUnfoundUseCaseRequestBuilder();
		builder.buildMessage((String) args.getById("message"));
		return finishBuildingRequest(builder);
	}

	private Request buildAddFormComponentRequest(Arguments args) {
		AddFormComponentRequestBuilder builder = 
				selectBuilderByAnswerFormat();
		builder.buildQuestionId((String) args.getById("questionId"));
		builder.buildQuestionContent((String) args.getById("questionContent"));
		builder.buildAnswerFormat();
		return finishBuildingRequest(builder);
	}

	private AddFormComponentRequestBuilder selectBuilderByAnswerFormat() {
		String format = (String) args.getById("answerFormat");
		if (format.equalsIgnoreCase("U"))
			return new AddUnstructuredFormComponentRequestBuilder();
		else if (format.equalsIgnoreCase("V"))
			return new AddOptionVariableFormComponentRequestBuilder(
					(String) args.getById("options"));
		else
			throw new IllegalArgumentException("No request builder for the answer "
					+ "format " + format + " exists.");
	}
	
	private Request buildAskQuestionRequest(Arguments args) {
		AskQuestionRequestBuilder builder = new AskQuestionRequestBuilder();
		builder.buildWhichQuestion((QuestionAsked) args.getById("which")); 
		return finishBuildingRequest(builder);
	}
	
	private Request finishBuildingRequest(RequestBuilderFunctions builder){
		builder.buildName();
		return builder.getRequest();
	}
}
