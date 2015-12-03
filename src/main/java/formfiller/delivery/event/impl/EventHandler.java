package formfiller.delivery.event.impl;

import formfiller.delivery.event.EventParser;
import formfiller.delivery.router.Router;

public class EventHandler {
	EventParser eventParser = new StringEventParser();
	Router router;

	public EventHandler(Router router) {
		this.router = router;
	}
	
	public void handleEvent(String event){
		ParsedEvent parsedEvent = eventParser.parse(event);
		router.route(parsedEvent);
	}
	
}
