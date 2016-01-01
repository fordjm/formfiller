package formfiller.delivery.event.eventSink;

/**
 * EventSinkFactory is the interface for creating EventSink instances 
 * by passing in modality component names.
 */
public interface EventSinkFactory {
    public EventSink make(String eventSinkName);
}
