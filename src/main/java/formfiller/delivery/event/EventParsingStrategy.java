package formfiller.delivery.event;

import java.util.List;

public interface EventParsingStrategy {
	String parseMethod(String input);
	List<String> parseParameters(String input);
}
