package formfiller.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.NullPrompt;
import formfiller.entities.NullResponse;
import formfiller.entities.Prompt;
import formfiller.entities.PromptImpl;
import formfiller.entities.Response;

public class FormWidgetTest {

	private void assertPromptIsNullPrompt() {
		assertTrue(FormWidget.getPrompt() instanceof NullPrompt);
		assertEquals("", FormWidget.getPrompt().getId());
		assertEquals("", FormWidget.getPrompt().getContent());
	}

	private void assertPromptIsNamePrompt() {
		assertTrue(FormWidget.getPrompt() instanceof PromptImpl);
		assertEquals("name", FormWidget.getPrompt().getId());
		assertEquals("What is your name?", FormWidget.getPrompt().getContent());
	}

	private void assertResponseIsNullResponse() {
		assertTrue(FormWidget.getResponse() instanceof NullResponse);
		assertEquals(-1, FormWidget.getResponse().getId());
		assertEquals("", FormWidget.getResponse().getContent());
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
	
	@Before
	public void setUp(){
		FormWidget.clear();
	}
	
	@Test
	public void onStart_getPromptReturnsNullPrompt(){
		assertPromptIsNullPrompt();
	}
	
	@Test
	public void onStart_getResponseReturnsNullResponse(){
		assertResponseIsNullResponse();
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
	}
	
	@Test
	public void atStart_constraintSetIsEmpty(){
		assertTrue(FormWidget.constraints().values().size() == 0);
	}
}
