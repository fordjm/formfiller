package formfiller.usecases.factory;

import formfiller.ApplicationContext;
import formfiller.appBoundaries.UseCase;
import formfiller.enums.ActionOutcome;
import formfiller.request.models.Request;
import formfiller.response.models.PresentableResponse;
import formfiller.usecases.factory.UseCaseFactoryImpl.UnknownUseCase;

public class LocalUseCaseFactory implements UseCaseFactory {
	UseCaseFactoryImpl impl = new UseCaseFactoryImpl();

	public UseCase make(String useCaseName) {
		try{
			return impl.make(useCaseName);
		} catch (UnknownUseCase u) {
			return makeUnfoundUseCase();
		}
	}

	// This never happens if the main partition uses default controllers.
	private UseCase makeUnfoundUseCase() {
		return new UseCase(){
			public void execute(Request request) {
				ApplicationContext.responsePresenter.present(
						makePresentableFailedUseCase());
			}  
		};
	}
	
	private PresentableResponse makePresentableFailedUseCase(){
		PresentableResponse result = new PresentableResponse();
		result.outcome = ActionOutcome.FAILED;
		result.message = "No appropriate use case was found.  Please try again.";
		return result;
	}
}
