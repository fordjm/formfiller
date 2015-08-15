package formfiller.ui.presenter;

import java.util.Observable;

import formfiller.boundaries.PresentQuestionResponseBoundary;
import formfiller.usecases.presentQuestion.PresentableQuestion;

public class QuestionPresenter extends Observable implements PresentQuestionResponseBoundary {
	PresentableQuestion presentableQuestion;
	
	public QuestionPresenter(){	}

	public PresentableQuestion getPresentableQuestion() {
		return presentableQuestion;
	}
	public void setPresentableQuestion(PresentableQuestion presentableQuestion) {
		this.presentableQuestion = presentableQuestion;
		setChanged();
		notifyObservers();
	}
	
}
