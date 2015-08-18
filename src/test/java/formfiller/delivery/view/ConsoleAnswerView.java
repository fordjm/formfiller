package formfiller.delivery.view;
import java.util.Observable;
import java.util.Scanner;

import formfiller.ApplicationContext;
import formfiller.delivery.UserRequestSource;
import formfiller.usecases.PresentableAnswer;

public class ConsoleAnswerView implements UserRequestSource {
	Scanner stdIn = new Scanner(System.in);
	
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
		if (answerMessage.length() > 0)
			System.out.println("Your current answer is: " + answerMessage);
	}
	private PresentableAnswer presentableAnswer(){
		return (PresentableAnswer) 
				ApplicationContext.answerPresenter.getPresentableResponse();
	}
	private String answerMessage(PresentableAnswer presentableAnswer){
		return presentableAnswer.getMessage();
	}

}