package formfiller.delivery;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.delivery.PresentableResponseImpl;
import formfiller.entities.ExecutedUseCase;
import formfiller.enums.ActionOutcome;

@RunWith(HierarchicalContextRunner.class)
public class PresentableResponseImplTest {

	private PresentableResponseImpl presentableNavigation;

	@Before
	public void setUp() {
		presentableNavigation = new PresentableResponseImpl();
	}
	@Test
	public void getOutcomeReturnsNull(){
		ActionOutcome outcome = presentableNavigation.getOutcome();
		
		assertThat(outcome, is(ActionOutcome.NO_OUTCOME));
	}
	@Test
	public void getMessageReturnsEmptyString(){
		String message = presentableNavigation.getMessage();

		assertThat(message, is(""));
	}
	
	public class GivenANullOutcome {
		
		@Test(expected = PresentableResponseImpl.IllegalOutcome.class)
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
	
	// TODO:  What is this really testing?
	public class NewExecutedUseCaseTest {
		
		@Before
		public void setUp(){
			ExecutedUseCase mockExecutedUseCase = Mockito.mock(ExecutedUseCase.class);
			Mockito.when(mockExecutedUseCase.getOutcome()).thenReturn(ActionOutcome.NO_OUTCOME);
			ApplicationContext.executedUseCases.push(mockExecutedUseCase);
		}
		@Test
		public void test() {
			PresentableResponseImpl impl = new PresentableResponseImpl();
			ExecutedUseCase lastUseCase = ApplicationContext.executedUseCases.peek();
			ActionOutcome outcome = lastUseCase.getOutcome();
			assertThat(impl.getOutcome(), is(outcome));
		}
		
	}

}
