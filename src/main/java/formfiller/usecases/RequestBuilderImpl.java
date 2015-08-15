package formfiller.usecases;

import java.util.HashMap;

import formfiller.usecases.navigation.NavigationRequest;

public class RequestBuilderImpl implements RequestBuilder {

	@Override
	public Request build(String requestName, HashMap args) {
		switch(requestName){
		case "presentQuestion": 
			return makePresentQuestionRequest(args);
		case "navigation": 
			return makeNavigationRequest(args);
		}
		return null;
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
