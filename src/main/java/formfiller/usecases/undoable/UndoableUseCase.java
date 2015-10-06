package formfiller.usecases.undoable;

import formfiller.appBoundaries.InputBoundary;

public interface UndoableUseCase extends InputBoundary {
	void undo();
}