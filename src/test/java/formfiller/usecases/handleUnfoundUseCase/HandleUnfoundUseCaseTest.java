package formfiller.usecases.handleUnfoundUseCase;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.Context;
import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.request.models.HandleUnfoundUseCaseRequest;
import formfiller.usecases.handleUnfoundController.HandleUnfoundUseCaseUseCase;
import formfiller.utilities.TestSetup;

public class HandleUnfoundUseCaseTest {
	private final String REQUEST_NOT_FOUND = "Request was not found.";
	private HandleUnfoundUseCaseUseCase useCase;
	private NotificationViewModel viewModel;

	private NotificationViewModel getViewModelFromPresenter() {
		return (NotificationViewModel) Context.outcomePresenter.getViewModel();
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
		viewModel = getViewModelFromPresenter();
		
		assertThat(viewModel.message, is(REQUEST_NOT_FOUND));
	}
}
