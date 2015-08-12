package formfiller.usecases;

import formfiller.entities.Answer;
import formfiller.gateways.ApplicationContext;

public class PresentResponseUseCase {
	public PresentableResponse presentResponse() {
		Answer response = ApplicationContext.responseGateway.getResponse();
		return new PresentableResponse(response.getId(), response.getContent());
	}
}
