package formfiller.delivery.viewModel;

import java.util.Observable;

import formfiller.delivery.ViewModel;
import formfiller.delivery.ui.testConsoleUi.ConsoleView;
import formfiller.response.models.PresentableQuestion;

public class QuestionViewModel implements ViewModel {
	PresentableQuestion question;

	public void update(Observable presenter, Object input) {
		outputPresentableResponse(input);
	}
	
	public void outputPresentableResponse(Object input) {
		question = (PresentableQuestion) input;
		ConsoleView.output(question.message);
	}
}
