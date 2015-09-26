package formfiller.delivery.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.delivery.Controller;
import formfiller.delivery.event.ParsedEvent;

public class DeleteFormComponentControllerTest {
	private DeleteFormComponentController controller;
	
	@Before
	public void setUp() {
		controller = new DeleteFormComponentController();
	}

	@Test
	public void implementsController() {
		assertThat(controller, instanceOf(Controller.class));
	}

	@Test(expected = NullPointerException.class)
	public void handlingNullThrowsException() {
		controller.handle(null);
	}

	@Test
	public void canHandleNullFields() {
		ParsedEvent mockParsedEvent = Mockito.mock(ParsedEvent.class);
		mockParsedEvent.method = null;
		mockParsedEvent.parameters = null;
		controller.handle(mockParsedEvent);
		
		//	TODO:	Assert component not found?
	}

	@Test
	public void canHandleEmptyParams() {
		ParsedEvent mockParsedEvent = Mockito.mock(ParsedEvent.class);
		mockParsedEvent.method = "DeleteFormComponent";
		mockParsedEvent.parameters = Collections.emptyList();
		controller.handle(mockParsedEvent);
		
		//	TODO:	Assert component not found?
	}

	@Test
	public void test() {
		ParsedEvent mockParsedEvent = Mockito.mock(ParsedEvent.class);
		mockParsedEvent.method = "DeleteFormComponent";
		mockParsedEvent.parameters = new ArrayList<String>(Arrays.asList("unfound"));
		controller.handle(mockParsedEvent);
		
		//	TODO:	Assert component not found?
	}
}
