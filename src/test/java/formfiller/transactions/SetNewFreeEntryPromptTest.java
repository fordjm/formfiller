package formfiller.transactions;

import static org.junit.Assert.*;

import org.junit.Test;

import formfiller.entities.FreeEntryFormat;
import formfiller.entities.IPrompt;
import formfiller.entities.ResponseConstraint;
import formfiller.persistence.PromptWidget;

public class SetNewFreeEntryPromptTest {

	@Test
	public void canSetNewFreeEntryPrompt() {
		Transaction t = new SetNewFreeEntryPrompt("name", "Name");
		
		t.execute();
		IPrompt p = PromptWidget.getPrompt();		
		ResponseConstraint r = p.format();
		
		assertEquals("Name", p.content());
		assertTrue(r instanceof FreeEntryFormat);
	}
}
