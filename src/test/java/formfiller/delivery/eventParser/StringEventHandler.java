package formfiller.delivery.eventParser;

import java.util.Observable;
import java.util.Observer;

import formfiller.delivery.EventParser;
import formfiller.delivery.router.Router;

public class StringEventHandler implements Observer {
	EventParser eventParser = new StringEventParser();
	Router router = Router.makeRouter();

	public void update(Observable o, Object arg) {
		String event = (String) arg;
		handleEvent(event);
	}
	
	private void handleEvent(String event){
		ParsedEvent parsedEvent = eventParser.parse(event);
		router.route(parsedEvent);
	}
}
