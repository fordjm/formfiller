package formfiller.utilities;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

//	TODO:	Add an opening for StringMatcher.
//			How to make equalsIgnoreCase work for keys?
public class StringToTypeConverter {
	private Map<String, Type> supportedTypes;
	
	public StringToTypeConverter(){
		supportedTypes = populateSupportedTypes();
	}

	private Map<String, Type> populateSupportedTypes() {
		HashMap<String, Type> result = new HashMap<String, Type>();
		result.put("boolean", boolean.class);
		result.put("byte", byte.class);
		result.put("char", char.class);
		result.put("double", double.class);
		result.put("float", float.class);
		result.put("int", int.class);
		result.put("long", long.class);
		result.put("short", short.class);
		result.put("Number", Number.class);
		result.put("String", String.class);
		return result;
	}
	
	public Type convert(String input) {
		return supportedTypes.get(input);
	}

}
