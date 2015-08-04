package formfiller.transactions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.Prompt;
import formfiller.persistence.FormWidget;
import formfiller.utilities.TestUtil;

@RunWith(HierarchicalContextRunner.class)
public class AddResponseTest<T> {
	static Transaction addResponse;
	public class WidgetHasNoPromptContext{
		@Before
		public void givenWidgetHasNoPrompt(){
			FormWidget.clear();
		}
		public class GivenAnInvalidResponse{
			@Before
			public void givenAnInvalidResponse(){
				addResponse = new AddResponse<T>(null);
			}
			@Test(expected = IllegalStateException.class)
			public void whenAddResponseRuns_ThenItThrowsAnException(){
				addResponse.execute();
			}
		}
		public class GivenAValidResponse{
			T validContent = (T) "Joe";
			@Before
			public void givenAValidResponse(){
				addResponse = new AddResponse<T>(validContent);
			}
			@Test(expected = IllegalStateException.class)
			public void whenAddResponseRuns_ThenItThrowsAnException(){
				addResponse.execute();
				assertNotSame(validContent, FormWidget.getResponse().getContent());
			}	
		}
	}
	public class WidgetHasAPromptContext{
		@Before
		public void givenWidgetHasAPrompt(){
			FormWidget.clear();
			Prompt prompt = TestUtil.makeMockNamePrompt();
			FormWidget.addPrompt(prompt);
		}
		public class GivenAnInvalidResponse{
			@Before
			public void givenAnInvalidResponse(){
				addResponse = new AddResponse<T>(null);
			}
			@Test(expected = IllegalArgumentException.class)
			public void whenAddResponseRuns_ThenItThrowsAnException(){
				addResponse.execute();
			}
		}
		public class GivenAValidResponse{
			T validContent = (T) "Joe";
			@Before
			public void givenAValidResponse(){
				addResponse = new AddResponse<T>(validContent);
			}
			@Test
			public void whenAddResponseRuns_ThenItAddsANewResponse(){
				addResponse.execute();
				assertSame(validContent, FormWidget.getResponse().getContent());
			}	
		}	
	}
}