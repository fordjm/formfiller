package formfiller.ui.view;

import java.util.Observable;

import formfiller.ApplicationContext;
import formfiller.usecases.presentQuestion.PresentableQuestion;

public class ConsoleQuestionView implements QuestionView {
	PresentableQuestion question;

	public void update(Observable presenter, Object input) {
		displayQuestion();
	}
	public void displayQuestion() {
		question = ApplicationContext.presentQuestionResponseBoundary.getPresentableQuestion();
		System.out.println(question.getContent());
	}

}
