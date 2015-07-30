package formfiller.utilities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
}
