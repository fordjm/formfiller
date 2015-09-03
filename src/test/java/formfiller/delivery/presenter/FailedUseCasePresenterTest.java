package formfiller.delivery.presenter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.response.models.PresentableResponse;

import static org.hamcrest.CoreMatchers.*;

/* Orphans from NavigationTest:
assertThat(getPresentedNavigation().outcome, is(ActionOutcome.FAILED));
assertEquals(getFailedNavigationResult(), getPresentedNavigation().message);

			private PresentableResponse getPresentedNavigation() {
				return (PresentableResponse)
						ApplicationContext.navigationPresenter.getPresentableResponse();
			}

			private String getFailedNavigationResult(){
				return "Sorry, you cannot move ahead.  The current question requires a response.";
			}
*/

//	TODO:	What should we really test here?
public class FailedUseCasePresenterTest {
	FailedUseCasePresenter failedUseCasePresenter;
	
	private PresentableResponse makePresentableResponse(){
		PresentableResponse result = Mockito.mock(PresentableResponse.class);
		result.message = "Request was not found.";
		return result;
	}

	@Before
	public void setUp() {
		failedUseCasePresenter = new FailedUseCasePresenter();
	}
	
	@Test
	public void canPresentResponse() {
		PresentableResponse presentableResponse = makePresentableResponse();
		
		failedUseCasePresenter.present(presentableResponse);
		PresentableResponse presentedResponse = 
				failedUseCasePresenter.getPresentableResponse();
		
		assertThat(presentedResponse, is(presentableResponse));
		assertThat(presentedResponse.message, is("Request was not found."));
	}
}
