package formfiller.delivery.event.impl;

import java.util.Collections;
import java.util.List;

/**
 * ParsedEvent is a data object containing user input used by the 
 * router and controllers.
 */
public class ParsedEvent {
	public String method;
	public List<String> parameters;
	
	public ParsedEvent() {
		method = "";
		parameters = Collections.emptyList();
	}
}