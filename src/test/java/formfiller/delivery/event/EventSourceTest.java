package formfiller.delivery.event;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;

import org.junit.Test;

import formfiller.delivery.EventSource;

public class EventSourceTest implements Observer {
	String event;

	public void update(Observable o, Object input) {
		event = (String) input;
		
		System.out.println(event);
	}

	@Test
	public void test() {
		EventSourceTestDouble eventSource = new EventSourceTestDouble();
		eventSource.addObserver(this);
		
		eventSource.captureEvents();
		
		assertThat(event, is("testEvent"));
	}
	
	private class EventSourceTestDouble extends EventSource {

		public void captureEvents() {
			getInputEvent();
		}

		private void getInputEvent() {
			String event = "testEvent";
			setChanged();
			notifyObservers(event);
		}		
	}
}
