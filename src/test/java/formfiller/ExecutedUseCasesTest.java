package formfiller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.usecases.undoable.UndoableUseCase;

public class ExecutedUseCasesTest {
	private ExecutedUseCases executedUseCases;
	private UndoableUseCase mostRecent;

	private void updateMostRecent_ByGetting() {
		mostRecent = executedUseCases.getMostRecent();
	}

	private void assertThat_GetMostRecent_ReturnsUndoableUseCase(
			UndoableUseCase undoableUseCase) {
		assertThat(mostRecent, is(undoableUseCase));
	}

	private void updateMostRecent_ByRemoving() {
		mostRecent = executedUseCases.removeMostRecent();
	}

	private void addExecutedUseCase(UndoableUseCase undoableUseCase) {
		executedUseCases.add(undoableUseCase);
	}

	private UndoableUseCase makeMockUndoableUseCase() {
		return Mockito.mock(UndoableUseCase.class);
	}

	private void assertThat_UndoableUseCase_IsContained(UndoableUseCase mockUndoableUseCase) {
		assertThat(executedUseCases.contains(mockUndoableUseCase), is(true));
	}

	private void assertThat_UndoableUseCase_IsNotContained(UndoableUseCase mockUndoableUseCase) {
		assertThat(executedUseCases.contains(mockUndoableUseCase), is(false));
	}

	@Before
	public void setUp() {
		executedUseCases = new ExecutedUseCases();
	}

	@Test
	public void atStart_GettingMostRecent_ReturnsNullObject() {
		updateMostRecent_ByGetting();
		
		assertThat_GetMostRecent_ReturnsUndoableUseCase(UndoableUseCase.NULL);
	}

	@Test
	public void atStart_RemovingMostRecent_ReturnsNullObject() {
		updateMostRecent_ByRemoving();
		
		assertThat_GetMostRecent_ReturnsUndoableUseCase(UndoableUseCase.NULL);
	}
	
	@Test(expected = ExecutedUseCases.NullAddition.class)
	public void addingNull_ThrowsException(){
		addExecutedUseCase(null);
	}
	
	@Test
	public void afterAddingAUseCase_GettingMostRecent_ReturnsGivenUseCase(){
		UndoableUseCase mockUndoableUseCase = makeMockUndoableUseCase();
		
		addExecutedUseCase(mockUndoableUseCase);
		updateMostRecent_ByGetting();
		
		assertThat_GetMostRecent_ReturnsUndoableUseCase(mockUndoableUseCase);
		assertThat_UndoableUseCase_IsContained(mockUndoableUseCase);
	}
	
	@Test
	public void afterAddingAUseCase_RemovingMostRecent_ReturnsGivenUseCase(){
		UndoableUseCase mockUndoableUseCase = makeMockUndoableUseCase();
		
		addExecutedUseCase(mockUndoableUseCase);
		updateMostRecent_ByRemoving();
		
		assertThat_GetMostRecent_ReturnsUndoableUseCase(mockUndoableUseCase);
		assertThat_UndoableUseCase_IsNotContained(mockUndoableUseCase);
	}
}
