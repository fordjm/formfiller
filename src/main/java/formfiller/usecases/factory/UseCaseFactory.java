package formfiller.usecases.factory;

import formfiller.applicationBoundaryInterfaces.UseCase;

public interface UseCaseFactory {
	public UseCase make(String useCaseName);
}
