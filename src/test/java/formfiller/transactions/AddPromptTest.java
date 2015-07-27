package formfiller.transactions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import formfiller.entities.Prompt;
import formfiller.persistence.FormWidget;

public class AddPromptTest {
	
	@Test
	public void canAddStringPrompt() {
		Transaction t = new AddPrompt("name", "Name");
		
		t.execute();
		Prompt p = FormWidget.getPrompt();
		
		assertEquals("Name", p.content());
	}
}
