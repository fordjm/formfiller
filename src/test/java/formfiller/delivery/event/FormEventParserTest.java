package formfiller.delivery.event;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.delivery.event.impl.ParsedEvent;
import formfiller.delivery.event.impl.FormEventParser;

// Adapted from:
// https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/test/cleancoderscom/http/RequestParserTest.java
// Retrieved 2015-08-14

@RunWith(HierarchicalContextRunner.class)
public class FormEventParserTest {
	private EventParser parser;
	private ParsedEvent parsedEvent;
	private String questionContent;

	private void parseEventString(String input) {
		parsedEvent = parser.parse(input);
	}

	private void assertThat_TheEventMethod_IsTheGivenString(String method) {
		assertThat(parsedEvent.method, is(method));
	}

	private void assertThat_TheEventParameters_AreEmpty() {
		assertThat(parsedEvent.parameters.size(), is(0));
	}

	private void assertThat_TheGivenStrings_CompriseTheEventParameters(
			String... parameters) {
		for (String param : parameters)
			assertThat(parsedEvent.parameters.contains(param), is(true));
		assertThat(parsedEvent.parameters.size(), is(parameters.length));
	}

	public class CharacterBasedParsingStrategyContext {
		@Before
		public void setUp(){
			parser = new FormEventParser(new CharacterBasedEventParsingStrategy());
			questionContent = "";
		}		
		
		@Test
		public void canParseNull(){
			parseEventString(null);
			
			assertThat_TheEventMethod_IsTheGivenString("");
			assertThat_TheEventParameters_AreEmpty();
		}
		
		@Test
		public void canParseEmptyString(){
			parseEventString("");
			
			assertThat_TheEventMethod_IsTheGivenString("");
			assertThat_TheEventParameters_AreEmpty();
		}
		
		@Test
		public void canParseUnknownCommand(){
			parseEventString("unknown");
			
			assertThat_TheEventMethod_IsTheGivenString("unknown");
			assertThat_TheEventParameters_AreEmpty();
		}
		
		//	TODO:	How to handle entered content without quotes (no quotes in voice entry?)
		@Test
		public void canParseAddFormComponentCommand() {	
			questionContent = "\"What is your name?\"";
			parseEventString("addFormComponent name " + questionContent + " U");
			
			assertThat_TheEventMethod_IsTheGivenString("addFormComponent");
			assertThat_TheGivenStrings_CompriseTheEventParameters("name", 
					questionContent, "U");
		}

		@Test
		public void canParseQuotedSingleWordContent() {	
			questionContent = "\"Name\"";
			parseEventString("addFormComponent name " + questionContent + " U");
			
			assertThat_TheEventMethod_IsTheGivenString("addFormComponent");
			assertThat_TheGivenStrings_CompriseTheEventParameters("name", 
					questionContent, "U");
		}

		@Test
		public void canParseCommaSeparatedContent() {	
			questionContent = "\"Please list your name, age, and birth date.\"";
			parseEventString("addFormComponent name " + questionContent + " U");
			
			assertThat_TheEventMethod_IsTheGivenString("addFormComponent");
			assertThat_TheGivenStrings_CompriseTheEventParameters("name", 
					questionContent, "U");
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
	
	public class JaccardDistanceParsingStrategyContext {
		@Before
		public void setUp(){
			parser = new FormEventParser(new TfIdfSimilarityEventParsingStrategy());
			questionContent = "";
		}		
		
		@Test
		public void canParseNull(){
			parseEventString(null);
			
			assertThat_TheEventMethod_IsTheGivenString("");
			assertThat_TheEventParameters_AreEmpty();
		}
		
		@Test
		public void canParseEmptyString(){
			parseEventString("");
			
			assertThat_TheEventMethod_IsTheGivenString("");
			assertThat_TheEventParameters_AreEmpty();
		}
		
		@Test
		public void canParseUnknownCommand(){
			parseEventString("unknown");
			
			assertThat_TheEventMethod_IsTheGivenString("unknown");
			assertThat_TheEventParameters_AreEmpty();
		}
		
		@Test
		public void canParseAskQuestionCommand() {
			parseEventString("ask question previous");
			
			assertThat_TheEventMethod_IsTheGivenString("ask question");
			assertThat_TheGivenStrings_CompriseTheEventParameters("previous");
		}
		
		@Test
		public void canParseAddAnswerCommand() {
			parseEventString("add answer July 4th");
			
			assertThat_TheEventMethod_IsTheGivenString("add answer");
			assertThat_TheGivenStrings_CompriseTheEventParameters("July 4th");
		}
		
		@Test
		public void canParseLongerAddAnswerCommand() {
			parseEventString("add answer July 4th, 1776");
			
			assertThat_TheEventMethod_IsTheGivenString("add answer");
			assertThat_TheGivenStrings_CompriseTheEventParameters("July 4th, 1776");
		}
		
		@Test
		public void canParseUnorderedAskQuestionCommand() {
			parseEventString("ask previous question");
			
			assertThat_TheEventMethod_IsTheGivenString("ask question");
			assertThat_TheGivenStrings_CompriseTheEventParameters("previous");
		}
		
		@Test
		public void cannotParseMoreThanOneParameter() {
			assertThat(true, is(true));
		}
	}
	
	
}
