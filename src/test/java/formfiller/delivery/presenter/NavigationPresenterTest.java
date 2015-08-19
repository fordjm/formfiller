package formfiller.delivery.presenter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.delivery.AbstractPresenter;
import formfiller.delivery.PresentableResponseImpl;
import formfiller.enums.ActionOutcome;

@RunWith(HierarchicalContextRunner.class)
public class NavigationPresenterTest {
	private NavigationPresenter presenter;
	private PresentableResponse presentableNavigation;

	PresentableResponse makeMockPresentableNavigationSucceeded() {
		return makeMockPresentableNavigation("", 
				ActionOutcome.SUCCEEDED);
	}
	private PresentableResponse makeMockPresentableNavigation(String message, 
			ActionOutcome outcome) {
		PresentableResponseImpl result = Mockito.mock(PresentableResponseImpl.class);
		Mockito.when(result.getMessage()).thenReturn(message);
		Mockito.when(result.getOutcome()).thenReturn(outcome);
		return result;
	}
	
	@Before
	public void setUp(){
		presenter = new NavigationPresenter();
	}
	
	@Test(expected = AbstractPresenter.IllegalPresentableResponse.class)
	public void settingPresentableResponseToNullThrowsException(){
		presenter.present(null);
	}
	
	public class GivenAPresentableResponse {
		
		@Before
		public void givenAPresentableNavigationSucceeded(){
			presentableNavigation = makeMockPresentableNavigationSucceeded();
		}
		@Test
		public void getPresentableResponseReturnsGivenPresentableNavigation(){
			presenter.present(presentableNavigation);
			
			PresentableResponse presentableResponse = presenter.getPresentableResponse();
			
			assertThat(presentableResponse, is(presentableNavigation));
		}
	}

}
