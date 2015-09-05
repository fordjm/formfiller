package formfiller.delivery.ui.consoleUi;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.response.models.PresentableResponse;

public class ConsoleViewTest extends Observable {
	private ConsoleView consoleView;

	@Before
	public void setUp() {
		consoleView = new ConsoleView();
	}

	@Test
	public void isAnObserver() {
		assertThat(consoleView, is(instanceOf(Observer.class)));
	}
	
	@Test
	public void isNotAdded() {
		assertThat(countObservers(), is(0));
	}
	
	@Test
	public void canBeAdded() {
		addObserver(consoleView);
		
		assertThat(countObservers(), is(1));
	}

	// 	TODO:	Find a better way to test this.
	@Test
	public void outputRunsOnUpdate() {
		addObserver(consoleView);
		
		setChanged();
		notifyObservers(Mockito.mock(PresentableResponse.class));
		
		assertThat(consoleView.outputRan, is(true));
	}	
}
