package formfiller.transactions;

import static org.junit.Assert.*;

import org.junit.Test;

import formfiller.entities.FreeEntryFormat;
import formfiller.entities.PromptFunctions;
import formfiller.entities.ResponseConstraint;
import formfiller.persistence.PromptWidget;

public class AddFreeEntryPromptTest {

	@Test
	public void canSetNewFreeEntryPrompt() {
		Transaction t = new AddFreeEntryPrompt("name", "Name");
		
		t.execute();
		PromptFunctions p = PromptWidget.getPrompt();		
		ResponseConstraint r = p.format();
		
		assertEquals("Name", p.content());
		assertTrue(r instanceof FreeEntryFormat);
	}
}
