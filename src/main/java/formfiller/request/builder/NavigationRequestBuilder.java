package formfiller.request.builder;

import formfiller.gateways.InMemoryTransporter;
import formfiller.request.implementations.NavigationRequestImpl;
import formfiller.request.interfaces.NavigationRequest;
import formfiller.request.interfaces.Request;

public class NavigationRequestBuilder implements RequestBuilderFunctions {
	NavigationRequest navigationRequest;
	
	public NavigationRequestBuilder(){
		navigationRequest = new NavigationRequestImpl();
	}

	public void buildName() {
		navigationRequest.setName("Navigation");
	}
	
	public void buildDirection(InMemoryTransporter.Direction direction){
		navigationRequest.setDirection(direction);
	}

	public Request getRequest() {
		return navigationRequest;
	}
}
