package formfiller.ui.consoleUi;

import java.util.Observable;

import formfiller.boundaries.PresentQuestionResponseBoundary;
import formfiller.usecases.presentQuestion.PresentableQuestion;

public class QuestionPresenter extends Observable implements PresentQuestionResponseBoundary {
	PresentableQuestion presentableQuestion;
	
	public QuestionPresenter(){	}

	public void presentQuestion(PresentableQuestion presentableQuestion) {
		this.presentableQuestion = presentableQuestion;
		setChanged();
		notifyObservers();
	}

	public PresentableQuestion getPresentableQuestion() {
		return presentableQuestion;
	}
	
}
