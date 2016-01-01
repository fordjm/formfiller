package formfiller.delivery.event.eventSource;

/**
 * Every modality component must include an EventSource implementation which 
 * enables and disables user input collection.
 */
public interface EventSource {
	public void disable();
	public void enable();
}
