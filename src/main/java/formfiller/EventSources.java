package formfiller;

import java.util.ArrayList;
import java.util.List;

import formfiller.delivery.event.eventSource.EventSource;

public class EventSources {
	private static List<EventSource> itsSources = new ArrayList<EventSource>();

	public static void add(EventSource source) {
		itsSources.add(source);
	}
	
	public static void disable() {
		for (EventSource source : itsSources)
			source.disable();
	}
	
	public static void enable() {
		for (EventSource source : itsSources)
			source.enable();
	}
	
}