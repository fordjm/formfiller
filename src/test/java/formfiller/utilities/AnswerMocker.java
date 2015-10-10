package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.entities.AnswerImpl;

public class AnswerMocker {	

	public static AnswerImpl makeMockEmptyAnswer(){
		return Mockito.mock(AnswerImpl.class);
	}
	
	public static AnswerImpl makeMockAnswer(Object content){
		return AnswerMocker.makeMockAnswer("id", content);
	}
	
	public static AnswerImpl makeMockAnswer(String id, Object content){
		AnswerImpl result = makeMockEmptyAnswer();
		Mockito.when(result.isValid()).thenReturn(true);
		Mockito.when(result.getId()).thenReturn(id);
		Mockito.when(result.getContent()).thenReturn(content);
		return result;
	}
	
}
