package formfiller.delivery.event.eventSource;

public interface EventSourceFactory {
	public EventSource make(String eventSourceName);
}
