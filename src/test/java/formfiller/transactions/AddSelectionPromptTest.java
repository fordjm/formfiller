package formfiller.transactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import formfiller.entities.PromptFunctions;
import formfiller.entities.ResponseConstraint;
import formfiller.entities.SelectionFormat;
import formfiller.persistence.PromptWidget;

public class AddSelectionPromptTest {
	
	@Test
	public void canSetNewSelectionPrompt(){
		List<String> names = Arrays.asList(new String[]{"Bob", "Jim", "Mike"});
		Transaction t = new AddSelectionPrompt("name", "Name", names);
		
		t.execute();		
		PromptFunctions p = PromptWidget.getPrompt();		
		ResponseConstraint r = p.format();		
		SelectionFormat s = (SelectionFormat) r;

		assertEquals("Name", p.content());
		assertTrue(r instanceof SelectionFormat);
		assertEquals(names, s.selections());
	}
}
