package formfiller.usecases.undoable;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NullUndoableUseCaseTest {
	private NullUndoableUseCase useCase;
	
	/*@Before
	public void setUp(){
		useCase = NullUndoableUseCase.instance();		
	}

	@Test
	public void instance_IsAnUndoableUseCase() {		
		assertThat(useCase, is(instanceOf(UndoableUseCase.class)));
	}

	@Test(expected = NullUndoableUseCase.NullUseCaseExecution.class)
	public void executingNullUseCase_ThrowsException() {
		useCase.execute(null);
	}

	@Test(expected = NullUndoableUseCase.NullUndo.class)
	public void undoingNullUseCase_ThrowsException() {
		useCase.undo();
	}*/
}
