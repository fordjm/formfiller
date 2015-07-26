package formfiller.transactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.FreeEntryFormat;
import formfiller.entities.Prompt;
import formfiller.entities.ResponseFormat;
import formfiller.persistence.FormWidget;

public class AddFreeEntryPromptTest<T> {
	
	@Test
	public void canAddStringFreeEntryPrompt() {
		Transaction t = new AddFreeEntryPrompt<String>("name", "Name");
		
		t.execute();
		Prompt<?> p = FormWidget.getPrompt();		
		ResponseFormat<?> r = p.format();
		
		assertEquals("Name", p.content());
		assertTrue(r instanceof FreeEntryFormat);
	}
}
