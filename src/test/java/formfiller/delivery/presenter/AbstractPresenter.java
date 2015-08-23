package formfiller.delivery.presenter;

import java.util.Observable;

import formfiller.boundaries.Presenter;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.delivery.View;

public abstract class AbstractPresenter extends Observable implements Presenter {

	public void addObserver(View view){
		super.addObserver(view);
	}	
	public void present(PresentableResponse presentableResponse){
		if (presentableResponse == null) 
			throw new IllegalPresentableResponse();
		setPresentableResponseFieldValue(presentableResponse);
		setChanged();
		notifyObservers();
	}
	protected abstract void setPresentableResponseFieldValue(PresentableResponse presentableResponse);

	public class IllegalPresentableResponse extends RuntimeException { }
}
