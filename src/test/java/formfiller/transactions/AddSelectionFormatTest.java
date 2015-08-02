package formfiller.transactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import formfiller.entities.Constrainable;
import formfiller.entities.SelectionFormat;
import formfiller.enums.ContentConstraint;
import formfiller.persistence.FormWidget;

public class AddSelectionFormatTest {
	
	@Test
	public void canAddSelectionFormat(){
		List<String> names = Arrays.asList("Bob", "Jim", "Mike");
		Transaction t = new AddSelectionFormat<String>(names);
		
		t.execute();		
		Map<ContentConstraint, Constrainable<?>> p = FormWidget.getConstraints();		
		Constrainable<?> r = p.get(ContentConstraint.FORMAT_SELECTION);
		SelectionFormat<String> s = (SelectionFormat<String>) r;

		assertTrue(r instanceof SelectionFormat);
		assertEquals(names, s.getSelections());
	}
}
