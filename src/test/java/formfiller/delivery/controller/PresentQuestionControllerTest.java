package formfiller.delivery.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableQuestion;
import formfiller.delivery.router.RouterTestHelper;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
import formfiller.utilities.MockCreation;
import formfiller.utilities.TestSetup;

public class PresentQuestionControllerTest {
	PresentQuestionController presentQuestionController;
	ParsedUserRequest mockParsedInput;
	PresentableQuestion presentableQuestion;
	
	private PresentableQuestion presentableQuestion(){
		return (PresentableQuestion) ApplicationContext.questionPresenter.getPresentableResponse();
	}
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		mockParsedInput = RouterTestHelper.makeMockParsedRequest("presentQuestion");
		ApplicationContext.questionGateway.save(MockCreation.makeMockNameQuestion());
		ApplicationContext.currentFormState.findQuestionByIndexOffset(1);
		presentQuestionController = new PresentQuestionController();
	}
	@Test
	public void requestingPresentableQuestionPutsQuestionAtBoundary() {
		presentQuestionController.handle(mockParsedInput);
		presentableQuestion = presentableQuestion();
		assertEquals(presentableQuestion.getId(), "name");
		assertEquals(presentableQuestion.getMessage(), "What is your name?");
	}

}
