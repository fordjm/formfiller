package formfiller.delivery.presenter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.*;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.boundaryCrossers.PresentableResponseImpl;

public class HandleUnfoundControllerPresenterTest {
	HandleUnfoundControllerPresenter handleUnfoundControllerPresenter;
	
	private PresentableResponse makePresentableResponse(){
		PresentableResponse result = Mockito.mock(PresentableResponseImpl.class);
		Mockito.when(result.getMessage()).thenReturn("Request was not found.");
		return result;
	}

	@Before
	public void setUp() {
		handleUnfoundControllerPresenter = new HandleUnfoundControllerPresenter();
	}
	@Test
	public void canSomething() {
		PresentableResponse presentableResponse = makePresentableResponse();
		
		handleUnfoundControllerPresenter.present(presentableResponse);
		PresentableResponse presentedResponse = 
				handleUnfoundControllerPresenter.getPresentableResponse();
		
		assertThat(presentedResponse, is(presentableResponse));
		assertThat(presentedResponse.getMessage(), is("Request was not found."));
	}
}
