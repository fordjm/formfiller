package formfiller.usecases;

import java.util.Observable;

public class PresentQuestionBoundary extends Observable {
	PresentableQuestion presentableQuestion = null;
	
	public PresentableQuestion getQuestion(){
		return presentableQuestion;
	}
	public void setQuestion(PresentableQuestion presentableQuestion){
		this.presentableQuestion = presentableQuestion;
		setChanged();
		notifyObservers();
	}
}
