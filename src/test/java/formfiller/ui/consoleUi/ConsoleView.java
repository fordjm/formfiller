package formfiller.ui.consoleUi;

import java.util.Observable;

import formfiller.ApplicationContext;
import formfiller.ui.QuestionView;
import formfiller.usecases.presentQuestion.PresentableQuestion;

public class ConsoleView implements QuestionView {
	PresentableQuestion question;

	public void update(Observable presenter, Object input) {
		displayQuestion();
	}
	public void displayQuestion() {
		question = ApplicationContext.presentQuestionResponseBoundary.getPresentableQuestion();
		System.out.println(question.getContent());
	}

}