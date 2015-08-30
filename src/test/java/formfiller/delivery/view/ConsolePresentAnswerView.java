package formfiller.delivery.view;
import java.util.Observable;
import java.util.Scanner;

import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableAnswer;
import formfiller.delivery.UserRequestSource;

public class ConsolePresentAnswerView implements UserRequestSource {
	private Scanner stdIn = new Scanner(System.in);
	private boolean wasDisplayed = false;
	
	// Should almost definitely be in ViewModel.
	// Is ViewModel/View split possible for ConsoleView?
	// TODO:	Split user request gathering out of the answer view.
	public String getUserRequestString() {
		return stdIn.nextLine();
	}
	public void update(Observable o, Object arg) {
		outputPresentableResponse();
	}
	public void outputPresentableResponse() {
		String answerMessage = answerMessage(presentableAnswer());
		if (answerMessage.length() > 0){
			setWasDisplayed(true);
			System.out.println("Your current answer is: " + answerMessage);
		}
		else
			setWasDisplayed(false);
	}
	private void setWasDisplayed(boolean wasDisplayed) {
		this.wasDisplayed = wasDisplayed;
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