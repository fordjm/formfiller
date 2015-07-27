package formfiller.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class NullResponseTest {

	@Test
	public void canCreateNullResponse() {
		Response<String> r = new NullResponse();
		assertEquals(-1, r.id());
		assertEquals("", r.content());
	}
}
