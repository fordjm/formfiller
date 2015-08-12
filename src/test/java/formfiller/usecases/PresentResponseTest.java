package formfiller.usecases;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.gateways.ApplicationContext;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class PresentResponseTest<T> {
	private PresentResponseUseCase presentResponseUseCase;
	
	@Before
	public void setupTest() {
		TestSetup.setupContext();
		presentResponseUseCase = new PresentResponseUseCase();
	}
	public class GivenNoResponse{
		@Test
		public void whenPresentResponseRuns_ThenItPresentsNoResponses(){
			PresentableResponse<T> presentableResponse = presentResponseUseCase.presentResponse();
			assertThat(presentableResponse.id, is(-1));
			assertThat(presentableResponse.content, is(equalTo((Object) "")));
		}		
	}	
	public class GivenAResponse{
		@Before
		public void givenAResponse(){
			Answer response = new AnswerImpl<String>(0, "Response content");
			ApplicationContext.responseGateway.save(response);
			ApplicationContext.responseGateway.findResponseByIndexOffset(1);
		}
		@Test
		public void whenPresentResponseRuns_ThenItPresentsAResponse(){
			PresentableResponse<T> presentableResponse = 
					presentResponseUseCase.presentResponse();
			assertThat(presentableResponse.id, is(0));
			assertThat(presentableResponse.content, is(equalTo((Object) "Response content")));
		}
	}
}
