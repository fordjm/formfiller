package formfiller.delivery.presenter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.boundaryCrossers.PresentableNavigation;
import formfiller.enums.ActionOutcome;

@RunWith(HierarchicalContextRunner.class)
public class NavigationPresenterTest {

	private NavigationPresenter presenter;
	private PresentableNavigation presentableNavigation;

	PresentableNavigation makeMockPresentableNavigationSucceeded() {
		return makeMockPresentableNavigation("", 
				ActionOutcome.SUCCEEDED);
	}
	private PresentableNavigation makeMockPresentableNavigation(String message, 
			ActionOutcome outcome) {
		PresentableNavigation result = Mockito.mock(PresentableNavigation.class);
		Mockito.when(result.getMessage()).thenReturn(message);
		Mockito.when(result.getOutcome()).thenReturn(outcome);
		return result;
	}
	
	@Before
	public void setUp(){
		presenter = new NavigationPresenter();
	}
	
	public class GivenANullPresentableNavigation {
		
		@Test(expected = AbstractPresenter.IllegalPresentableResponse.class)
		public void setPresentableResponseThrowsException(){
			presenter.setPresentableResponse(null);
		}
		
	}
	
	public class GivenAPresentableNavigation {
		
		@Before
		public void givenAPresentableNavigationSucceeded(){
			presentableNavigation = makeMockPresentableNavigationSucceeded();
		}
		@Test
		public void getPresentableResponseReturnsGivenPresentableNavigation(){
			presenter.setPresentableResponse(presentableNavigation);
			
			PresentableNavigation presentableResponse = presenter.getPresentableResponse();
			
			assertThat(presentableResponse, is(presentableNavigation));
		}
	}

}
