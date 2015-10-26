package formfiller.utilities;

import org.apache.commons.lang3.ClassUtils;

public class ApacheCommonsStringToTypeConverter implements StringToTypeConverter {
	private final String LANG_PACKAGE_PREFIX = "java.lang.";
	private final String UTIL_PACKAGE_PREFIX = "java.util.";
	
	//	TODO:	Determine where these package-specific methods belong.
	public Class<?> convert(String abbreviatedName) {
		return convert(LANG_PACKAGE_PREFIX, abbreviatedName);
	}

	public Class<?> convertUtilMemberClass(String abbreviatedName) {
		Package pkg = java.lang.reflect.Type.class.getPackage();
		Package.getPackages();
		return convert(UTIL_PACKAGE_PREFIX, abbreviatedName);
	}

	//	TODO:	Handle errors in a communicative way.
	public Class<?> convert(String packageName, String abbreviatedName) {
		String fullName = packageName + abbreviatedName;		
		try {
			// return Class.forName(fullName);
			return ClassUtils.getClass(fullName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
