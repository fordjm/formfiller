package formfiller.delivery.event.impl;

import java.util.Scanner;

import formfiller.delivery.event.EventSource;

public class ConsoleEventSource extends EventSource {
	private static final Scanner stdIn = new Scanner(System.in);
	
	public void captureEvents(){
		while (true){
			getInputEvent();
		}
	}

	private void getInputEvent() {
		String event = stdIn.nextLine();
		setChanged();
		notifyObservers(event);
	}
}
