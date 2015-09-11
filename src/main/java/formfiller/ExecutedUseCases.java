package formfiller;

import java.util.EmptyStackException;
import java.util.Stack;

import formfiller.usecases.undoable.NullUndoableUseCase;
import formfiller.usecases.undoable.UndoableUseCase;

public class ExecutedUseCases {
	private Stack<UndoableUseCase> executedUseCases;
	
	public ExecutedUseCases(){
		executedUseCases = new Stack<UndoableUseCase>();
	}
	
	public void add(UndoableUseCase useCase){
		if (useCase == null) throw new NullAddition();
		executedUseCases.push(useCase);
	}
	
	public boolean contains(UndoableUseCase useCase){
		return executedUseCases.contains(useCase);
	}

	public UndoableUseCase getMostRecent() {
		try{
			return executedUseCases.peek();
		} catch (EmptyStackException e) {
			return NullUndoableUseCase.instance();
		} 
	}

	public UndoableUseCase removeMostRecent() {
		try{
			return executedUseCases.pop();
		} catch (EmptyStackException e) {
			return NullUndoableUseCase.instance();
		} 
	}
	
	@SuppressWarnings("serial")
	public class NullAddition extends RuntimeException { }
}
