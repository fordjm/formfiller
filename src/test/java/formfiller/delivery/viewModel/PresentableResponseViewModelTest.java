package formfiller.delivery.viewModel;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.response.models.PresentableResponse;

public class PresentableResponseViewModelTest implements Observer {
	private PresentableResponseViewModel presentableResponseViewModel;
	private Boolean observerWasUpdated;
	private String message;

	public void update(Observable o, Object input) {
		PresentableResponse response = (PresentableResponse) input;
		message = response.message;
		observerWasUpdated = true;
	}

	@Before
	public void setUp() {
		presentableResponseViewModel = new PresentableResponseViewModel();
		observerWasUpdated = false;
		message = "";
	}

	@Test
	public void isObservable() {
		assertThat(presentableResponseViewModel, is(instanceOf(Observable.class)));
	}
	
	@Test
	public void hasNoObservers() {
		assertThat(presentableResponseViewModel.countObservers(), is(0));
	}
	
	@Test
	public void canAddObserver() {
		addTestObserverToViewModel();
		
		assertThat(presentableResponseViewModel.countObservers(), is(1));
	}
	
	private void addTestObserverToViewModel() {
		presentableResponseViewModel.addObserver(this);
	}

	@Test
	public void observerIsNotUpdated(){
		assertThat(observerWasUpdated , is(false));
	}

	@Test
	public void messageIsEmptyString(){
		assertThat(message , is(""));
	}
	
	@Test
	public void canUpdateObserver() {
		addTestObserverToViewModel();
		PresentableResponse response = Mockito.mock(PresentableResponse.class);
		response.message = "message";
		
		presentableResponseViewModel.outputPresentableResponse(response);		
		
		assertThat(observerWasUpdated, is(true));
		assertThat(message, is("message"));
	}
	
	//	TODO:	Remove all Observer tests.
	
}
