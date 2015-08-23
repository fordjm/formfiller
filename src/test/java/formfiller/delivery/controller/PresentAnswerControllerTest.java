package formfiller.delivery.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableAnswer;
import formfiller.delivery.router.RouterTestHelper;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
import formfiller.entities.Answer;
import formfiller.utilities.MockCreation;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class PresentAnswerControllerTest {
	private PresentAnswerController controller;
	private ParsedUserRequest mockParsedUserRequest;
	private Answer foundAnswer;

	private PresentableAnswer getPresentableAnswer(){
		return (PresentableAnswer) ApplicationContext.answerPresenter.getPresentableResponse();
	}
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		mockParsedUserRequest = RouterTestHelper.makeMockParsedRequest("presentAnswer");
		ApplicationContext.answerGateway.save(MockCreation.makeMockNameAnswer("myName"));
		controller = new PresentAnswerController();
	}
	
	public class GivenAnAnswer {
		@Test
		public void requestingPresentableAnswerPutsAnswerAtBoundary() {
			foundAnswer = ApplicationContext.answerGateway.findAnswerByIndex(0);
			
			controller.handle(mockParsedUserRequest);
			
			assertThat(getPresentableAnswer().getId(), is(foundAnswer.getId()));
			assertThat(getPresentableAnswer().getMessage(), is(foundAnswer.getContent()));
		}
	}

}
