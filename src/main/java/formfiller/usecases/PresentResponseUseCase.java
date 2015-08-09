package formfiller.usecases;

import formfiller.entities.Response;
import formfiller.gateways.ApplicationContext;

public class PresentResponseUseCase {
	public PresentableResponse presentResponse() {
		Response response = ApplicationContext.responseGateway.getResponse();
		return new PresentableResponse(response.getId(), response.getContent());
	}
}
