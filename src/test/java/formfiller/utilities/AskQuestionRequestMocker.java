package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.enums.QuestionAsked;
import formfiller.request.models.AskQuestionRequest;

public class AskQuestionRequestMocker {

	public static AskQuestionRequest makeMockAskQuestionRequest(QuestionAsked direction){
		AskQuestionRequest result = Mockito.mock(AskQuestionRequest.class);
		result.which = direction;
		return result;
	}
}
