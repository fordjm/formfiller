package formfiller.usecases.handleUnfoundController;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.ApplicationContext;
import formfiller.request.models.HandleUnfoundControllerRequest;
import formfiller.response.models.PresentableResponse;
import formfiller.utilities.TestSetup;

public class HandleUnfoundControllerTest {
	private final String REQUEST_NOT_FOUND = "Request was not found.";
	private HandleUnfoundControllerUseCase handleUnfoundControllerUseCase;
	private PresentableResponse currentPresentableResponse;

	private PresentableResponse getPresentableResponseFromPresenter() {
		return ApplicationContext.responsePresenter.getPresentableResponse();
	}

	@Before
	public void setUp() {
		TestSetup.setupContext();
		handleUnfoundControllerUseCase = new HandleUnfoundControllerUseCase();
	}
	
	@Test
	public void atStart_PresenterHasNoPresentableResponse(){
		currentPresentableResponse = getPresentableResponseFromPresenter();
		assertThat(currentPresentableResponse, is(equalTo(null)));
	}
	
	@Test
	public void executionPassesRequestDataToResponse() {
		HandleUnfoundControllerRequest mockRequest = Mockito.mock(HandleUnfoundControllerRequest.class);
		mockRequest.message = REQUEST_NOT_FOUND;
		
		handleUnfoundControllerUseCase.execute(mockRequest);
		currentPresentableResponse = getPresentableResponseFromPresenter();
		
		assertThat(currentPresentableResponse.message, is(REQUEST_NOT_FOUND));
	}
}
