package formfiller.delivery.event;

import formfiller.delivery.event.impl.ParsedEvent;

/**
 * EventParser is the interface for incoming UI requests.
 */
public interface EventParser {
	ParsedEvent parse(String string);
}
