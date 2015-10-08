package formfiller.delivery.event;

import formfiller.delivery.event.impl.ParsedEvent;

public interface EventParser {
	ParsedEvent parse(String string);
}
