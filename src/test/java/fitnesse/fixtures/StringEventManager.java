package fitnesse.fixtures;

import formfiller.delivery.event.impl.EventHandler;
import formfiller.delivery.router.PlaceholderTextRouterFactory;
import formfiller.delivery.router.Router;

public class StringEventManager {
	private Router router;
	private EventHandler handler;

	//	TODO:  Find a name without "manager" in it.
	public StringEventManager() {
		router = PlaceholderTextRouterFactory.makeRouter();
		handler = new EventHandler(router);
	}
	
	public void updateHandler(String input){
		handler.handleEvent(input);
	}
	
}
