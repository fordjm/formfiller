package formfiller.usecases.undoable;

import formfiller.request.models.Request;

//	TODO:	Is there any reason to throw exceptions here?
public class NullUndoableUseCase implements UndoableUseCase {
	private static NullUndoableUseCase itsInstance;
	
	private NullUndoableUseCase() { }

	public void execute(Request request) { }

	public void undo() { }

	public static NullUndoableUseCase instance() {
		if (itsInstance == null) itsInstance = new NullUndoableUseCase();
		return itsInstance;
	}
	
	@SuppressWarnings("serial")
	public class NullUseCaseExecution extends RuntimeException { }
	
	@SuppressWarnings("serial")
	public class NullUndo extends RuntimeException { }
}
