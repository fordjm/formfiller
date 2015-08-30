package formfiller.delivery.presenter;

import java.util.Observable;

import formfiller.boundaries.Presenter;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.delivery.ViewModel;

public abstract class AbstractPresenter extends Observable implements Presenter {

	public void addObserver(ViewModel view){
		super.addObserver(view);
	}	
	
	public void present(PresentableResponse presentableResponse){
		if (presentableResponse == null) 
			throw new NullPresentableResponse();
		
		setPresentableResponseFieldValue(presentableResponse);
		setChanged();
		notifyObservers(presentableResponse);
	}
	
	protected abstract void setPresentableResponseFieldValue(PresentableResponse presentableResponse);

	@SuppressWarnings("serial")
	public class NullPresentableResponse extends RuntimeException { }
}