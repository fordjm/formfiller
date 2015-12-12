package fitnesse.fixtures;

import formfiller.delivery.event.impl.EventHandler;
import formfiller.delivery.router.PlaceholderTextRouterFactory;
import formfiller.delivery.router.Router;

public class ConsoleEventManager {
	private Router router;
	private EventHandler handler;

	//	TODO:  Find a name without "manager" in it.
	public ConsoleEventManager() {
		router = PlaceholderTextRouterFactory.makeRouter();
		handler = new EventHandler(router);
	}
	
	public void updateHandler(String input){
		handler.handleEvent(input);
	}
	
}
