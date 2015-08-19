package formfiller.request.builder;

import formfiller.request.implementations.RequestImpl;
import formfiller.request.interfaces.Request;

public class PresentAnswerRequestBuilder implements AbstractRequestBuilder {
	RequestImpl presentAnswerRequestImpl;
	
	public PresentAnswerRequestBuilder(){
		presentAnswerRequestImpl = new RequestImpl();
	}

	public void buildName() {
		presentAnswerRequestImpl.setName("PresentAnswer");
	}
	public Request getRequest() {
		return presentAnswerRequestImpl;
	}

}
