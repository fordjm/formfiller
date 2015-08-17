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
	public class GivenNoAnswer{
		@Test
		public void whenPresentAnswerRuns_ThenItPresentsNoAnswers(){
			PresentableAnswer<T> presentableAnswer = presentAnswerUseCase.presentAnswer();
			assertThat(presentableAnswer.id, is(-1));
			assertThat(presentableAnswer.content, is(equalTo((Object) "")));
		}		
	}	
	public class GivenAnAnswer{
		@Before
		public void givenAnAnswer(){
			Answer answer = new AnswerImpl<String>(0, "Response content");
			ApplicationContext.answerGateway.save(answer);
			ApplicationContext.answerGateway.findAnswerByIndexOffset(1);
		}
		@Test
		public void whenPresentAnswerRuns_ThenItPresentsAnAnswer(){
			PresentableAnswer<T> presentableResponse = 
					presentAnswerUseCase.presentAnswer();
			assertThat(presentableResponse.id, is(0));
			assertThat(presentableResponse.content, is(equalTo((Object) "Response content")));
		}
	}
}
