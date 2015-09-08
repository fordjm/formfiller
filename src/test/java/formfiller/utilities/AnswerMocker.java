package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.entities.Answer;

public class AnswerMocker {
	
	public static Answer makeMockAnswer(int id, Object content){
		Answer result = Mockito.mock(Answer.class);
		result.id = id;
		result.content = content;
		return result;
	}
}
