package formfiller;

import java.util.Scanner;

import formfiller.delivery.EventSource;

public class ConsoleEventSource implements EventSource {
	private static final Scanner stdIn = new Scanner(System.in);

	public String getInputEvent() {
		return stdIn.nextLine();
	}
}
