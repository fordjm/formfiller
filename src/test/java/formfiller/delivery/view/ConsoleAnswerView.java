package formfiller.delivery.view;
import java.util.Observable;
import java.util.Scanner;

import formfiller.ApplicationContext;
import formfiller.delivery.UserRequestSource;
import formfiller.usecases.PresentableAnswer;

public class ConsoleAnswerView implements UserRequestSource {
	private Scanner stdIn = new Scanner(System.in);
	private boolean wasDisplayed = false;
	
	// Should almost definitely be in ViewModel.
	// Is ViewModel/View split possible for ConsoleView?
	public String getUserRequestString() {
		return stdIn.nextLine();
	}
	public void update(Observable o, Object arg) {
		displayPresentableResponse();
	}
	public void displayPresentableResponse() {
		String answerMessage = answerMessage(presentableAnswer());
		if (answerMessage.length() > 0){
			wasDisplayed = true;
			System.out.println("Your current answer is: " + answerMessage);
		}
		else
			wasDisplayed = false;
	}
	public boolean wasDisplayed() {
		return wasDisplayed;
	}
	private PresentableAnswer presentableAnswer(){
		return (PresentableAnswer) 
				ApplicationContext.answerPresenter.getPresentableResponse();
	}
	private String answerMessage(PresentableAnswer presentableAnswer){
		return presentableAnswer.getMessage();
	}

}