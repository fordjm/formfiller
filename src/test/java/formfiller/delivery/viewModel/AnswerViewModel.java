package formfiller.delivery.viewModel;
import java.util.Observable;

import formfiller.delivery.ViewModel;
import formfiller.delivery.ui.testConsoleUi.ConsoleView;
import formfiller.response.models.PresentableAnswer;

public class AnswerViewModel implements ViewModel {
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