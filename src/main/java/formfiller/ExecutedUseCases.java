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
	
	public void add(UndoableUseCase undoableUseCase){
		if (undoableUseCase == null) throw new NullAddition();
		executedUseCases.push(undoableUseCase);
	}
	
	public boolean contains(UndoableUseCase undoableUseCase){
		return executedUseCases.contains(undoableUseCase);
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
