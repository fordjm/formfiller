package formfiller.delivery.view;
import java.util.Observable;

import formfiller.boundaryCrossers.PresentableAnswer;
import formfiller.delivery.UserRequestSource;
import formfiller.delivery.ViewModel;

public class PresentAnswerViewModel implements UserRequestSource, ViewModel {
	private boolean wasDisplayed = false;
	
	// Should almost definitely be in ViewModel.
	// Is ViewModel/View split possible for ConsoleView?
	// TODO:	Split user request gathering out of the answer view.
	public String getUserRequestInput() {
		return ConsoleView.input();
	}
	
	public void update(Observable o, Object input) {
		outputPresentableResponse(input);
	}
	
	public void outputPresentableResponse(Object input) {
		String answerMessage = getAnswerMessage(getPresentableAnswer(input));
		if (answerMessage.length() > 0){
			setWasDisplayed(true);
			ConsoleView.output("Your current answer is: " + answerMessage);
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
	
	private PresentableAnswer getPresentableAnswer(Object input){
		return (PresentableAnswer) input;
	}
	
	private String getAnswerMessage(PresentableAnswer presentableAnswer){
		return presentableAnswer.getMessage();
	}

}