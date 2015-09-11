package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.enums.WhichQuestion;
import formfiller.request.models.AskQuestionRequest;

public class AskQuestionRequestMocker {

	public static AskQuestionRequest makeMockAskQuestionRequest(WhichQuestion direction){
		AskQuestionRequest result = Mockito.mock(AskQuestionRequest.class);
		result.which = direction;
		return result;
	}
}
