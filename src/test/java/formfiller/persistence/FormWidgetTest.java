package formfiller.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.NullPrompt;
import formfiller.entities.Prompt;
import formfiller.entities.PromptImpl;
import formfiller.entities.Response;
import formfiller.entities.ResponseImpl;

public class FormWidgetTest {

	private Prompt p;

	private void assertPromptIsNullPrompt() {
		assertTrue(FormWidget.getPrompt() instanceof NullPrompt);
	}

	private void assertResponseContentIsEmptyString() {
		assertEquals("", FormWidget.getResponse().content());
	}

	private void setFormWidgetPrompt(Prompt prompt) {
		FormWidget.setPrompt(p);
	}

	private void setPromptFormWidgetResponse(Response response) {
		if (response == null)
			FormWidget.setResponse(new ResponseImpl(-1, ""));
		else
			FormWidget.setResponse(response);
	}

	private PromptImpl createNamePrompt() {
		return new PromptImpl("name", "Name");
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
	public void onStart_getResponseReturnsEmptyString(){
		assertResponseContentIsEmptyString();
	}
	
	@Test
	public void givenNull_setPromptSetsNullPrompt(){
		setFormWidgetPrompt(null);
		assertPromptIsNullPrompt();
	}
	
	@Test
	public void givenNull_setResponseSetsEmptyString(){
		setPromptFormWidgetResponse(null);
		assertResponseContentIsEmptyString();
	}
	
	@Test
	public void givenString_setResponseSetsNewString(){
		setPromptFormWidgetResponse(new ResponseImpl(0, "Joe"));
		assertEquals("Joe", FormWidget.getResponse().content());
	}
	
	@Test
	public void givenPrompt_SetPromptSetsNewPrompt(){
		p = createNamePrompt();
		setFormWidgetPrompt(p);
		assertEquals(p, FormWidget.getPrompt());
	}
	
	@Test
	public void afterClear_fieldsHaveDefaultValues(){
		setFormWidgetPrompt(createNamePrompt());
		FormWidget.clear();
		assertPromptIsNullPrompt();
		assertResponseContentIsEmptyString();
	}
	
	@Test
	public void atStart_constraintListIsNull(){
		assertTrue(FormWidget.constraints().size() == 0);
	}
}
