package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.boundaries.UseCase;
import formfiller.enums.ActionOutcome;

@RunWith(HierarchicalContextRunner.class)
public class ExecutedUseCaseImplTest {	
	private ExecutedUseCaseImpl executedUseCaseImpl;
	private UseCase mockUseCase;
	private ActionOutcome outcome;
	private String message;

	private UseCase makeMockUseCase(){
		UseCase result = Mockito.mock(UseCase.class);		
		return result;
	}
	
	public class GivenANullUseCase {
		
		@Before
		public void givenANullUseCase(){
			mockUseCase = null;
			outcome = ActionOutcome.NO_OUTCOME;
			message = "messsage";
		}
		@Test(expected = ExecutedUseCaseImpl.IllegalUseCase.class)
		public void constructorThrowsException(){
			executedUseCaseImpl = new ExecutedUseCaseImpl(mockUseCase, outcome, message);
		}
		
	}
	
	public class GivenANullOutcome {
		
		@Before
		public void givenANullOutcome(){
			mockUseCase = makeMockUseCase();
			outcome = null;
			message = "message";
		}
		@Test(expected = ExecutedUseCaseImpl.IllegalOutcome.class)
		public void constructorThrowsException(){
			executedUseCaseImpl = new ExecutedUseCaseImpl(mockUseCase, outcome, message);
		}
		
	}
	
	public class GivenANullMessage {
		@Before
		public void givenANullMessage(){
			mockUseCase = makeMockUseCase();
			outcome = ActionOutcome.FAILED;
			message = null;
		}
		@Test
		public void getMessageReturnsTheEmptyString(){
			executedUseCaseImpl = new ExecutedUseCaseImpl(mockUseCase, outcome, message);
			
			String returnedMessage = executedUseCaseImpl.getMessage();
			
			assertThat(returnedMessage, is(""));
		}
	}
	
	public class GivenLegalUseCaseOutcomeAndMessage {
		
		@Before
		public void givenLegalUseCaseAndOutcome(){
			mockUseCase = makeMockUseCase();
			outcome = ActionOutcome.NO_OUTCOME;
			message = "message";
		}
		@Test
		public void test() {
			executedUseCaseImpl = new ExecutedUseCaseImpl(mockUseCase, outcome, message);
			
			assertThat(executedUseCaseImpl.getUseCase(), is(mockUseCase));
			assertThat(executedUseCaseImpl.getOutcome(), is(ActionOutcome.NO_OUTCOME));
			assertThat(executedUseCaseImpl.getMessage(), is("message"));
		}
		
	}

}
