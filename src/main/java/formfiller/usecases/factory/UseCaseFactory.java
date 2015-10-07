package formfiller.usecases.factory;

import formfiller.appBoundaries.UseCase;

public interface UseCaseFactory {
	public UseCase make(String useCaseName);
}
