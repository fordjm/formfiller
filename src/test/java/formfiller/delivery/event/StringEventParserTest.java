package formfiller.delivery.event;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.delivery.EventParser;

// Adapted from:
// https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/test/cleancoderscom/http/RequestParserTest.java
// Retrieved 2015-08-14

public class StringEventParserTest {
	EventParser parser;
	ParsedEvent parsedEvent;

	private void setParsedEvent(String input) {
		parsedEvent = parser.parse(input);
	}

	@Before
	public void setUp(){
		parser = new StringEventParser();
	}
	
	@Test
	public void canParseEmptyString(){
		setParsedEvent("");
		assertEquals("", parsedEvent.method);
		assertEquals("", parsedEvent.param);
	}
	
	@Test
	public void canParseNull(){
		setParsedEvent(null);
		assertEquals("", parsedEvent.method);
		assertEquals("", parsedEvent.param);
	}
	
	@Test
	public void canParseUnknownCommand(){
		setParsedEvent("unknown");
		assertEquals("unknown", parsedEvent.method);
		assertEquals("", parsedEvent.param);
	}
	
	@Test
	public void canParsePresentQuestionCommand() {		
		setParsedEvent("presentQuestion");
		assertEquals("presentQuestion", parsedEvent.method);
		assertEquals("", parsedEvent.param);
	}
	
	@Test
	public void canParsePresentQuestionCommandWithExtraSpaces() {		
		setParsedEvent("  presentQuestion   ");
		assertEquals("presentQuestion", parsedEvent.method);
		assertEquals("", parsedEvent.param);
	}
	
	@Test
	public void canParsePresentQuestionCommandWithOneExtraArgument() {		
		setParsedEvent("presentQuestion -1");
		assertEquals("presentQuestion", parsedEvent.method);
		assertEquals("-1", parsedEvent.param);
	}
	
	@Test
	public void canParsePresentQuestionCommandWithTwoExtraArguments() {		
		setParsedEvent("presentQuestion -1 0");
		assertEquals("presentQuestion", parsedEvent.method);
		assertEquals("-1", parsedEvent.param);
	}
	
	@Test
	public void canParseNavigationCommand() {
		setParsedEvent("navigation -1");
		assertEquals("navigation", parsedEvent.method);
		assertEquals("-1", parsedEvent.param);
	}
	
	@Test
	public void canParseNavigationCommandWithExtraSpaces() {
		setParsedEvent("    navigation  -1  ");
		assertEquals("navigation", parsedEvent.method);
		assertEquals("-1", parsedEvent.param);
	}
}
