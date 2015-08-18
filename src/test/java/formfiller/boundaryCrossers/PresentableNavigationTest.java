package formfiller.boundaryCrossers;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.enums.ActionOutcome;

@RunWith(HierarchicalContextRunner.class)
public class PresentableNavigationTest {

	private PresentableNavigation presentableNavigation;

	@Before
	public void setUp() {
		presentableNavigation = new PresentableNavigation();
	}
	@Test
	public void getOutcomeReturnsNull(){
		ActionOutcome outcome = presentableNavigation.getOutcome();
		
		assertNull(outcome);
	}
	@Test
	public void getMessageReturnsEmptyString(){
		String message = presentableNavigation.getMessage();

		assertThat(message, is(""));
	}
	
	public class GivenANullOutcome {
		@Test(expected = PresentableNavigation.IllegalOutcome.class)
		public void setOutcomeThrowsIllegalOutcome() {
			presentableNavigation.setOutcome(null);
		}
	}
	
	public class GivenAnActionOutcome {
		@Before
		public void givenAnActionOutcome(){
			presentableNavigation.setOutcome(ActionOutcome.SUCCEEDED);
		}
		@Test
		public void getOutcomeReturnsTheGivenOutcome() {
			ActionOutcome outcome = presentableNavigation.getOutcome();
			
			assertThat(outcome, is(ActionOutcome.SUCCEEDED));
		}
	}
	
	public class GivenAMessage {
		@Before
		public void givenAMessage(){
			presentableNavigation.setMessage("message");
		}
		@Test
		public void getMessageReturnsGivenMessage(){
			String message = presentableNavigation.getMessage();
			
			assertThat(message, is("message"));
		}
	}

}
