package formfiller.utilities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import formfiller.entities.Prompt;
import formfiller.entities.ResponseImpl;

public class TestUtil {
	public static Prompt createMockPrompt(Class<?> classToMock, String id, String content) {
		Prompt mockPrompt = 
				(Prompt) mock(classToMock);
		when(mockPrompt.id()).thenReturn(id);
		when(mockPrompt.content()).thenReturn(content);
		return mockPrompt;
	}

	public static <T> ResponseImpl<T> createMockResponseImpl(int id, T content) {
		ResponseImpl<T> mockResponseImpl = 
				(ResponseImpl<T>) mock(ResponseImpl.class);
		when(mockResponseImpl.id()).thenReturn(id);
		when(mockResponseImpl.content()).thenReturn(content);
		return mockResponseImpl;
	}	

	public static ResponseImpl<String> createMockStringResponseImpl(int id, String content){
		return TestUtil.createMockResponseImpl(id, content);
	}

	public static ResponseImpl<Integer> createMockIntegerResponseImpl(int id, Integer content) {
		return TestUtil.createMockResponseImpl(id, content);
	}

	public static ResponseImpl<Date> createMockDateResponseImpl(int id, Date content) {
		return TestUtil.createMockResponseImpl(id, content);
	}
}
