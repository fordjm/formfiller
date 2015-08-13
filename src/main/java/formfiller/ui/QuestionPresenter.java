package formfiller.ui;

import java.util.Observable;

import formfiller.boundaries.QuestionPresentation;

public class QuestionPresenter extends Observable implements QuestionPresentation {
	public QuestionPresenter(){	}

	public void presentQuestion() {
		setChanged();
		notifyObservers(presentableQuestion);
	}
	
}
