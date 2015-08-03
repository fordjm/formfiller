package formfiller.utilities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import formfiller.entities.ListResponse;
import formfiller.entities.Response;

public class TestUtil {
	
	public static <T> Response<T> makeMockResponse(int id, T content, boolean satisfiesConstraint) {
		Response<T> result = 
				(Response<T>) mock(Response.class);
		when(result.getId()).thenReturn(id);
		when(result.getContent()).thenReturn(content);
		when(result.satisfiesConstraint()).thenReturn(satisfiesConstraint);
		return result;
	}
}
