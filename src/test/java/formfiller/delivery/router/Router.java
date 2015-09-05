package formfiller.delivery.router;

import java.util.HashMap;
import java.util.Map;

import formfiller.delivery.Controller;
import formfiller.delivery.controller.HandleUnfoundUseCaseController;
import formfiller.delivery.controller.NavigationController;
import formfiller.delivery.event.ParsedEvent;

//Adapted from:
//https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/src/cleancoderscom/http/Router.java
//Retrieved 2015-08-14

public class Router {
	public static final Controller handleUnfoundUseCaseController = 
			new HandleUnfoundUseCaseController();
	private Map<String, Controller> routes = new HashMap<String, Controller>();
	
	public static Router makeRouter(){
		Router result = new Router();
		result.addMethod("navigation", new NavigationController());
		return result;
	}
	
	private Router() { }

	public void addMethod(String request, Controller controller) {
		routes.put(request, controller);
	}
	
	public void route(ParsedEvent parsedEvent) {
		Controller controller = getController(parsedEvent.method);
		controller.handle(parsedEvent);
	}
	
	protected Controller getController(String method) {
		Controller result = routes.get(method);
		if (result == null) result = handleUnfoundUseCaseController;
		return result;
	}
}
