package formfiller.ui.presenter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.entities.Prompt;
import formfiller.usecases.presentQuestion.PresentableQuestion;

public class QuestionPresenterTest {
	PresentableQuestion presentableQuestion;
	Prompt mockQuestion;

	PresentableQuestion makeMockPresentableQuestion(String id, String content) {
		PresentableQuestion result = Mockito.mock(PresentableQuestion.class);
		Mockito.when(result.getId()).thenReturn(id);
		Mockito.when(result.getContent()).thenReturn(content);
		return result;
	}

	@Before
	public void setUp(){
		presentableQuestion = makeMockPresentableQuestion("name", "What is your name?");
	}
	@Test
	public void canPresentQuestion() {
		QuestionPresenter questionPresenter = new QuestionPresenter();
		questionPresenter.setPresentableQuestion(presentableQuestion);
		assertEquals(presentableQuestion, questionPresenter.getPresentableQuestion());
	}

}
