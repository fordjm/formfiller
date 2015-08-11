package formfiller.ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.gateways.ApplicationContext;
import formfiller.usecases.PresentableQuestion;

public class QuestionPresenterTest {
	private PresentableQuestion question;

	@Before
	public void setUp(){
		question = new PresentableQuestion("id", "content");
	}

	@Test
	public void canCreateQuestionPresenter() {
		QuestionPresenter questionPresenter = new QuestionPresenter();
		//QuestionView consoleView = Mockito.mock(ConsoleView.class);
		QuestionView consoleView = new ConsoleView();
		questionPresenter.addObserver(consoleView);
		//Mockito.verify(consoleView).displayQuestion();					TODO:  Make this work.
		ApplicationContext.presentQuestionBoundary.setQuestion(question);
	}

}
