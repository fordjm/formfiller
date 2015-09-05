package formfiller.delivery.event;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.ApplicationContext;
import formfiller.delivery.EventSource;
import formfiller.entities.FormComponent;
import formfiller.gateways.Transporter;
import formfiller.utilities.TestSetup;

public class EventHandlerTest {	
	private EventHandler eventHandler;
	private EventSource mockEventSource;
	
	private FormComponent getCurrent() {
		Transporter transporter = ApplicationContext.formComponentGateway.getTransporter();
		return transporter.getCurrent();
	}

	@Before
	public void setUp() {
		TestSetup.setupContext();
		eventHandler = new EventHandler();
		mockEventSource = Mockito.mock(EventSource.class);
	}
	
	@Test
	public void getCurrentIsFormComponentDotEnd() {
		assertThat(getCurrent(), is(FormComponent.END));
	}

	@Test
	public void canHandleNull() {		
		eventHandler.update(mockEventSource, null);
		
		assertThat(getCurrent(), is(FormComponent.END));
	}

	@Test
	public void canHandleEmptyString() {
		eventHandler.update(mockEventSource, "");
		
		assertThat(getCurrent(), is(FormComponent.END));
	}
}
