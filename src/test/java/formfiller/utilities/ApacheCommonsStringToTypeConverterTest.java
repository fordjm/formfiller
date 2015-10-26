package formfiller.utilities;

//import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ApacheCommonsStringToTypeConverterTest {
	private ApacheCommonsStringToTypeConverter converter;
	private Class<?> converted;

	@Before
	public void setUp() {
		converter = new ApacheCommonsStringToTypeConverter();		
	}		
	
	@Test
	public void canConvertBoolean() {
		converted = converter.convert("Boolean");
		assertEquals(Boolean.class, converted);
	}	
	
	@Test
	public void canConvertByte() {
		converted = converter.convert("Byte");
		assertEquals(Byte.class, converted);
	}	
	
	@Test
	public void canConvertCharacter() {
		converted = converter.convert("Character");
		assertEquals(Character.class, converted);
	}
	
	@Test
	public void canConvertDate() {
		converted = converter.convert("java.util.", "Date");
		assertEquals(Date.class, converted);
	}
	
	@Test
	public void canConvertDouble() {
		converted = converter.convert("Double");
		assertEquals(Double.class, converted);
	}
	
	@Test
	public void canConvertInteger() {
		converted = converter.convert("Integer");
		assertEquals(Integer.class, converted);
	}
	
	@Test
	public void canConvertString() {
		converted = converter.convert("String");
		assertEquals(String.class, converted);
	}

}
