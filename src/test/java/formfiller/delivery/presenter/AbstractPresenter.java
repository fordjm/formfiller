package formfiller.delivery.presenter;

import java.util.Observable;

import formfiller.appBoundaries.Presenter;
import formfiller.delivery.ViewModel;
import formfiller.delivery.viewModel.PresentableResponseViewModel;
import formfiller.response.models.PresentableResponse;

public abstract class AbstractPresenter extends Observable implements Presenter {
	ViewModel viewModel = new PresentableResponseViewModel();

	public void addViewModel(ViewModel view){
	}	
	
	public void present(PresentableResponse presentableResponse){
		if (presentableResponse == null) 
			throw new NullPresentableResponse();
		
		setPresentableResponseFieldValue(presentableResponse);
		setChanged();
		notifyObservers(presentableResponse);
		
		viewModel.outputPresentableResponse(presentableResponse);
	}
	
	protected abstract void setPresentableResponseFieldValue(PresentableResponse presentableResponse);

	@SuppressWarnings("serial")
	public class NullPresentableResponse extends RuntimeException { }
}