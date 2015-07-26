package formfiller.transactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import formfiller.entities.Prompt;
import formfiller.entities.ResponseFormat;
import formfiller.entities.SelectionFormat;
import formfiller.persistence.FormWidget;

public class AddSelectionPromptTest {
	
	@Test
	public void canSetNewSelectionPrompt(){
		List<String> names = Arrays.asList(new String[]{"Bob", "Jim", "Mike"});
		Transaction t = new AddSelectionPrompt<String>("name", "Name", names);
		
		t.execute();		
		Prompt p = FormWidget.getPrompt();		
		ResponseFormat<String> r = p.format();	
		SelectionFormat<String> s = (SelectionFormat<String>) r;

		assertEquals("Name", p.content());
		assertTrue(r instanceof SelectionFormat);
		assertEquals(names, s.selections());
	}
}
