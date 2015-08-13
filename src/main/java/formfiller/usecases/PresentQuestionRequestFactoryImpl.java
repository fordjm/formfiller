package formfiller.usecases;

public class PresentQuestionRequestFactoryImpl implements PresentQuestionRequestFactory {
	public PresentQuestionRequest makePresentQuestionRequest(){		
		return new PresentQuestionRequest();
	}

	public class PresentQuestionRequest {		
		private PresentQuestionRequest(){ }
	}

}
