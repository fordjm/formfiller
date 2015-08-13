package formfiller.ui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.Prompt;
import formfiller.usecases.presentQuestion.PresentableQuestion;
import formfiller.usecases.presentQuestion.PresentableQuestionFactory;
import formfiller.usecases.presentQuestion.PresentableQuestionFactoryImpl;
import formfiller.utilities.TestSetup;
import formfiller.utilities.TestUtil;

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
		mockQuestion = TestUtil.makeMockNameQuestion();
		presentableQuestion = makePresentableQuestion(mockQuestion);
	}
	@Test
	public void canPresentQuestion() {
		QuestionPresenter questionPresenter = new QuestionPresenter();
		questionPresenter.presentQuestion(presentableQuestion);
		assertEquals(presentableQuestion, questionPresenter.getPresentableQuestion());
	}

}
