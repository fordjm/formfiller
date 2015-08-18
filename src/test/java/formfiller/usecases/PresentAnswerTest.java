package formfiller.usecases;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.ApplicationContext;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class PresentAnswerTest<T> {
	private PresentAnswerUseCase presentAnswerUseCase;
	
	@Before
	public void setupTest() {
		TestSetup.setupContext();
		presentAnswerUseCase = new PresentAnswerUseCase();
	}
	@Test
	public void executingDummyUseCaseDoesNothing(){
		presentAnswerUseCase.execute(null);
	}
}
