package formfiller.ui;

import java.util.Observable;

import formfiller.gateways.ApplicationContext;
import formfiller.usecases.PresentableQuestion;

public class ConsoleView implements QuestionView {
	PresentableQuestion question;

	public void update(Observable presenter, Object input) {
		displayQuestion();
	}
	public void displayQuestion() {
		question = ApplicationContext.presentQuestionBoundary.getQuestion();
		System.out.println(question.content);
	}

}
