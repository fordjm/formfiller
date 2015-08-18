package formfiller.delivery.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.ApplicationContext;
import formfiller.usecases.PresentableAnswer;
import formfiller.utilities.TestSetup;

public class ConsoleAnswerViewTest {
	private ConsoleAnswerView consoleAnswerView;
	
	private PresentableAnswer presentableAnswer(String message){
		PresentableAnswer result = new PresentableAnswer();
		result.setMessage(message);
		return result;
	}
	
	@Before
	public void setUp() {
		TestSetup.setupContext();
		consoleAnswerView = new ConsoleAnswerView();
	}
	@Test
	public void doesNotPresentEmptyResponse() {
		ApplicationContext.answerPresenter.addObserver(consoleAnswerView);
		ApplicationContext.answerPresenter.present(presentableAnswer(""));
	}	
	@Test
	public void doesDisplayNonEmptyResponse() {
		ApplicationContext.answerPresenter.addObserver(consoleAnswerView);
		ApplicationContext.answerPresenter.present(presentableAnswer("message"));
		/*AnswerPresenter answerPresenter = Mockito.mock(AnswerPresenter.class);
		Mockito.when(answerPresenter.getPresentableResponse()).thenReturn(new PresentableAnswer());*/
		//consoleAnswerView.update(answerPresenter, null);
		//Mockito.verify(answerPresenter).getPresentableResponse();
	}

}
