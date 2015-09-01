package formfiller.usecases.handleUnfoundController;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.request.interfaces.HandleUnfoundControllerRequest;
import formfiller.utilities.TestSetup;

public class HandleUnfoundControllerTest {
	private final String REQUEST_NOT_FOUND = "Request was not found.";
	private HandleUnfoundControllerUseCase handleUnfoundControllerUseCase;
	private PresentableResponse currentPresentableResponse;

	private PresentableResponse getPresentableResponseFromPresenter() {
		return ApplicationContext.handleUnfoundControllerPresenter.getPresentableResponse();
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
		Mockito.when(mockRequest.getMessage()).thenReturn(REQUEST_NOT_FOUND);
		
		handleUnfoundControllerUseCase.execute(mockRequest);
		currentPresentableResponse = getPresentableResponseFromPresenter();
		
		assertThat(currentPresentableResponse.message, is(REQUEST_NOT_FOUND));
	}
}
