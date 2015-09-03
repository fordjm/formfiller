package formfiller.delivery.viewModel;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.delivery.presenter.AnswerPresenter;
import formfiller.response.models.PresentableAnswer;

@RunWith(HierarchicalContextRunner.class)
public class AnswerViewModelTest {
	private AnswerViewModel consoleAnswerView;
	
	private PresentableAnswer makePresentableAnswer(String message){
		PresentableAnswer result = new PresentableAnswer();
		result.message = message;
		return result;
	}
	
	private AnswerPresenter makeMockPresenter() {
		AnswerPresenter result = Mockito.mock(AnswerPresenter.class);
		return result;
	}
	
	public class GivenAPresentableAnswer {
		private AnswerPresenter answerPresenter;
		private PresentableAnswer presentableAnswer;
		
		@Before
		public void setUp() {
			consoleAnswerView = new AnswerViewModel();
			answerPresenter = makeMockPresenter();
		}

		public class GivenAnEmptyStringResponse {
			
			@Before
			public void givenAnEmptyStringResponse(){
				presentableAnswer = makePresentableAnswer("");
			}
			
			@Test
			public void doesNotPresentEmptyResponse() {
				consoleAnswerView.update(answerPresenter, presentableAnswer);
				assertThat(consoleAnswerView.wasDisplayed(), is(false));
			}				
		}
		
		public class GivenANonEmptyResponse {
			
			@Before
			public void givenANonEmptyResponse(){
				presentableAnswer = makePresentableAnswer("message");
				ApplicationContext.answerPresenter = answerPresenter;				
			}
			
			@Test
			public void doesDisplayNonEmptyResponse() {
				consoleAnswerView.update(answerPresenter, presentableAnswer);
				assertThat(consoleAnswerView.wasDisplayed(), is(true));
			}			
		}		
	}
}
