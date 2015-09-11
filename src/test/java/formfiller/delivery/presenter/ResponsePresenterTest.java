package formfiller.delivery.presenter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.delivery.viewModel.PresentableResponseViewModel;
import formfiller.enums.Outcome;
import formfiller.response.models.PresentableResponse;

import static org.hamcrest.CoreMatchers.*;

public class ResponsePresenterTest {
	ResponsePresenter responsePresenter;
	
	private PresentableResponse makePresentableResponse(){
		PresentableResponse result = Mockito.mock(PresentableResponse.class);
		result.message = "Request was not found.";
		return result;
	}

	@Before
	public void setUp() {
		responsePresenter = new ResponsePresenter(new PresentableResponseViewModel());
	}
	
	@Test
	public void atStart_PresenterHasNoPresentableResponse(){
		PresentableResponse currentResponse = 
				responsePresenter.getPresentableResponse();
		
		assertThat(currentResponse, is(instanceOf(PresentableResponse.class)));
		assertThat(currentResponse.message, is(""));
		assertThat(currentResponse.outcome, is(Outcome.NEUTRAL));
	}
	
	@Test
	public void canPresentResponse() {
		PresentableResponse presentableResponse = makePresentableResponse();
		
		responsePresenter.present(presentableResponse);
		PresentableResponse presentedResponse = 
				responsePresenter.getPresentableResponse();
		
		assertThat(presentedResponse, is(presentableResponse));
		assertThat(presentedResponse.message, is("Request was not found."));
	}
}
