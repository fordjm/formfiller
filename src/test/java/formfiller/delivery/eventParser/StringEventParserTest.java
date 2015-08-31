package formfiller.delivery.eventParser;

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

	private void setParsedRequest(String input) {
		parsedEvent = parser.parse(input);
	}

	@Before
	public void setUp(){
		parser = new StringEventParser();
	}
	
	@Test
	public void canParseEmptyString(){
		setParsedRequest("");
		assertEquals("", parsedEvent.method);
		assertEquals("", parsedEvent.param);
	}
	
	@Test
	public void canParseNull(){
		setParsedRequest(null);
		assertEquals("", parsedEvent.method);
		assertEquals("", parsedEvent.param);
	}
	
	@Test
	public void canParseUnknownCommand(){
		setParsedRequest("unknown");
		assertEquals("unknown", parsedEvent.method);
		assertEquals("", parsedEvent.param);
	}
	
	@Test
	public void canParsePresentQuestionCommand() {		
		setParsedRequest("presentQuestion");
		assertEquals("presentQuestion", parsedEvent.method);
		assertEquals("", parsedEvent.param);
	}
	
	@Test
	public void canParsePresentQuestionCommandWithExtraSpaces() {		
		setParsedRequest("  presentQuestion   ");
		assertEquals("presentQuestion", parsedEvent.method);
		assertEquals("", parsedEvent.param);
	}
	
	@Test
	public void canParsePresentQuestionCommandWithOneExtraArgument() {		
		setParsedRequest("presentQuestion -1");
		assertEquals("presentQuestion", parsedEvent.method);
		assertEquals("-1", parsedEvent.param);
	}
	
	@Test
	public void canParsePresentQuestionCommandWithTwoExtraArguments() {		
		setParsedRequest("presentQuestion -1 0");
		assertEquals("presentQuestion", parsedEvent.method);
		assertEquals("-1", parsedEvent.param);
	}
	
	@Test
	public void canParseNavigationCommand() {
		setParsedRequest("navigation -1");
		assertEquals("navigation", parsedEvent.method);
		assertEquals("-1", parsedEvent.param);
	}
	
	@Test
	public void canParseNavigationCommandWithExtraSpaces() {
		setParsedRequest("    navigation  -1  ");
		assertEquals("navigation", parsedEvent.method);
		assertEquals("-1", parsedEvent.param);
	}
}
