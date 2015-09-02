package formfiller.usecases.factory;

import formfiller.boundaries.UseCase;

public interface UseCaseFactory {
	public UseCase make(String useCaseName);
}
