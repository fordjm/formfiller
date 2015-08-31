package formfiller;

import java.util.Scanner;

import formfiller.delivery.EventSource;

// TODO:	Make this operate through a listener.
public class ConsoleEventSource implements EventSource {
	private static final Scanner stdIn = new Scanner(System.in);

	public String getInputEvent() {
		return stdIn.nextLine();
	}
}
