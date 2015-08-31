package formfiller.delivery;

import formfiller.delivery.eventParser.ParsedEvent;

public interface EventParser {

	ParsedEvent parse(String string);

}
