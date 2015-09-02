package formfiller.delivery;

import formfiller.delivery.event.ParsedEvent;

public interface EventParser {
	ParsedEvent parse(String string);
}
