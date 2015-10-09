package formfiller.utilities;

import static org.junit.Assert.*;

import java.lang.reflect.Type;

import org.junit.Before;
import org.junit.Test;

public class StringToTypeConverterTest {
	private StringToTypeConverter converter;
	private Type converted;

	private void assertThatConverterReturnsType(Type type) {
		assertEquals(type, converted);
	}

	@Before
	public void setUp() {
		converter = new StringToTypeConverter();
	}

	@Test
	public void canConvertBoolean() {
		converted = converter.convert("boolean");
		
		assertThatConverterReturnsType(boolean.class);
	}

	@Test
	public void canConvertByte() {
		converted = converter.convert("byte");
		
		assertThatConverterReturnsType(byte.class);
	}

	@Test
	public void canConvertChar() {
		converted = converter.convert("char");
		
		assertThatConverterReturnsType(char.class);
	}

	@Test
	public void canConvertDouble() {
		converted = converter.convert("double");
		
		assertThatConverterReturnsType(double.class);
	}

	@Test
	public void canConvertFloat() {
		converted = converter.convert("float");
		
		assertThatConverterReturnsType(float.class);
	}

	@Test
	public void canConvertInt() {
		converted = converter.convert("int");
		
		assertThatConverterReturnsType(int.class);
	}

	@Test
	public void canConvertLong() {
		converted = converter.convert("long");
		
		assertThatConverterReturnsType(long.class);
	}

	@Test
	public void canConvertShort() {
		converted = converter.convert("short");
		
		assertThatConverterReturnsType(short.class);
	}

	//	TODO:	Determine how to handle capitalization inconsistency.
	@Test
	public void canConvertNumber() {
		converted = converter.convert("number");
		
		assertThatConverterReturnsType(Number.class);
	}
	
	@Test
	public void canConvertString() {
		converted = converter.convert("string");
		
		assertThatConverterReturnsType(String.class);
	}

}
