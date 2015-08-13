package formfiller.usecases.presentQuestion;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import formfiller.ApplicationContext;
import formfiller.usecases.presentQuestion.PresentQuestionController;
import formfiller.usecases.presentQuestion.PresentableQuestion;
import formfiller.utilities.TestSetup;
import formfiller.utilities.TestUtil;

public class PresentQuestionControllerTest {
	PresentQuestionController controller;
	PresentableQuestion presentableQuestion;
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		ApplicationContext.questionGateway.save(TestUtil.makeMockNameQuestion());
		ApplicationContext.questionGateway.findQuestionByIndexOffset(1);
		controller = new PresentQuestionController();
	}
	@Test
	public void requestingPresentableQuestionPutsQuestionAtBoundary() {
		controller.requestQuestionPresentation();
		presentableQuestion = ApplicationContext.presentQuestionResponseBoundary.getPresentableQuestion();
		assertEquals(presentableQuestion.getId(), "name");
		assertEquals(presentableQuestion.getContent(), "What is your name?");
	}

}
