package formfiller.delivery.event;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import formfiller.delivery.EventParser;

// Adapted from:
// https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/test/cleancoderscom/http/RequestParserTest.java
// Retrieved 2015-08-14

public class StringEventParserTest {
	EventParser parser;
	ParsedEvent parsedEvent;

	@Before
	public void setUp(){
		parser = new StringEventParser();
	}

	private void parseEventString(String input) {
		parsedEvent = parser.parse(input);
	}

	private void assertThat_TheEventMethod_IsTheGivenString(String method) {
		assertThat(parsedEvent.method, is(method));
	}

	private void assertThat_TheEventParameters_AreEmpty() {
		assertThat(parsedEvent.parameters, instanceOf(Collection.class));
		assertThat(parsedEvent.parameters.size(), is(0));
	}

	private void assertThat_TheGivenStrings_CompriseTheEventParameters(
			String... parameters) {
		for (String param : parameters)
			assertThat(parsedEvent.parameters.contains(param), is(true));
		assertThat(parsedEvent.parameters.size(), is(parameters.length));
	}
	
	@Test
	public void canParseEmptyString(){
		parseEventString("");
		
		assertThat_TheEventMethod_IsTheGivenString("");
		assertThat_TheEventParameters_AreEmpty();
	}
	
	@Test
	public void canParseNull(){
		parseEventString(null);
		
		assertThat_TheEventMethod_IsTheGivenString("");
		assertThat_TheEventParameters_AreEmpty();
	}
	
	@Test
	public void canParseUnknownCommand(){
		parseEventString("unknown");
		
		assertThat_TheEventMethod_IsTheGivenString("unknown");
		assertThat_TheEventParameters_AreEmpty();
	}
	
	//	TODO:	How to handle entered content without quotes?
	@Test
	public void canParseAddFormComponentCommand() {		
		parseEventString("addFormComponent name \"What is your name?\" U");
		
		assertThat_TheEventMethod_IsTheGivenString("addFormComponent");
		assertThat_TheGivenStrings_CompriseTheEventParameters("name", 
				"\"What is your name?\"", "U");
	}
	
	@Test
	public void canParseAddFormComponentCommandWithExtraSpaces() {		
		parseEventString("  addFormComponent   ");
		assertEquals("addFormComponent", parsedEvent.method);
		assertThat_TheEventParameters_AreEmpty();
	}
	
	@Test
	public void canParseAskQuestionCommand() {
		parseEventString("askQuestion previous");
		
		assertThat_TheEventMethod_IsTheGivenString("askQuestion");
		assertThat_TheGivenStrings_CompriseTheEventParameters("previous");
	}
	
	@Test
	public void canParseAskQuestionCommandWithExtraSpaces() {
		parseEventString("    askQuestion  next  ");
		
		assertThat_TheEventMethod_IsTheGivenString("askQuestion");
		assertThat_TheGivenStrings_CompriseTheEventParameters("next");
	}
}
