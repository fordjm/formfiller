package formfiller.delivery.presenter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import formfiller.boundaryCrossers.PresentableNavigation;
import formfiller.enums.ActionOutcome;

public class NavigationPresenterImplTest {

	private NavigationPresenterImpl presenter;
	private PresentableNavigation presentableNavigation;

	// TODO:  Replace these with mocks. (Move them to a new PresentableNavigationTest class)
	PresentableNavigation makeFailedPresentableNavigation() {
		return makePresentableNavigation(getAnswerRequiredMessage(), 
				ActionOutcome.FAILED);
	}
	private String getAnswerRequiredMessage(){
		return "Sorry, you cannot move ahead.  "
				+ "The current question requires a response.";
	}
	private PresentableNavigation makeSucceessfulPresentableNavigation() {
		return makePresentableNavigation("", 
				ActionOutcome.SUCCEEDED);
	}
	private PresentableNavigation makePresentableNavigation(String message, 
			ActionOutcome navigationOutcome) {
		return new PresentableNavigation(message, navigationOutcome);
	}	
	
	// TODO:  Improve these.
	@Before
	public void setUp(){
		presenter = new NavigationPresenterImpl();
	}
	@Test
	public void canPresentSuccessfulNavigation() {		
		presentableNavigation = makeSucceessfulPresentableNavigation();
		
		presenter.setPresentableResponse(presentableNavigation);
		
		assertThat(presenter.getPresentableResponse(), is(presentableNavigation));
	}
	@Test
	public void canPresentFailedNavigation() {		
		presentableNavigation = makeFailedPresentableNavigation();
		
		presenter.setPresentableResponse(presentableNavigation);
		
		assertThat(presenter.getPresentableResponse(), is(presentableNavigation));
	}

}
