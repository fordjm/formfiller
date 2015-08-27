package formfiller.delivery.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableQuestion;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
import formfiller.entities.Prompt;
import formfiller.utilities.*;

@RunWith(HierarchicalContextRunner.class)
public class PresentQuestionControllerTest {
	PresentQuestionController presentQuestionController;
	ParsedUserRequest mockParsedUserRequest;
	Prompt foundQuestion;
	
	private PresentableQuestion getPresentableQuestion(){
		return (PresentableQuestion) ApplicationContext.questionPresenter.getPresentableResponse();
	}
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		mockParsedUserRequest = 
				ParsedUserRequestMocker.makeMockParsedUserRequest("presentQuestion");
		ApplicationContext.questionGateway.save(QuestionMocker.makeMockNameQuestion());
		presentQuestionController = new PresentQuestionController();
	}
	
	public class GivenAQuestion {
		@Test
		public void requestingPresentableQuestionPutsQuestionAtBoundary() {
			foundQuestion = ApplicationContext.questionGateway.findQuestionByIndex(0);
			
			presentQuestionController.handle(mockParsedUserRequest);
			
			assertEquals(getPresentableQuestion().getId(), foundQuestion.getId());
			assertEquals(getPresentableQuestion().getMessage(), foundQuestion.getContent());
		}
	}
	
}
