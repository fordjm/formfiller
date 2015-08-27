package formfiller.usecases.presentAnswer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableAnswer;
import formfiller.utilities.AnswerMocker;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class PresentAnswerTest {
	private PresentAnswerUseCase presentAnswerUseCase;
	
	PresentableAnswer getCurrentPresentableAnswer() {
		PresentableAnswer result = (PresentableAnswer) 
				ApplicationContext.answerPresenter.getPresentableResponse();
		return result;
	}

	@Before
	public void setupTest() {
		TestSetup.setupContext();
		presentAnswerUseCase = new PresentAnswerUseCase();
	}

	public class GivenNoAnswers {

		@Test
		public void presentingAnswerPresentsANoAnswerObject(){
			presentAnswerUseCase.execute(null);
			PresentableAnswer presentedAnswer = 
					(PresentableAnswer) ApplicationContext.answerPresenter.getPresentableResponse();

			assertThat(presentedAnswer.getId(), is(-1));
			assertThat(presentedAnswer.getMessage(), is(""));
		}	

	}

	public class GivenAnAnswer {

		@Before
		public void givenAnAnswer(){
			ApplicationContext.answerGateway.save(AnswerMocker.makeMockNameAnswer("nameAnswer"));
		}
		@Test
		public void presentingAnswerPresentsTheGivenAnswer(){
			presentAnswerUseCase.execute(null);
			PresentableAnswer presentedAnswer = getCurrentPresentableAnswer();

			assertThat(presentedAnswer.getId(), is(0));
			assertThat(presentedAnswer.getMessage(), is("nameAnswer"));
		}

	}
}
