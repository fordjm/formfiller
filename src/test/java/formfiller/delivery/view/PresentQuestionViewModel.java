package formfiller.delivery.view;

import java.util.Observable;

import formfiller.boundaryCrossers.PresentableQuestion;
import formfiller.delivery.ViewModel;

public class PresentQuestionViewModel implements ViewModel {
	PresentableQuestion question;

	public void update(Observable presenter, Object input) {
		outputPresentableResponse(input);
	}
	
	public void outputPresentableResponse(Object input) {
		question = (PresentableQuestion) input;
		ConsoleView.output(question.message);
	}
}
