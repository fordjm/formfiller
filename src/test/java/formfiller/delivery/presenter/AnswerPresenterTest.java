package formfiller.delivery.presenter;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.boundaryCrossers.PresentableAnswer;

@RunWith(HierarchicalContextRunner.class)
public class AnswerPresenterTest {
	private AnswerPresenter answerPresenter;
	
	@Before
	public void setUp() {
		answerPresenter = new AnswerPresenter();
	}
	
	@Test(expected = AbstractPresenter.NullPresentableResponse.class)
	public void settingPresentableResponseToNullThrowsException(){
		answerPresenter.present(null);
	}
	
	public class GivenAPresentableAnswer {
		private PresentableAnswer presentableAnswer;
		
		@Before
		public void givenAPresentableAnswer(){
			presentableAnswer = new PresentableAnswer();
			presentableAnswer.message = "";
		}
		
		@Test
		public void presentingAnswerPresentsGivenAnswer() {
			answerPresenter.present(presentableAnswer);
			PresentableAnswer response = 
					(PresentableAnswer) answerPresenter.getPresentableResponse();
			assertThat(response, is(presentableAnswer));
		}		
	}
}
