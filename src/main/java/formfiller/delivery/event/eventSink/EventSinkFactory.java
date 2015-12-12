package formfiller.delivery.event.eventSink;

public interface EventSinkFactory {
    public EventSink make(String eventSinkName);
}
