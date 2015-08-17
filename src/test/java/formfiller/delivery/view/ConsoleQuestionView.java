package formfiller.delivery.view;

import java.util.Observable;

import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableQuestion;
import formfiller.delivery.PresentQuestionView;

public class ConsoleQuestionView implements PresentQuestionView {
	PresentableQuestion question;

	public void update(Observable presenter, Object input) {
		displayQuestion();
	}
	public void displayQuestion() {
		question = ApplicationContext.questionPresenter.getPresentableResponse();
		System.out.println(question.getMessage());
	}

}
