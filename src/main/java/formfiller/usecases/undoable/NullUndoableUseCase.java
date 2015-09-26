package formfiller.usecases.undoable;

import formfiller.request.models.Request;

public class NullUndoableUseCase implements UndoableUseCase {
	private static NullUndoableUseCase itsInstance;
	
	private NullUndoableUseCase() { }

	public void execute(Request request) { }

	public void undo() { }

	public static NullUndoableUseCase instance() {
		if (itsInstance == null) itsInstance = new NullUndoableUseCase();
		return itsInstance;
	}
}
