package formfiller.delivery.event;

import java.util.Scanner;

import formfiller.delivery.EventSource;

public class ConsoleEventSource extends EventSource {
	private static final Scanner stdIn = new Scanner(System.in);
	
	public void captureEvents(){
		while (true){
			getInputEvent();
		}
	}

	private void getInputEvent() {
		String event = stdIn.nextLine();
		event = event.toLowerCase();
		setChanged();
		notifyObservers(event);
	}
}
