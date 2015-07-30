package formfiller.utilities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import formfiller.entities.Prompt;
import formfiller.entities.Response;

public class TestUtil {
	
	public static <T> Response<T> makeMockResponse(int id, T content, boolean satisfiesConstraint) {
		Response<T> mockResponse = 
				(Response<T>) mock(Response.class);
		when(mockResponse.getId()).thenReturn(id);
		when(mockResponse.getContent()).thenReturn(content);
		when(mockResponse.satisfiesConstraint()).thenReturn(satisfiesConstraint);
		return mockResponse;
	}	
	
	// delete when all tests refactored
	public static Prompt createMockPrompt(String id, String content) {
		Prompt mockPrompt = 
				(Prompt) mock(Prompt.class);
		when(mockPrompt.getId()).thenReturn(id);
		when(mockPrompt.getContent()).thenReturn(content);
		return mockPrompt;
	}

	public static <T> Response<T> createMockResponse(int id, T content) {
		Response<T> mockResponse = 
				(Response<T>) mock(Response.class);
		when(mockResponse.getId()).thenReturn(id);
		when(mockResponse.getContent()).thenReturn(content);
		return mockResponse;
	}	

	public static Response<String> createMockStringResponse(int id, String content){
		return TestUtil.createMockResponse(id, content);
	}

	public static Response<Integer> createMockIntegerResponseImpl(int id, Integer content) {
		return TestUtil.createMockResponse(id, content);
	}

	public static Response<Date> createMockDateResponseImpl(int id, Date content) {
		return TestUtil.createMockResponse(id, content);
	}
}
