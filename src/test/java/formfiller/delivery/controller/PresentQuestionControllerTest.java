package formfiller.delivery.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableQuestion;
import formfiller.delivery.router.RouterTestHelper;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
import formfiller.entities.Prompt;
import formfiller.utilities.MockCreation;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class PresentQuestionControllerTest {
	PresentQuestionController presentQuestionController;
	ParsedUserRequest mockParsedInput;
	PresentableQuestion presentableQuestion;
	Prompt foundQuestion;
	
	private PresentableQuestion getPresentableQuestion(){
		return (PresentableQuestion) ApplicationContext.questionPresenter.getPresentableResponse();
	}
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		mockParsedInput = RouterTestHelper.makeMockParsedRequest("presentQuestion");
		ApplicationContext.questionGateway.save(MockCreation.makeMockNameQuestion());
		presentQuestionController = new PresentQuestionController();
	}
	
	public class GivenAQuestion {
		@Test
		public void requestingPresentableQuestionPutsQuestionAtBoundary() {
			foundQuestion = ApplicationContext.questionGateway.findQuestionByIndex(0);
			
			presentQuestionController.handle(mockParsedInput);
			
			assertEquals(getPresentableQuestion().getId(), foundQuestion.getId());
			assertEquals(getPresentableQuestion().getMessage(), foundQuestion.getContent());
		}
	}
	
}
