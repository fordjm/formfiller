package formfiller.ui;

import java.util.Observable;

import formfiller.usecases.PresentableQuestion;
import formfiller.usecases.PresentableQuestionFactoryImpl.PresentableQuestionImpl;

public class ConsoleView implements QuestionView {
	PresentableQuestion question;

	public void update(Observable presenter, Object input) {
		if (input instanceof PresentableQuestionImpl){
			question = (PresentableQuestionImpl) input;
			displayQuestion();
		}
	}
	public void displayQuestion() {
		System.out.println(question.getContent());
	}

}
