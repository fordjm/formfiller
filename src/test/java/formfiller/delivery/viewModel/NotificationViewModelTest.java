package formfiller.delivery.viewModel;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.response.models.PresentableResponse;

public class NotificationViewModelTest {
	private NotificationViewModel viewModel;
	private String message;

	@Before
	public void setUp() {
		viewModel = new NotificationViewModel();
		message = "";
	}

	@Test
	public void messageIsEmptyString(){
		assertThat(message , is(""));
	}
	
}
