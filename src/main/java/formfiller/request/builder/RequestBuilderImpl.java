package formfiller.request.builder;

import java.util.HashMap;

import formfiller.request.interfaces.Request;

public class RequestBuilderImpl implements RequestBuilder {

	public <K,V> Request build(String requestName, HashMap<K,V> args) {
		if(requestName.equalsIgnoreCase("handleUnfoundController"))
			return buildHandleUnfoundControllerRequest(args); 
		else if(requestName.equalsIgnoreCase("presentQuestion"))
			return buildPresentQuestionRequest(args);
		else if(requestName.equalsIgnoreCase("presentAnswer"))
			return buildPresentAnswerRequest(args);
		else if(requestName.equalsIgnoreCase("navigation"))
			return buildNavigationRequest(args);
		else
			return getNoRequest();
	}

	private <K,V> Request buildHandleUnfoundControllerRequest(HashMap<K,V> args) {
		HandleUnfoundControllerRequestBuilder builder = 
				new HandleUnfoundControllerRequestBuilder();
		builder.buildMessage((String) args.get("message"));
		return finishBuildingRequest(builder);
	}
	private <K,V> Request buildPresentQuestionRequest(HashMap<K,V> args) {
		PresentQuestionRequestBuilder builder = new PresentQuestionRequestBuilder();
		return finishBuildingRequest(builder);
	}	
	private <K,V> Request buildPresentAnswerRequest(HashMap<K,V> args) {
		PresentAnswerRequestBuilder builder = new PresentAnswerRequestBuilder();
		return finishBuildingRequest(builder);
	}
	private <K,V> Request buildNavigationRequest(HashMap<K,V> args) {
		NavigationRequestBuilder builder = new NavigationRequestBuilder();
		builder.buildOffset((int) args.get("offset"));
		return finishBuildingRequest(builder);
	}
	private Request finishBuildingRequest(AbstractRequestBuilder builder){
		builder.buildName();
		return builder.getRequest();
	}
	private Request getNoRequest() {
		return new Request(){
			public String getName() {
				return "NoRequest";
			}
			public void setName(String name) { }};
	}

}
