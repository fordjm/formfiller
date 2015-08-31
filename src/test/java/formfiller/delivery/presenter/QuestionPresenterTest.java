package formfiller.delivery.presenter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.boundaryCrossers.PresentableQuestion;
import formfiller.entities.Prompt;

public class QuestionPresenterTest {
	PresentableQuestion presentableQuestion;
	Prompt mockQuestion;

	PresentableQuestion makeMockPresentableQuestion(String id, String content) {
		PresentableQuestion result = Mockito.mock(PresentableQuestion.class);
		result.id = id;
		result.message = content;
		return result;
	}

	@Before
	public void setUp(){
		presentableQuestion = makeMockPresentableQuestion("name", "What is your name?");
	}
	
	@Test
	public void canPresentQuestion() {
		QuestionPresenter questionPresenter = new QuestionPresenter();
		questionPresenter.present(presentableQuestion);
		assertEquals(presentableQuestion, questionPresenter.getPresentableResponse());
	}
}
