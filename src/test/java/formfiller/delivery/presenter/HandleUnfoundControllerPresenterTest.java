package formfiller.delivery.presenter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.response.models.PresentableResponse;

import static org.hamcrest.CoreMatchers.*;

public class HandleUnfoundControllerPresenterTest {
	HandleUnfoundControllerPresenter handleUnfoundControllerPresenter;
	
	private PresentableResponse makePresentableResponse(){
		PresentableResponse result = Mockito.mock(PresentableResponse.class);
		result.message = "Request was not found.";
		return result;
	}

	@Before
	public void setUp() {
		handleUnfoundControllerPresenter = new HandleUnfoundControllerPresenter();
	}
	
	@Test
	public void canPresentResponse() {
		PresentableResponse presentableResponse = makePresentableResponse();
		
		handleUnfoundControllerPresenter.present(presentableResponse);
		PresentableResponse presentedResponse = 
				handleUnfoundControllerPresenter.getPresentableResponse();
		
		assertThat(presentedResponse, is(presentableResponse));
		assertThat(presentedResponse.message, is("Request was not found."));
	}
}
