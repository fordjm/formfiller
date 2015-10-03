package formfiller.utilities;

import formfiller.usecases.undoable.UndoableUseCaseExecution;

public class UndoableUseCaseExecutionCommonTests {
	private UndoableUseCaseExecution useCase;
	
	public static boolean runTestsOnUseCase(UndoableUseCaseExecution useCase){
		return new UndoableUseCaseExecutionCommonTests(useCase).runTests();
	}

	private UndoableUseCaseExecutionCommonTests(UndoableUseCaseExecution useCase) { 
		this.useCase = useCase;
	}
	
	private boolean runTests() {
		return extendsUndoableUseCaseExecution() && 
				undoingBeforeExecutingThrowsException() && 
				executingNullThrowsException();
	}

	public boolean extendsUndoableUseCaseExecution() {		
		return useCase instanceof UndoableUseCaseExecution;
	}
	
	public boolean undoingBeforeExecutingThrowsException(){
		try{
			useCase.undo();
			return false;
		} catch (UndoableUseCaseExecution.UnsuccessfulUseCaseUndo u){
			return true;
		}
	}

	public boolean executingNullThrowsException() {
		try{
			useCase.execute(null);
			return false;
		} catch (NullPointerException n){
			return true;
		}		
	}

}
