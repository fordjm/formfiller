package formfiller.entities;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class NullPromptTest<T> {

	private Prompt<T> n;

	private SelectionFormat<T> createSelectionFormatWithNoSelections() {
		return new SelectionFormat<T>(new ArrayList<T>());
	}

	private void assertNullPromptHasFreeEntryFormat() {
		assertTrue(n.format() instanceof FreeEntryFormat);
	}
	
	@Before
	public void setup(){
		n = new NullPrompt();
	}

	@Test
	public void contentReturnsEmptyString() {
		assertEquals("", n.content());
	}
	
	@Test
	public void beforeSetFormat_formatIsFreeEntryFormat(){
		assertNullPromptHasFreeEntryFormat();
	}
	
	@Test
	public void afterSetFormat_formatIsFreeEntryFormat(){
		n.setFormat(createSelectionFormatWithNoSelections());
		assertNullPromptHasFreeEntryFormat();
	}
	
	@Test
	public void idReturnsEmptyString() {
		assertEquals("", n.id());
	}
}
