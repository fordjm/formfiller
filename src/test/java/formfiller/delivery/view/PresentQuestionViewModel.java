package formfiller.delivery.view;

import java.util.Observable;

import formfiller.delivery.ViewModel;
import formfiller.response.models.PresentableQuestion;

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
