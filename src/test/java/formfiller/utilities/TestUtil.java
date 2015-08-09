package formfiller.utilities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import formfiller.entities.Constraint;
import formfiller.entities.Prompt;
import formfiller.entities.Response;
import formfiller.enums.ContentConstraint;

public class TestUtil {
	public static <T> Constraint makeMockConstraint(int id, boolean satisfiesConstraint) {
		Constraint result = mock(Constraint.class);
		when(result.getName()).thenReturn(ContentConstraint.MOCK);
		when(result.getId()).thenReturn(id);
		when(result.satisfiesConstraint()).thenReturn(satisfiesConstraint);
		return result;
	}
	public static Prompt makeMockNamePrompt() {
		return makeMockPrompt("name", "What is your name?");
	}
	public static Prompt makeMockPrompt(String id, String content){
		Prompt result = mock(Prompt.class);
		when (result.getId()).thenReturn(id);
		when (result.getContent()).thenReturn(content);
		return result;
	}
	public static Response makeMockNameResponse(String name) {
		return makeMockResponse(0, name, true);
	}
	public static <T> Response makeMockResponse(int id, T content, boolean satisfiesConstraint) {
		Response result = 
				(Response) mock(Response.class);
		when(result.getId()).thenReturn(id);
		when(result.getContent()).thenReturn(content);
		when(result.satisfiesConstraint()).thenReturn(satisfiesConstraint);
		return result;
	}
	public static <T> Response makeMockResponse(boolean satisfiesConstraint) {
		Response result = 
				(Response) mock(Response.class);
		when(result.getContent()).thenReturn("");
		when(result.satisfiesConstraint()).thenReturn(satisfiesConstraint);
		return result;
	}
}
