package formfiller.utilities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import formfiller.deprecated.ConstrainableAnswer;

public class ConstrainableAnswerMocker {
	
	public static ConstrainableAnswer makeMockNameAnswer(String name) {
		return makeMockAnswer(0, name, true);
	}
	
	public static ConstrainableAnswer makeMockAgeAnswer(int age) {
		return makeMockAnswer(0, age, true);
	}
	
	public static <T> ConstrainableAnswer makeMockAnswer(int id, T content, boolean satisfiesConstraint) {
		ConstrainableAnswer result = 
				(ConstrainableAnswer) mock(ConstrainableAnswer.class);
		when(result.getId()).thenReturn(id);
		when(result.getContent()).thenReturn(content);
		when(result.isSatisfiedBy(null)).thenReturn(satisfiesConstraint);
		return result;
	}
	
	public static <T> ConstrainableAnswer makeMockAnswer(boolean satisfiesConstraint) {
		ConstrainableAnswer result = 
				(ConstrainableAnswer) mock(ConstrainableAnswer.class);
		when(result.getContent()).thenReturn("");
		when(result.isSatisfiedBy(null)).thenReturn(satisfiesConstraint);
		return result;
	}
}
