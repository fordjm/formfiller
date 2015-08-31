package formfiller.delivery;

import java.util.Observable;

public abstract class EventSource extends Observable {
	public abstract void captureEvents();
}
