package formfiller.ui.consoleUi;

import java.util.HashMap;
import java.util.Map;

import formfiller.ui.Controller;

//Adapted from:
//https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/src/cleancoderscom/http/Router.java
//Retrieved 2015-08-14
public class Router {
	Map<String, Controller> routes = new HashMap<String, Controller>();

	public void addMethod(String request, Controller controller) {
		routes.put(request, controller);
	}
	public void route(ParsedUserRequest parsedInput) {
		Controller controller = routes.get(parsedInput.getMethod());
		controller.handle(parsedInput);
	}

}
