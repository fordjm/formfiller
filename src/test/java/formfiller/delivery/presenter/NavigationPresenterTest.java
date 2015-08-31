package formfiller.delivery.presenter;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.boundaryCrossers.PresentableAnswer;
import formfiller.boundaryCrossers.PresentableFormComponent;
import formfiller.boundaryCrossers.PresentableQuestion;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.enums.ActionOutcome;

@RunWith(HierarchicalContextRunner.class)
public class NavigationPresenterTest {
	private NavigationPresenter presenter;
	private PresentableResponse presentableNavigation;

	private PresentableResponse makeMockPresentableNavigation(String message, 
				ActionOutcome outcome) {
		PresentableResponse result;
		if (outcome == ActionOutcome.SUCCEEDED)
			result = makeMockPresentableFormComponent();
		else
			result = Mockito.mock(PresentableResponse.class);
		result.message = message;
		result.outcome = outcome;
		return result;
	}
	
	private PresentableResponse makeMockPresentableFormComponent() {
		PresentableFormComponent result = Mockito.mock(PresentableFormComponent.class);
		result.question = Mockito.mock(PresentableQuestion.class);
		result.answer = Mockito.mock(PresentableAnswer.class);
		return result;
	}

	@Before
	public void setUp(){
		presenter = new NavigationPresenter();
	}
	
	@Test(expected = AbstractPresenter.NullPresentableResponse.class)
	public void presentingNullThrowsException(){
		presenter.present(null);
	}
	
	public class GivenAPresentableResponse {
		
		@Before
		public void givenAPresentableNavigationThatSucceeded(){
			presentableNavigation = makeMockPresentableNavigation("", ActionOutcome.SUCCEEDED);
		}
		@Test
		public void gettingPresentableResponse_ReturnsGivenPresentableNavigation(){
			presenter.present(presentableNavigation);
			
			PresentableResponse presentableResponse = presenter.getPresentableResponse();
			
			assertThat(presentableResponse, is(presentableNavigation));
			assertThat(presentableResponse, is(instanceOf(PresentableFormComponent.class)));
		}
	}
}
