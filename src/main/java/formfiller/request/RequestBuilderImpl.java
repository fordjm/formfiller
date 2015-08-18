package formfiller.request;

import java.util.HashMap;

import formfiller.usecases.handleUnfoundController.HandleUnfoundControllerRequest;
import formfiller.usecases.navigation.NavigationRequest;

// TODO:  Figure out what requests have in common.
//		  Make this a real builder, not a factory.
public class RequestBuilderImpl implements RequestBuilder {

	@Override
	public Request build(String requestName, HashMap args) {
		if(requestName.equalsIgnoreCase("handleUnfoundController"))
			return makeHandleUnfoundControllerRequest(args); 
		else if(requestName.equalsIgnoreCase("presentQuestion"))
			return makePresentQuestionRequest(args);
		else if(requestName.equalsIgnoreCase("navigation"))
			return makeNavigationRequest(args);
		else
			return null;
	}

	private Request makeHandleUnfoundControllerRequest(HashMap args) {
		HandleUnfoundControllerRequest result = new HandleUnfoundControllerRequestImpl();
		result.setMessage((String) args.get("message"));
		return result;
	}
	public class HandleUnfoundControllerRequestImpl implements HandleUnfoundControllerRequest{
		private String message;
		
		private HandleUnfoundControllerRequestImpl(){ }

		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}

	private Request makePresentQuestionRequest(HashMap args) {
		return new PresentQuestionRequest();
	}	
	public class PresentQuestionRequest implements Request{		
		private PresentQuestionRequest(){ }
	}

	private Request makeNavigationRequest(HashMap args) {
		NavigationRequest result = new NavigationRequestImpl();
		result.setOffset((int)args.get("offset")); 
		return result;
	}
	public class NavigationRequestImpl implements NavigationRequest {
		private int offset = -1;
		
		private NavigationRequestImpl(){ }

		public int getOffset() {
			return offset;
		}
		public void setOffset(int offset) {
			this.offset = offset;
		}
	}

}
