package formfiller.ui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.ui.consoleUi.ConsoleUserRequestParser;
import formfiller.ui.consoleUi.ParsedUserRequest;

// Adapted from:
// https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/test/cleancoderscom/http/RequestParserTest.java
// Retrieved 2015-08-14
public class UserRequestParserTest {
	UserRequestParser parser;
	ParsedUserRequest parsedUserRequest;

	@Before
	public void setUp(){
		parser = new ConsoleUserRequestParser();
	}
	@Test
	public void canParseEmptyString(){
		parsedUserRequest = parser.parse("");
		assertEquals("", parsedUserRequest.getMethod());
		assertEquals("", parsedUserRequest.getParam());
	}
	@Test
	public void canParseNull(){
		parsedUserRequest = parser.parse(null);
		assertEquals("", parsedUserRequest.getMethod());
		assertEquals("", parsedUserRequest.getParam());
	}
	@Test
	public void canParsePresentQuestionCommand() {		
		parsedUserRequest = parser.parse("presentQuestion");
		assertEquals("presentQuestion", parsedUserRequest.getMethod());
	}
	@Test
	public void canParsePresentQuestionCommandWithExtraSpaces() {		
		parsedUserRequest = parser.parse("  presentQuestion   ");
		assertEquals("presentQuestion", parsedUserRequest.getMethod());
	}
	@Test
	public void canParseNavigationCommand() {
		parsedUserRequest = parser.parse("navigation -1");
		assertEquals("navigation", parsedUserRequest.getMethod());
		assertEquals("-1", parsedUserRequest.getParam());
	}
	@Test
	public void canParseNavigationCommandWithExtraSpaces() {
		parsedUserRequest = parser.parse("    navigation  -1  ");
		assertEquals("navigation", parsedUserRequest.getMethod());
		assertEquals("-1", parsedUserRequest.getParam());
	}

}
