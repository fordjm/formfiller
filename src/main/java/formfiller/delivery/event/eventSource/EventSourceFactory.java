package formfiller.delivery.event.eventSource;

/**
 * EventSourceFactory is the interface for creating EventSource instances 
 * by passing in modality component names.
 */
public interface EventSourceFactory {
	public EventSource make(String eventSourceName);
}
