package formfiller.usecases;

public class PresentQuestionController {

	public void requestPresentableQuestion() {
		PresentQuestionUseCase presentQuestionUseCase = 
				new PresentQuestionUseCase();
		presentQuestionUseCase.requestPresentableQuestion();
	}

}
