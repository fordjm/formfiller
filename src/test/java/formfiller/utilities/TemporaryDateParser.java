package formfiller.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TemporaryDateParser {
	public static Date parseDateFromStringMonthFirst(String input){
		SimpleDateFormat format = new SimpleDateFormat("MMM-dd-yyyy");
		try {
			return format.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date parseDateFromStringDateFirst(String input){
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			return format.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
