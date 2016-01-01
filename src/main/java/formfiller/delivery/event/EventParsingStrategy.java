package formfiller.delivery.event;

import java.util.List;

/**
 * EventParsingStrategy defines the interface for UI event parsing algorithms.
 */
public interface EventParsingStrategy {
	String parseMethod(String input);
	List<String> parseParameters(String input);
}
