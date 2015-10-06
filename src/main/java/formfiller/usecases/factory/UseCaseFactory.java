package formfiller.usecases.factory;

import formfiller.appBoundaries.InputBoundary;

public interface UseCaseFactory {
	public InputBoundary make(String useCaseName);
}
