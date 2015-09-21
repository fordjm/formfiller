package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.entities.Answer;

public class AnswerMocker {	
	public static Answer makeMockAnswer(String id, Object content){
		Answer result = Mockito.mock(Answer.class);
		result.questionId = id;
		result.content = content;
		return result;
	}
}
