package formfiller.delivery.presenter;

import java.util.Observable;

import formfiller.applicationBoundaryInterfaces.Presenter;
import formfiller.delivery.ViewModel;
import formfiller.response.models.PresentableResponse;

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