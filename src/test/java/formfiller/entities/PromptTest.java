package formfiller.entities;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PromptTest {

	private IPrompt p;
	private ResponseConstraint mockedFormat;
	private ResponseConstraint formatOfP;

	private IPrompt createPrompt(String id, String content) {
		return new Prompt(id, content);
	}
	
	private IPrompt createNamePrompt() {
		return createPrompt("name", "Joe");
	}
	
	private void setupMockedFormat(ResponseConstraint r){
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
		assertEquals("Joe", p.content());
	}
	
	@Test
	public void givenFreeEntryFormat_formatIsFreeEntry(){
		setupMockedFormat(new FreeEntryFormat());
		assertTrue(formatOfP instanceof FreeEntryFormat);
	}
	
	@Test
	public void givenSelectionFormat_formatIsSelection(){
		setupMockedFormat(new SelectionFormat(new ArrayList<String>()));
		assertTrue(formatOfP instanceof SelectionFormat);
	}
}
