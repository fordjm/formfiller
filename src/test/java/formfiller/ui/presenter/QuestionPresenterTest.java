package formfiller.ui.presenter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.Prompt;
import formfiller.usecases.presentQuestion.PresentableQuestion;
import formfiller.usecases.presentQuestion.PresentableQuestionFactory;
import formfiller.usecases.presentQuestion.PresentableQuestionFactoryImpl;
import formfiller.utilities.MockCreation;

public class QuestionPresenterTest {
	PresentableQuestion presentableQuestion;
	Prompt mockQuestion;

	PresentableQuestion makePresentableQuestion(Prompt requestedQuestion) {
		PresentableQuestionFactory factory = new PresentableQuestionFactoryImpl();
		PresentableQuestion result = factory.makePresentableQuestion();
		result.setId(requestedQuestion.getId());
		result.setContent(requestedQuestion.getContent());
		return result;
	}

	@Before
	public void setUp(){
		mockQuestion = MockCreation.makeMockNameQuestion();
		presentableQuestion = makePresentableQuestion(mockQuestion);
	}
	@Test
	public void canPresentQuestion() {
		QuestionPresenter questionPresenter = new QuestionPresenter();
		questionPresenter.setPresentableQuestion(presentableQuestion);
		assertEquals(presentableQuestion, questionPresenter.getPresentableQuestion());
	}

}
