package formfiller.ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.boundaries.QuestionPresentation;

public class QuestionPresenterTest {

	@Before
	public void setUp(){
		QuestionPresentation.presentableQuestion.setId("id");
		QuestionPresentation.presentableQuestion.setContent("content");
	}

	@Test
	public void canCreateQuestionPresenter() {
		QuestionPresenter questionPresenter = new QuestionPresenter();
		//QuestionView consoleView = Mockito.mock(ConsoleView.class);
		QuestionView consoleView = new ConsoleView();
		questionPresenter.addObserver(consoleView);
		//Mockito.verify(consoleView).displayQuestion();					TODO:  Make this work.
		questionPresenter.presentQuestion();
	}

}
