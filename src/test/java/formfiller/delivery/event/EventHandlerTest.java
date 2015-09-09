package formfiller.delivery.event;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.FormFillerContext;
import formfiller.delivery.EventSource;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.utilities.TestSetup;

public class EventHandlerTest {	
	private EventHandler eventHandler;
	private EventSource mockEventSource;
	
	private FormComponent getCurrent() {
		return FormFillerContext.formComponentState.getCurrent();
	}

	private FormComponent getEndFormComponent() {
		return NullFormComponents.END;
	}

	@Before
	public void setUp() {
		TestSetup.setupContext();
		eventHandler = new EventHandler();
		mockEventSource = Mockito.mock(EventSource.class);
	}
	
	@Test
	public void getCurrentIsFormComponentDotEnd() {
		assertThat(getCurrent(), is(getEndFormComponent()));
	}

	@Test
	public void canHandleNull() {		
		eventHandler.update(mockEventSource, null);
		
		assertThat(getCurrent(), is(getEndFormComponent()));
	}

	@Test
	public void canHandleEmptyString() {
		eventHandler.update(mockEventSource, "");
		
		assertThat(getCurrent(), is(getEndFormComponent()));
	}
}
