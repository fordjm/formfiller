package formfiller.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.Constrainable;
import formfiller.entities.NullPrompt;
import formfiller.entities.NullResponse;
import formfiller.entities.Prompt;
import formfiller.entities.Response;
import formfiller.enums.ConstraintName;
import formfiller.utilities.TestUtil;

public class FormWidgetTest {

	static void assertPromptIsNullPrompt() {
		assertTrue(FormWidget.getPrompt() instanceof NullPrompt);
		assertEquals("", FormWidget.getPrompt().getId());
		assertEquals("", FormWidget.getPrompt().getContent());
	}

	static void assertResponseIsNullResponse() {
		assertTrue(FormWidget.getResponse() instanceof NullResponse);
		assertEquals(-1, FormWidget.getResponse().getId());
		assertEquals("", FormWidget.getResponse().getContent());
	}
	
	static void assertWidgetHasNoConstraints(){
		Collection<Constrainable<?>> constraintValues = getConstraintValues();
		assertTrue(constraintValues.size() == 0);
	}
	
	static Collection<Constrainable<?>> getConstraintValues(){
		Map<ConstraintName, Constrainable<?>> constraintsMap = getConstraintsMap();
		return constraintsMap.values();
	}
	
	static Map<ConstraintName, Constrainable<?>> getConstraintsMap(){
		return FormWidget.getConstraints();
	}
	
	static Prompt makeMockPrompt(String id, String content){
		Prompt result = mock(Prompt.class);
		when (result.getId()).thenReturn(id);
		when (result.getContent()).thenReturn(content);
		return result;
	}

	static void assertResponseDidNotChange(Response<?> initialResponse, Response<?> newResponse,
			Response<?> finalResponse) {
		assertTrue(finalResponse instanceof NullResponse);
		assertNotSame(initialResponse, newResponse);
		assertSame(initialResponse, finalResponse);
	}
	
	public static class GivenAClearedWidget{
		Prompt mockPrompt;
		Response<?> mockResponse;
		
		@Before
		public void givenWidgetInStartState(){
			FormWidget.clear();
		}
	}
	
	public static class GivenFieldsHaveDefaultValues extends GivenAClearedWidget{
		
		@Test
		public void whenGetPromptRuns_ThenItReturnsNullPrompt(){
			assertPromptIsNullPrompt();
		}
		
		@Test
		public void whenGetResponseRuns_ThenItReturnsNullResponse(){
			assertResponseIsNullResponse();
		}	
		
		@Test
		public void whenConstraintValuesSizeChecked_ThenItReturnsZero(){
			assertWidgetHasNoConstraints();
		}		
	}
	
	public static class GivenAnInvalidPrompt extends GivenAClearedWidget{
		
		@Before
		public void givenAnInvalidPrompt(){
			mockPrompt = null;
		}
		
		@Test
		public void whenSetPromptRuns_ThenWidgetSetsNullPrompt(){
			Prompt initialPrompt = FormWidget.getPrompt();
			FormWidget.addPrompt(mockPrompt);
			Prompt currentPrompt = FormWidget.getPrompt();
			assertNotNull(currentPrompt);
			assertSame(initialPrompt, currentPrompt);
		}
	}
	
	public static class GivenAValidPrompt extends GivenAClearedWidget{
		
		@Before
		public void givenAValidPrompt(){
			mockPrompt = makeMockPrompt("name", "What is your name?");
		}
		
		@Test
		public void whenSetPromptRuns_ThenWidgetSetsNewPrompt(){
			FormWidget.addPrompt(mockPrompt);
			Prompt currentPrompt = FormWidget.getPrompt();
			assertFalse(currentPrompt instanceof NullPrompt);
			assertSame(mockPrompt, currentPrompt);
		}
	}
	
	public static class GivenAnInvalidResponse extends GivenAClearedWidget{
		@Before
		public void givenAnInvalidResponse(){
			mockResponse = new NullResponse();
		}
		
		@Test
		public void whenSetResponseRuns_ThenResponseDoesNotChange(){
			Response<?> initialResponse = FormWidget.getResponse();
			FormWidget.addResponse(mockResponse);
			Response<?> finalResponse = FormWidget.getResponse();
			
			assertResponseDidNotChange(initialResponse, mockResponse, finalResponse);			
		}
	}
	
	public static class GivenAValidResponse extends GivenAClearedWidget{
		
		@Before
		public <T> void givenAValidResponse(){
			mockResponse = TestUtil.makeMockResponse(0, (T) "Joe", true);
		}
		
		// Cases make no difference.  No prompt means no response.
		@Test
		public void whenSetResponseRuns_ThenWidgetSetsNewResponse(){
			Response<?> initialResponse = FormWidget.getResponse();
			FormWidget.addResponse(mockResponse);
			Response<?> finalResponse = FormWidget.getResponse();
			
			assertResponseDidNotChange(initialResponse, mockResponse, finalResponse);
		}	
	}
	
	public static class GivenValidPromptAdded extends GivenAClearedWidget{
		
		@Before
		public void givenValidPromptSet(){
			mockPrompt = makeMockPrompt("name", "What is your name?");
			FormWidget.addPrompt(mockPrompt);
		}
	}
	
	public static class GivenCannotAddResponse extends GivenValidPromptAdded{
		@Before
		public <T> void givenCannotAddResponse(){
			mockResponse = null;
		}
		
		@Test
		public void whenSetResponseRuns_ThenResponseDoesNotChange(){
			Response<?> initialResponse = FormWidget.getResponse();
			FormWidget.addResponse(mockResponse);
			Response<?> finalResponse = FormWidget.getResponse();
			
			assertResponseDidNotChange(initialResponse, mockResponse, finalResponse);			
		}
	}
	
	public static class GivenCanAddResponse extends GivenValidPromptAdded{
		
		@Before
		public <T> void givenCanSetResponse(){
			mockResponse = TestUtil.makeMockResponse(0, (T) "Joe", true);
		}
		
		// TODO:  Handle different response cases.
		@Test
		public void whenSetResponseRuns_ThenWidgetSetsNewResponse(){
			Response<?> initialResponse = FormWidget.getResponse();
			FormWidget.addResponse(mockResponse);
			Response<?> finalResponse = FormWidget.getResponse();
			
			assertEquals(mockResponse, finalResponse);
		}
	}
}
