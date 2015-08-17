package formfiller.delivery.view;

import java.util.Observable;

import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableQuestion;
import formfiller.delivery.View;

public class ConsoleQuestionView implements View {
	PresentableQuestion question;

	public void update(Observable presenter, Object input) {
		displayPresentableResponse();
	}
	public void displayPresentableResponse() {
		question = ApplicationContext.questionPresenter.getPresentableResponse();
		System.out.println(question.getMessage());
	}

}
