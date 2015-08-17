package formfiller.delivery.presenter;

import java.util.Observable;

import formfiller.boundaries.PresentQuestionResponseBoundary;
import formfiller.boundaryCrossers.PresentableQuestion;

public class QuestionPresenter extends Observable implements PresentQuestionResponseBoundary {
	PresentableQuestion presentableQuestion;
	
	public QuestionPresenter(){	}

	public PresentableQuestion getPresentableResponse() {
		return presentableQuestion;
	}
	public void setPresentableQuestion(PresentableQuestion presentableQuestion) {
		this.presentableQuestion = presentableQuestion;
		setChanged();
		notifyObservers();
	}
	
}
