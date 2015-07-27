package formfiller.transactions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import formfiller.entities.Prompt;
import formfiller.entities.PromptImpl;
import formfiller.persistence.FormWidget;

public class PromptSubjectTest {

	@Test
	public void canCreatePromptSubjectTransaction() {
		Transaction t = new PromptSubject("name", "Name");
		assertTrue(t instanceof PromptSubject);
	}
	
	@Test
	public void newTest(){
		Transaction t = new PromptSubject("name", "Name");
		t.execute();
		Prompt p = FormWidget.getPrompt();
		assertTrue(p instanceof PromptImpl);
	}
}
