package formfiller.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PromptTest<T> {

	private Prompt<T> p;
	private ResponseFormat<T> mockedFormat;
	private ResponseFormat<T> formatOfP;

	private Prompt<T> createPrompt(String id, String content) {
		return new PromptImpl<T>(id, content);
	}
	
	private Prompt<T> createNamePrompt() {
		return createPrompt("name", "What is your name?");
	}
	
	private void setupMockedFormat(ResponseFormat<T> r){
		mockedFormat = mock(r.getClass());
		p.setFormat(mockedFormat);
		formatOfP = p.format();
	}
	
	@Before
	public void setup(){
		p = createNamePrompt();
	}
	
	@Test 
	public void givenStringId_idIsNewString(){
		assertEquals("name", p.id());
	}
	
	@Test 
	public void givenStringContent_contentIsNewString(){
		assertEquals("What is your name?", p.content());
	}
	
	@Test
	public void givenFreeEntryFormat_formatIsFreeEntry(){
		setupMockedFormat(new FreeEntryFormat<T>());
		assertTrue(formatOfP instanceof FreeEntryFormat);
	}
	
	@Test
	public void givenSelectionFormat_formatIsSelection(){
		setupMockedFormat(new SelectionFormat<T>(new ArrayList<T>()));
		assertTrue(formatOfP instanceof SelectionFormat);
	}
}
