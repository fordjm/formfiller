package formfiller.delivery.presenter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.response.models.PresentableResponse;

import static org.hamcrest.CoreMatchers.*;

public class ResponsePresenterTest {
	NotificationPresenter presenter;
	
	private PresentableResponse makePresentableResponse(){
		PresentableResponse result = Mockito.mock(PresentableResponse.class);
		result.message = "Request was not found.";
		return result;
	}

	@Before
	public void setUp() {
		presenter = new NotificationPresenter();
	}
	
	@Test
	public void atStart_PresenterHasNoViewModel(){		
		NotificationViewModel castViewModel = (NotificationViewModel) presenter.getViewModel();
		
		assertNull(castViewModel);
	}
	
	@Test
	public void canPresentResponse() {
		PresentableResponse presentableResponse = makePresentableResponse();
		
		presenter.present(presentableResponse);	
		NotificationViewModel castViewModel = (NotificationViewModel) presenter.getViewModel();
		
		assertThat(castViewModel.message, is("Request was not found."));
	}
}
