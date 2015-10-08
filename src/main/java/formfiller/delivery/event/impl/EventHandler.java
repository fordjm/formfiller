package formfiller.delivery.event.impl;

import java.util.Observable;
import java.util.Observer;

import formfiller.delivery.event.EventParser;
import formfiller.delivery.router.Router;

public class EventHandler implements Observer {
	EventParser eventParser = new StringEventParser();
	Router router;

	public EventHandler(Router router) {
		this.router = router;
	}

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
