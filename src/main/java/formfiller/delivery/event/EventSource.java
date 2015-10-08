package formfiller.delivery.event;

import java.util.Observable;

public abstract class EventSource extends Observable {
	public abstract void captureEvents();
}
