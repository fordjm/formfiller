package formfiller.delivery.presenter;

import java.util.Observable;

import formfiller.boundaries.Presenter;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.delivery.View;

public abstract class AbstractPresenter extends Observable implements Presenter {
	PresentableResponse presentableResponse;

	public void addObserver(View view){
		super.addObserver(view);
	}
	public PresentableResponse getPresentableResponse() {
		return presentableResponse;
	}	
	public void setPresentableResponse(PresentableResponse presentableResponse){
		setPresentableResponseFieldValue(presentableResponse);
		setChanged();
		notifyObservers();
	}
	protected abstract void setPresentableResponseFieldValue(PresentableResponse presentableResponse);

}
