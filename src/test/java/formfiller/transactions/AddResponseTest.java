package formfiller.transactions;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import formfiller.entities.Prompt;
import formfiller.entities.PromptImpl;
import formfiller.entities.Response;
import formfiller.persistence.FormWidget;

public class AddResponseTest {
	
	public static class GivenWidgetHasAPrompt{
		
		@Before
		public void givenWidgetHasAPrompt(){
			FormWidget.clear();
			Prompt p = new PromptImpl("name", "What is your name?");
			FormWidget.addPrompt(p);
		}

		@Ignore
		@Test
		public void canAddNewStringResponse() {
			Response<?> response;
			
			Transaction t = new AddResponse<String>("Joe");
			
			t.execute();
			
			response = FormWidget.getResponse();
			assertEquals("Joe", response.getContent());
		}
	}
}