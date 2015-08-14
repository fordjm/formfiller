package formfiller.usecases.presentQuestion;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import formfiller.ApplicationContext;
import formfiller.ui.RouterTestHelper;
import formfiller.ui.consoleUi.ParsedUserRequest;
import formfiller.utilities.MockCreation;
import formfiller.utilities.TestSetup;

public class PresentQuestionControllerTest {
	PresentQuestionController presentQuestionController;
	ParsedUserRequest mockParsedInput;
	PresentableQuestion presentableQuestion;
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		mockParsedInput = RouterTestHelper.makeMockParsedInput("presentQuestion");
		ApplicationContext.questionGateway.save(MockCreation.makeMockNameQuestion());
		ApplicationContext.questionGateway.findQuestionByIndexOffset(1);
		presentQuestionController = new PresentQuestionController();
	}
	@Test
	public void requestingPresentableQuestionPutsQuestionAtBoundary() {
		presentQuestionController.handle(mockParsedInput);
		presentableQuestion = ApplicationContext.presentQuestionResponseBoundary.getPresentableQuestion();
		assertEquals(presentableQuestion.getId(), "name");
		assertEquals(presentableQuestion.getContent(), "What is your name?");
	}

}
