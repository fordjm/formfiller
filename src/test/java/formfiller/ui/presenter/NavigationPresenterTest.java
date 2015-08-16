package formfiller.ui.presenter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import formfiller.usecases.navigation.NavigationOutcome;
import formfiller.usecases.navigation.PresentableNavigation;
import formfiller.usecases.navigation.PresentableNavigationImpl;

public class NavigationPresenterTest {

	private NavigationPresenter presenter;
	private PresentableNavigation presentableNavigation;

	// TODO:  Replace these with mocks. (Move them to a new PresentableNavigationTest class)
	PresentableNavigation makeFailedPresentableNavigation() {
		return makePresentableNavigation(
				NavigationOutcome.FAILED, getAnswerRequiredMessage());
	}
	private String getAnswerRequiredMessage(){
		return "Sorry, you cannot move ahead.  "
				+ "The current question requires a response.";
	}
	private PresentableNavigation makeSucceessfulPresentableNavigation() {
		return makePresentableNavigation(
				NavigationOutcome.SUCCESSFUL, "");
	}
	private PresentableNavigation makePresentableNavigation(
			NavigationOutcome navigationOutcome, String message) {
		return new PresentableNavigationImpl(navigationOutcome, message);
	}	
	
	// TODO:  Improve these.
	@Before
	public void setUp(){
		presenter = new NavigationPresenter();
	}
	@Test
	public void canPresentSuccessfulNavigation() {		
		presentableNavigation = makeSucceessfulPresentableNavigation();
		
		presenter.setPresentableNavigation(presentableNavigation);
		
		assertThat(presenter.getPresentableNavigation(), is(presentableNavigation));
	}
	@Test
	public void canPresentFailedNavigation() {		
		presentableNavigation = makeFailedPresentableNavigation();
		
		presenter.setPresentableNavigation(presentableNavigation);
		
		assertThat(presenter.getPresentableNavigation(), is(presentableNavigation));
	}

}
