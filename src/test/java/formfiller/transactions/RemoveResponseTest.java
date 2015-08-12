package formfiller.transactions;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.Answer;
import formfiller.persistence.FormWidget;
import formfiller.utilities.TestUtil;

@RunWith(HierarchicalContextRunner.class)
public class RemoveResponseTest {
	public class GivenARemoveResponse{
		Transaction removeResponse;
		@Before
		public void givenARemoveResponse(){
			removeResponse = new RemoveResponse();
		}
		public class GivenWidgetHasNoResponse{
			@Before
			public void givenWidgetHasNoResponse(){
				FormWidget.clear();
			}
			@Test(expected = IllegalStateException.class)
			public void whenRemoveResponseRuns_ThenItThrowsAnException(){
				removeResponse.execute();
			}		
		}
		public class GivenWidgetHasOneResponse{
			Answer mockResponse;
			@Before
			public void givenWidgetHasAResponse() throws Exception {
				// Line 39 brings more support for mocking FormWidget.
				FormWidget.addPrompt(TestUtil.makeMockNameQuestion());
				mockResponse = TestUtil.makeMockResponse(0, "Response", true);
				FormWidget.addResponse(mockResponse);
			}
			@Test
			public void whenRemoveResponseRuns_ThenWidgetHasNoResponses() {
				removeResponse = new RemoveResponse();				
				removeResponse.execute();				
				assertFalse(FormWidget.hasResponse());
			}
		}
	}
}
