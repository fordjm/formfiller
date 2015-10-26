package formfiller.utilities;

public interface StringToTypeConverter {
	Class<?> convert(String abbreviatedName);
	
	Class<?> convertUtilMemberClass(String abbreviatedName);
}