package formfiller.usecases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import formfiller.boundaries.QuestionPresentation;
import formfiller.gateways.ApplicationContext;
import formfiller.utilities.TestSetup;
import formfiller.utilities.TestUtil;

public class PresentQuestionControllerTest {
	PresentQuestionController controller;
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		ApplicationContext.questionGateway.save(TestUtil.makeMockNameQuestion());
		ApplicationContext.questionGateway.findQuestionByIndexOffset(1);
		controller = new PresentQuestionController();
	}
	@Test
	public void requestingPresentableQuestionPutsQuestionAtBoundary() {
		controller.requestPresentableQuestion();
		
		assertEquals(QuestionPresentation.presentableQuestion.getId(), "name");
		assertEquals(QuestionPresentation.presentableQuestion.getContent(), "What is your name?");
	}

}
