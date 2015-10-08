package formfiller.utilities;

import java.lang.reflect.Type;

public class ParseTester {  
	public static boolean canParseToType(Type type, Object object) {
		try{
			parseOrThrowException(type, object);
			return true;
		} catch (Exception e){
			return false;
		}		
	}

	//	TODO:	Make this extensible to new types like GeoPoint and alternate parsing methods.
	private static void parseOrThrowException(Type type, Object object) {
		if (type.equals(byte.class)) ((Byte) object).byteValue();
		else if (type.equals(boolean.class)) ((Boolean) object).booleanValue();
		else if (type.equals(char.class)) ((Character) object).charValue();
		else if (type.equals(double.class)) ((Double) object).doubleValue();
		else if (type.equals(float.class)) ((Float) object).floatValue();
		else if (type.equals(int.class)) ((Integer) object).intValue();
		else if (type.equals(long.class)) ((Long) object).longValue();
		else if (type.equals(short.class)) ((Short) object).shortValue();
		else throw new IllegalArgumentException("Cannot parse objects to type " + type);
	}
	
}