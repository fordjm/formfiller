package formfiller.delivery.presenter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.response.models.NotificationResponseModel;
import formfiller.response.models.PresentableResponse;

import static org.hamcrest.CoreMatchers.*;

public class ResponsePresenterTest {
	NotificationPresenter presenter;
	
	private NotificationResponseModel makeResponseModel(){
		NotificationResponseModel result = Mockito.mock(NotificationResponseModel.class);
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
		NotificationResponseModel response = makeResponseModel();
		
		presenter.present(response);	
		NotificationViewModel castViewModel = (NotificationViewModel) presenter.getViewModel();
		
		assertThat(castViewModel.message, is("Request was not found."));
	}
}
