package formfiller.ui;

import java.util.Observable;
import java.util.Observer;

import formfiller.gateways.ApplicationContext;
import formfiller.usecases.PresentableQuestion;

public class QuestionPresenter extends Observable implements Observer {
	PresentableQuestion question;
	public QuestionPresenter(){
		ApplicationContext.presentQuestionBoundary.addObserver(this);
	}

	public void update(Observable observable, Object input) {
		setChanged();
		notifyObservers();
	}
	
}
