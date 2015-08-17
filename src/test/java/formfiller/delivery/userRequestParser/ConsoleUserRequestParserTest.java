package formfiller.delivery.userRequestParser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

// Adapted from:
// https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/test/cleancoderscom/http/RequestParserTest.java
// Retrieved 2015-08-14
public class ConsoleUserRequestParserTest {
	UserRequestParser parser;
	ParsedUserRequest parsedUserRequest;

	private void setParsedRequest(String input) {
		parsedUserRequest = parser.parse(input);
	}

	@Before
	public void setUp(){
		parser = new ConsoleUserRequestParser();
	}
	@Test
	public void canParseEmptyString(){
		setParsedRequest("");
		assertEquals("", parsedUserRequest.getMethod());
		assertEquals("", parsedUserRequest.getParam());
	}
	@Test
	public void canParseNull(){
		setParsedRequest(null);
		assertEquals("", parsedUserRequest.getMethod());
		assertEquals("", parsedUserRequest.getParam());
	}
	@Test
	public void canParseUnknownCommand(){
		setParsedRequest("unknown");
		assertEquals("unknown", parsedUserRequest.getMethod());
		assertEquals("", parsedUserRequest.getParam());
	}
	@Test
	public void canParsePresentQuestionCommand() {		
		setParsedRequest("presentQuestion");
		assertEquals("presentQuestion", parsedUserRequest.getMethod());
		assertEquals("", parsedUserRequest.getParam());
	}
	@Test
	public void canParsePresentQuestionCommandWithExtraSpaces() {		
		setParsedRequest("  presentQuestion   ");
		assertEquals("presentQuestion", parsedUserRequest.getMethod());
		assertEquals("", parsedUserRequest.getParam());
	}
	@Test
	public void canParsePresentQuestionCommandWithOneExtraArgument() {		
		setParsedRequest("presentQuestion -1");
		assertEquals("presentQuestion", parsedUserRequest.getMethod());
		assertEquals("-1", parsedUserRequest.getParam());
	}
	@Test
	public void canParsePresentQuestionCommandWithTwoExtraArguments() {		
		setParsedRequest("presentQuestion -1 0");
		assertEquals("presentQuestion", parsedUserRequest.getMethod());
		assertEquals("-1", parsedUserRequest.getParam());
	}
	@Test
	public void canParseNavigationCommand() {
		setParsedRequest("navigation -1");
		assertEquals("navigation", parsedUserRequest.getMethod());
		assertEquals("-1", parsedUserRequest.getParam());
	}
	@Test
	public void canParseNavigationCommandWithExtraSpaces() {
		setParsedRequest("    navigation  -1  ");
		assertEquals("navigation", parsedUserRequest.getMethod());
		assertEquals("-1", parsedUserRequest.getParam());
	}

}
