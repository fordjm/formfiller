package formfiller.delivery.view;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.boundaries.Presenter;
import formfiller.delivery.presenter.AnswerPresenter;
import formfiller.usecases.PresentableAnswer;

@RunWith(HierarchicalContextRunner.class)
public class ConsoleAnswerViewTest {
	private ConsoleAnswerView consoleAnswerView;
	
	private PresentableAnswer presentableAnswer(String message){
		PresentableAnswer result = new PresentableAnswer();
		result.setMessage(message);
		return result;
	}
	private AnswerPresenter makeMockPresenter() {
		AnswerPresenter result = Mockito.mock(AnswerPresenter.class);
		return result;
	}
	private void assignPresentableResponse(Presenter presenter, PresentableAnswer presentableAnswer) {
		Mockito.when(presenter.getPresentableResponse()).thenReturn(presentableAnswer);
	}
	
	public class GivenAPresentableAnswer {
		private AnswerPresenter answerPresenter;
		
		@Before
		public void setUp() {
			consoleAnswerView = new ConsoleAnswerView();
			answerPresenter = makeMockPresenter();
		}

		public class GivenAnEmptyStringResponse {
			@Before
			public void givenAnEmptyStringResponse(){
				assignPresentableResponse(answerPresenter, presentableAnswer(""));
				ApplicationContext.answerPresenter = answerPresenter;
			}
			@Test
			public void doesNotPresentEmptyResponse() {
				consoleAnswerView.update(answerPresenter, null);
				Mockito.verify(answerPresenter).getPresentableResponse();
				assertThat(consoleAnswerView.wasDisplayed(), is(false));
			}	
			
		}
		
		public class GivenANonEmptyResponse {
			
			@Before
			public void givenANonEmptyResponse(){
				assignPresentableResponse(answerPresenter, presentableAnswer("message"));
				ApplicationContext.answerPresenter = answerPresenter;				
			}
			@Test
			public void doesDisplayNonEmptyResponse() {
				consoleAnswerView.update(answerPresenter, null);
				Mockito.verify(answerPresenter).getPresentableResponse();
				assertThat(consoleAnswerView.wasDisplayed(), is(true));
			}
			
		}		
	}

}
