package formfiller.request.builders;

import formfiller.delivery.controller.Arguments;
import formfiller.enums.WhichQuestion;
import formfiller.request.models.Request;

public class RequestBuilderImpl implements RequestBuilder {

	public Request build(String requestName, Arguments args) {
		if(requestName.equalsIgnoreCase("handleUnfoundUseCase")) {
			return buildHandleUnfoundControllerRequest(args);
		}
		else if(requestName.equalsIgnoreCase("askQuestion"))
			return buildAskQuestionRequest(args);
		else
			return getNoRequest();
	}

	private Request buildHandleUnfoundControllerRequest(Arguments args) {
		HandleUnfoundUseCaseRequestBuilder builder = 
				new HandleUnfoundUseCaseRequestBuilder();
		builder.buildMessage((String) args.getById("message"));
		return finishBuildingRequest(builder);
	}
	
	private Request buildAskQuestionRequest(Arguments args) {
		AskQuestionRequestBuilder builder = new AskQuestionRequestBuilder();
		builder.buildWhichQuestion((WhichQuestion) args.getById("which")); 
		return finishBuildingRequest(builder);
	}
	
	private Request finishBuildingRequest(RequestBuilderFunctions builder){
		builder.buildName();
		return builder.getRequest();
	}
	
	//	TODO:	Request.NULL object
	private Request getNoRequest() {
		Request result = new Request();
		result.name = "NoRequest";
		return result;
	}
}
