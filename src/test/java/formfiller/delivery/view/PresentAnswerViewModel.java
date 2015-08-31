package formfiller.delivery.view;
import java.util.Observable;

import formfiller.boundaryCrossers.PresentableAnswer;
import formfiller.delivery.ViewModel;

public class PresentAnswerViewModel implements ViewModel {
	private boolean wasDisplayed = false;
	
	public void update(Observable o, Object input) {
		outputPresentableResponse(input);
	}
	
	public void outputPresentableResponse(Object input) {
		String answerMessage = getAnswerMessage(getPresentableAnswer(input));
		
		if (answerMessage.length() > 0){
			setWasDisplayed(true);
			ConsoleView.output("Your current answer is: " + answerMessage);
		} else
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
		return presentableAnswer.message;
	}
}