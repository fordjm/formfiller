package formfiller.delivery.view;

import java.util.Scanner;

public class ConsoleView {
	private static final Scanner stdIn = new Scanner(System.in);
	
	public static String input(){
		return stdIn.nextLine();
	}

	public static void output(String message){
		System.out.println(message);
	}
}
