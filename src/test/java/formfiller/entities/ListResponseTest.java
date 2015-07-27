package formfiller.entities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ListResponseTest<T> {
	private String getStringContent(Response<String> response){
		return response.content();
	}	

	@Test
	public void canCreateListResponse() {
		Response<Response<String>> responseContent = new ListResponse<String>(0, "Fantasia");
		String stringContent = getStringContent(responseContent.content());

		assertTrue(responseContent instanceof ListResponse);
		assertTrue(responseContent.content() instanceof Response);
		assertTrue(stringContent instanceof String);
	}
}
