package formfiller.request.builders;

import formfiller.delivery.controller.Arguments;
import formfiller.enums.Direction;
import formfiller.request.models.Request;

public class RequestBuilderImpl implements RequestBuilder {

	public Request build(String requestName, Arguments args) {
		if(requestName.equalsIgnoreCase("handleUnfoundUseCase")) {
			return buildHandleUnfoundControllerRequest(args);
		}
		else if(requestName.equalsIgnoreCase("navigation"))
			return buildNavigationRequest(args);
		else
			return getNoRequest();
	}

	private Request buildHandleUnfoundControllerRequest(Arguments args) {
		HandleUnfoundUseCaseRequestBuilder builder = 
				new HandleUnfoundUseCaseRequestBuilder();
		builder.buildMessage((String) args.getById("message"));
		return finishBuildingRequest(builder);
	}
	
	private Request buildNavigationRequest(Arguments args) {
		NavigationRequestBuilder builder = new NavigationRequestBuilder();
		builder.buildDirection((Direction) args.getById("direction")); 
		return finishBuildingRequest(builder);
	}
	
	private Request finishBuildingRequest(RequestBuilderFunctions builder){
		builder.buildName();
		return builder.getRequest();
	}
	
	//	TODO:	Build a NoRequest object.
	private Request getNoRequest() {
		Request result = new Request();
		result.name = "NoRequest";
		return result;
	}
}
