package formfiller.request.builders;

import formfiller.gateways.InMemoryTransporter;
import formfiller.enums.Direction;
import formfiller.request.models.NavigationRequest;
import formfiller.request.models.Request;

public class NavigationRequestBuilder implements RequestBuilderFunctions {
	NavigationRequest navigationRequest;
	
	public NavigationRequestBuilder(){
		navigationRequest = new NavigationRequest();
	}

	public void buildName() {
		navigationRequest.name = "Navigation";
	}
	
	public void buildDirection(Direction direction){
		navigationRequest.direction = direction;
	}

	public Request getRequest() {
		return navigationRequest;
	}
}
