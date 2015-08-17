package formfiller.delivery.router;

import java.util.HashMap;
import java.util.Map;

import formfiller.delivery.Controller;
import formfiller.delivery.controller.HandleUnfoundController;
import formfiller.delivery.userRequestParser.ParsedUserRequest;

//Adapted from:
//https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/src/cleancoderscom/http/Router.java
//Retrieved 2015-08-14
public class Router {
	public static final Controller handleUnfoundController = new HandleUnfoundController();
	private Map<String, Controller> routes = new HashMap<String, Controller>();

	public void addMethod(String request, Controller controller) {
		routes.put(request, controller);
	}
	public void route(ParsedUserRequest parsedUserRequest) {
		Controller controller = getController(parsedUserRequest);
		controller.handle(parsedUserRequest);
	}
	protected Controller getController(ParsedUserRequest parsedUserRequest) {
		Controller result = routes.get(parsedUserRequest.getMethod());
		if (result == null) result = handleUnfoundController;
		return result;
	}

}
