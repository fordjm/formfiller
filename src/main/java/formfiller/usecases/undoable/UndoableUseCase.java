package formfiller.usecases.undoable;

import formfiller.appBoundaries.UseCase;
import formfiller.request.models.Request;

public interface UndoableUseCase extends UseCase, Undoable {
	public static final UndoableUseCase NULL = new UndoableUseCase(){
		public void execute(Request request) { }

		public void undo() { }
	};
}