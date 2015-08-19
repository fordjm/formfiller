package formfiller.request.builder;

import formfiller.request.implementations.RequestImpl;
import formfiller.request.interfaces.Request;

public class PresentQuestionRequestBuilder implements AbstractRequestBuilder {
	RequestImpl presentQuestionRequestImpl;
	
	public PresentQuestionRequestBuilder(){
		presentQuestionRequestImpl = new RequestImpl();
	}

	public void buildName() {
		presentQuestionRequestImpl.setName("PresentQuestion");
	}
	public Request getRequest() {
		return presentQuestionRequestImpl;
	}

}
