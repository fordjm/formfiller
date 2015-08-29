package formfiller.request.builder;

import formfiller.gateways.Transporter;
import formfiller.request.implementations.NavigationRequestImpl;
import formfiller.request.interfaces.NavigationRequest;
import formfiller.request.interfaces.Request;

public class NavigationRequestBuilder implements AbstractRequestBuilder {
	NavigationRequest navigationRequest;
	
	public NavigationRequestBuilder(){
		navigationRequest = new NavigationRequestImpl();
	}

	public void buildName() {
		navigationRequest.setName("Navigation");
	}
	
	public void buildDirection(Transporter.Direction direction){
		navigationRequest.setDirection(direction);
	}

	public Request getRequest() {
		return navigationRequest;
	}

}
