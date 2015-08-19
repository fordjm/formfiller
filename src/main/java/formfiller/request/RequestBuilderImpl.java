package formfiller.request;

import java.util.HashMap;

import formfiller.usecases.handleUnfoundController.HandleUnfoundControllerRequest;

// TODO:  Figure out what requests have in common.
//		  Make this a real builder, not a factory.
public class RequestBuilderImpl implements RequestBuilder {

	public <K,V> Request build(String requestName, HashMap<K,V> args) {
		if(requestName.equalsIgnoreCase("handleUnfoundController"))
			return makeHandleUnfoundControllerRequest(args); 
		else if(requestName.equalsIgnoreCase("presentQuestion"))
			return makePresentQuestionRequest(args);
		else if(requestName.equalsIgnoreCase("presentAnswer"))
			return makePresentAnswerRequest(args);
		else if(requestName.equalsIgnoreCase("navigation"))
			return makeNavigationRequest(args);
		else
			return new NoRequestImpl();
	}

	private <K,V> Request makeHandleUnfoundControllerRequest(HashMap<K,V> args) {
		HandleUnfoundControllerRequest result = new HandleUnfoundControllerRequestImpl();
		result.setMessage((String) args.get("message"));
		return result;
	}
	private <K,V> Request makePresentQuestionRequest(HashMap<K,V> args) {
		return new PresentQuestionRequestImpl();
	}	
	private <K,V> Request makePresentAnswerRequest(HashMap<K,V> args) {
		return new PresentAnswerRequestImpl();
	}
	private <K,V> Request makeNavigationRequest(HashMap<K,V> args) {
		NavigationRequest result = new NavigationRequestImpl();
		result.setOffset((int) args.get("offset")); 
		return result;
	}

}
