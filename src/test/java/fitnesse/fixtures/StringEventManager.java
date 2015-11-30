package fitnesse.fixtures;

import formfiller.delivery.event.EventSource;
import formfiller.delivery.event.impl.ConsoleEventSource;
import formfiller.delivery.event.impl.EventHandler;
import formfiller.delivery.router.PlaceholderTextRouterFactory;
import formfiller.delivery.router.Router;

public class StringEventManager {
	private EventSource source;
	private Router router;
	private EventHandler handler;

	//	TODO:  Find a name without "manager" in it.
	public StringEventManager() {
		source = new ConsoleEventSource();
		router = PlaceholderTextRouterFactory.makeRouter();
		handler = new EventHandler(router);
	}
	
	public void updateHandler(String input){
		handler.update(source, input);
	}
}
