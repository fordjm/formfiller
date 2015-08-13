package formfiller.usecases;

import formfiller.entities.Answer;
import formfiller.ApplicationContext;

public class PresentResponseUseCase {
	public PresentableResponse presentResponse() {
		Answer response = ApplicationContext.answerGateway.getResponse();
		return new PresentableResponse(response.getId(), response.getContent());
	}
}
