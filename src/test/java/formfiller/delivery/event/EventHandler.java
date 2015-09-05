package formfiller.delivery.event;

import java.util.Observable;
import java.util.Observer;

import formfiller.delivery.EventParser;
import formfiller.delivery.router.Router;

public class EventHandler implements Observer {
	EventParser eventParser = new StringEventParser();
	Router router = Router.makeRouter();

	public void update(Observable o, Object input) {
		if (input == null) input = "";
		
		String event = (String) input;
		handleEvent(event);
	}
	
	private void handleEvent(String event){
		ParsedEvent parsedEvent = eventParser.parse(event);
		router.route(parsedEvent);
	}
}
