package formfiller.delivery.event.impl;

import formfiller.delivery.event.CharacterBasedEventParsingStrategy;
import formfiller.delivery.event.EventParser;
import formfiller.delivery.router.Router;

/**
 * EventHandler parses UI input with an event parser and passes the 
 * resulting object to the router.
 */
public class EventHandler {
	EventParser eventParser;
	Router router;

	public EventHandler(Router router) {
		this.router = router;
		this.eventParser = new FormEventParser(new CharacterBasedEventParsingStrategy());
	}
	
	public void handleEvent(String event){
		ParsedEvent parsedEvent = eventParser.parse(event);
		router.route(parsedEvent);
	}
	
}
