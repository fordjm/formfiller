package formfiller.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.AbstractResponse;
import formfiller.entities.Constrainable;
import formfiller.entities.NullPrompt;
import formfiller.entities.NullResponse;
import formfiller.entities.Prompt;
import formfiller.entities.Response;
import formfiller.utilities.ConstraintName;
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
	
	public static <T> Response<T> makeMockResponse(int id, T content) {
		Response<T> mockResponse = 
				(Response<T>) mock(Response.class);
		when(mockResponse.getId()).thenReturn(id);
		when(mockResponse.getContent()).thenReturn(content);
		return mockResponse;
	}	
	
	public static class GivenWidgetInStartState{
		Prompt mockPrompt;
		Response<?> mockResponse;
		
		@Before
		public void givenWidgetInStartState(){
			FormWidget.clear();
		}		
	}
	
	public static class GivenFieldsHaveDefaultValues extends GivenWidgetInStartState{
		
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
	
	public static class GivenAnInvalidPrompt extends GivenWidgetInStartState{
		
		@Before
		public void givenAnInvalidPrompt(){
			mockPrompt = null;
		}
		
		@Test
		public void whenSetPromptRuns_ThenWidgetSetsNullPrompt(){
			FormWidget.setPrompt(mockPrompt);
			Prompt currentPrompt = FormWidget.getPrompt();
			assertNotNull(currentPrompt);
			assertTrue(currentPrompt instanceof NullPrompt);
		}
	}
	
	public static class GivenAValidPrompt extends GivenWidgetInStartState{
		
		@Before
		public void givenAValidPrompt(){
			mockPrompt = makeMockPrompt("name", "What is your name?");
		}
		
		@Test
		public void whenSetPromptRuns_ThenWidgetSetsNewPrompt(){
			FormWidget.setPrompt(mockPrompt);
			Prompt currentPrompt = FormWidget.getPrompt();
			assertFalse(currentPrompt instanceof NullPrompt);
			assertSame(mockPrompt, currentPrompt);
		}
	}
	
	public static class GivenAnInvalidResponse extends GivenWidgetInStartState{
		@Before
		public void givenAnInvalidResponse(){
			mockResponse = new NullResponse();
		}
		
		@Test
		public void whenSetResponseRuns_ThenResponseDoesNotChange(){
			Response<?> initialResponse = FormWidget.getResponse();
			FormWidget.setResponse(mockResponse);
			Response<?> finalResponse = FormWidget.getResponse();
			
			assertResponseDidNotChange(initialResponse, mockResponse, finalResponse);			
		}
	}
	
	public static class GivenAValidResponse extends GivenWidgetInStartState{
		
		@Before
		public <T> void givenAValidResponse(){
			mockResponse = makeMockResponse(0, (T) "Joe");
		}
		
		// TODO:  Cases:  Single, Multiple (Must implement Cardinality first)
		@Test
		public void whenSetResponseRuns_ThenWidgetSetsNewResponse(){
			Response<?> initialResponse = FormWidget.getResponse();
			FormWidget.setResponse(mockResponse);
			Response<?> finalResponse = FormWidget.getResponse();
			
			assertResponseDidNotChange(initialResponse, mockResponse, finalResponse);
		}	
	}
	
	public static class GivenValidPromptSet extends GivenWidgetInStartState{
		
		@Before
		public void givenValidPromptSet(){
			mockPrompt = makeMockPrompt("name", "What is your name?");
			FormWidget.setPrompt(mockPrompt);
		}
	}
	
	public static class GivenCannotSetResponse extends GivenValidPromptSet{
		@Before
		public <T> void givenCannotSetResponse(){
			mockResponse = null;
		}
		
		@Test
		public void whenSetResponseRuns_ThenResponseDoesNotChange(){
			Response<?> initialResponse = FormWidget.getResponse();
			FormWidget.setResponse(mockResponse);
			Response<?> finalResponse = FormWidget.getResponse();
			
			assertResponseDidNotChange(initialResponse, mockResponse, finalResponse);			
		}
	}
	
	public static class GivenCanSetResponse extends GivenValidPromptSet{
		
		@Before
		public <T> void givenCanSetResponse(){
			mockResponse = makeMockResponse(0, (T) "Joe");
		}
		
		@Test
		public void whenSetResponseRuns_ThenWidgetSetsNewResponse(){
			Response<?> initialResponse = FormWidget.getResponse();
			FormWidget.setResponse(mockResponse);
			Response<?> finalResponse = FormWidget.getResponse();
			
			assertEquals(mockResponse, finalResponse);
		}
	}
	
	// TODO:  Figure out widget's constraint-handling.

	/*private void assertPromptIsNamePrompt() {
		assertTrue(FormWidget.getPrompt() instanceof PromptImpl);
		assertEquals("name", FormWidget.getPrompt().getId());
		assertEquals("What is your name?", FormWidget.getPrompt().getContent());
	}

	private void assertResponseIsNameResponse() {
		Response<?> r = FormWidget.getResponse();
		assertTrue(r instanceof Response);
		assertEquals(0, r.getId());
		assertEquals("Joe", r.getContent());
	}

	private void setFormWidgetPrompt(Prompt prompt) {
		FormWidget.setPrompt(prompt);
	}

	private void setFormWidgetResponse(Response<?> response) {
			FormWidget.setResponse(response);
	}

	private Prompt createMockNamePrompt() {
		return createMockPrompt(PromptImpl.class, "name", "What is your name?");
	}

	private Prompt createMockPrompt(Class<?> classToMock, String id, String content) {
		Prompt mockPrompt = 
				(Prompt) mock(classToMock);
		when(mockPrompt.getId()).thenReturn(id);
		when(mockPrompt.getContent()).thenReturn(content);
		return mockPrompt;
	}
	
	private Response<String> createMockNameResponse(){
		return createMockResponse(Response.class, 0, "Joe");
	}

	private Response<String> createMockResponse(Class<?> classToMock, int id, String content) {
		Response<String> mockResponse = 
				(Response<String>) mock(classToMock);
		when(mockResponse.getId()).thenReturn(id);
		when(mockResponse.getContent()).thenReturn(content);
		return mockResponse;
	}
	
	@Test
	public void givenNull_setPromptSetsNullPrompt(){
		setFormWidgetPrompt(null);
		assertPromptIsNullPrompt();
	}
	
	@Test
	public void givenNull_setResponseSetsNullResponse(){
		setFormWidgetResponse(null);
		assertResponseIsNullResponse();
	}
	
	@Test
	public void givenString_setResponseSetsNewString(){
		setFormWidgetResponse(createMockNameResponse());
		assertResponseIsNameResponse();
	}
	
	@Test
	public void givenPrompt_SetPromptSetsNewPrompt(){
		Prompt prompt = createMockNamePrompt();
		setFormWidgetPrompt(prompt);
		assertPromptIsNamePrompt();
	}
	
	@Test
	public void afterClear_fieldsHaveDefaultValues(){
		setFormWidgetPrompt(createMockNamePrompt());
		FormWidget.clear();
		assertPromptIsNullPrompt();
		assertResponseIsNullResponse();
	}*/
}
