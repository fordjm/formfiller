package formfiller.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {	
	public static Date makeDate(String input) {
		SimpleDateFormat format = new SimpleDateFormat("MMM-dd-yyyy");
		try {
			return format.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
