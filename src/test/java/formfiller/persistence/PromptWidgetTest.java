package formfiller.persistence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.IPrompt;
import formfiller.entities.NullPrompt;
import formfiller.entities.Prompt;

public class PromptWidgetTest {

	private IPrompt p;

	private void assertPromptIsNullPrompt() {
		assertTrue(PromptWidget.getPrompt() instanceof NullPrompt);
	}

	private void assertResponseIsEmptyString() {
		assertEquals("", PromptWidget.getResponse());
	}

	private void setWidgetPrompt(IPrompt prompt) {
		PromptWidget.setPrompt(p);
	}

	private void setPromptWidgetResponse(String response) {
		PromptWidget.setResponse(response);
	}

	private Prompt createNamePrompt() {
		return new Prompt("name", "Name");
	}
	
	@Before
	public void setup(){
		PromptWidget.clear();
	}
	
	@Test
	public void onStart_getPromptReturnsNullPrompt(){
		assertPromptIsNullPrompt();
	}
	
	@Test
	public void onStart_getResponseReturnsEmptyString(){
		assertResponseIsEmptyString();
	}
	
	@Test
	public void givenNull_setPromptSetsNullPrompt(){
		setWidgetPrompt(null);
		assertPromptIsNullPrompt();
	}
	
	@Test
	public void givenNull_setResponseSetsEmptyString(){
		setPromptWidgetResponse(null);
		assertResponseIsEmptyString();
	}
	
	@Test
	public void givenString_setResponseSetsNewString(){
		setPromptWidgetResponse("Joe");
		assertEquals("Joe", PromptWidget.getResponse());
	}
	
	@Test
	public void givenPrompt_SetPromptSetsNewPrompt(){
		p = createNamePrompt();
		setWidgetPrompt(p);
		assertEquals(p, PromptWidget.getPrompt());
	}
	
	@Test
	public void afterClear_fieldsHaveDefaultValues(){
		setWidgetPrompt(createNamePrompt());
		PromptWidget.clear();
		assertPromptIsNullPrompt();
		assertResponseIsEmptyString();
	}
}
