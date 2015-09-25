package fitnesse.fixtures;

import formfiller.delivery.EventSource;
import formfiller.delivery.event.ConsoleEventSource;
import formfiller.delivery.event.EventHandler;
import formfiller.delivery.router.PlaceholderRouterFactory;
import formfiller.delivery.router.Router;

public class FixtureEventHandler {
	private EventSource source;
	private Router router;
	private EventHandler handler;
	
	public FixtureEventHandler() {
		//	TODO:  Make fixture event source or generalize name.
		source = new ConsoleEventSource();
		router = PlaceholderRouterFactory.makeRouter();
		handler = new EventHandler(router);
	}
	
	public void updateHandler(String input){
		handler.update(source, input);
	}
}
