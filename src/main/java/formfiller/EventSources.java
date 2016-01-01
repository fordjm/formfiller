package formfiller;

import java.util.ArrayList;
import java.util.List;

import formfiller.delivery.event.eventSource.EventSource;

/**
 * The EventSources class stores a collection of EventSource objects.
 * EventSources regulates the flow of user input by enabling or disabling 
 * each EventSource in the collection.
 */
public class EventSources {
	private static List<EventSource> itsSources = new ArrayList<EventSource>();
	/**
	 * This method adds an EventSource object to the collection.
	 * 
	 * @param source EventSource variable to be added
	 * @since version 0.0.1
	 */
	public static void add(EventSource source) {
		itsSources.add(source);
	}
	
	/**
	 * This method disables each EventSource in itsSources 
	 * in order to disable user input.
	 * 
	 * @since version 0.0.1
	 */	
	public static void disable() {
		for (EventSource source : itsSources)
			source.disable();
	}
	
	/**
	 * This method enables each EventSource in itsSources 
	 * in order to enable user input.
	 * 
	 * @since version 0.0.1
	 */		
	public static void enable() {
		for (EventSource source : itsSources)
			source.enable();
	}
	
}
