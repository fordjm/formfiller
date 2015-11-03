package formfiller.delivery.event.impl;

import java.util.Collections;
import java.util.List;

public class ParsedEvent {
	public String method;
	public List<String> parameters;
	
	public ParsedEvent() {
		method = "";
		parameters = Collections.emptyList();
	}
}