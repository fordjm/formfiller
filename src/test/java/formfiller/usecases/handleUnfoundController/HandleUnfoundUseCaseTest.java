package formfiller.usecases.handleUnfoundController;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.ApplicationContext;
import formfiller.request.models.HandleUnfoundUseCaseRequest;
import formfiller.response.models.PresentableResponse;
import formfiller.utilities.TestSetup;

public class HandleUnfoundUseCaseTest {
	private final String REQUEST_NOT_FOUND = "Request was not found.";
	private HandleUnfoundUseCaseUseCase useCase;
	private PresentableResponse currentPresentableResponse;

	private PresentableResponse getPresentableResponseFromPresenter() {
		return ApplicationContext.responsePresenter.getPresentableResponse();
	}

	@Before
	public void setUp() {
		TestSetup.setupContext();
		useCase = new HandleUnfoundUseCaseUseCase();
	}
	
	@Test
	public void canHandleNull() {
		useCase.execute(null);
	}
	
	@Test
	public void executionPassesRequestDataToResponse() {
		HandleUnfoundUseCaseRequest mockRequest = Mockito.mock(HandleUnfoundUseCaseRequest.class);
		mockRequest.message = REQUEST_NOT_FOUND;
		
		useCase.execute(mockRequest);
		currentPresentableResponse = getPresentableResponseFromPresenter();
		
		assertThat(currentPresentableResponse.message, is(REQUEST_NOT_FOUND));
	}
}
