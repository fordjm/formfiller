package formfiller.utilities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;

public class SampleAnswers {
	private final Map<String, Answer> ALL;
	private final Collection<Answer> NON_NUMBER_SAMPLES;
	
	public SampleAnswers() {
		ALL = makeAllSamples();
		NON_NUMBER_SAMPLES = makeNonNumberSamples();
	}

	private Map<String, Answer> makeAllSamples() {
		Map<String, Answer> result = new HashMap<String, Answer>();
		addAllSamples(result);
		return result;
	}
	
	//	TODO:	How to correctly handle Number?
	private void addAllSamples(Map<String, Answer> samplesMap){
		Number sampleNumber = BigDecimal.ONE;
		Date sampleDate = getCurrentDate();
		samplesMap.put("boolean", makeAnswer("boolean", true));
		samplesMap.put("byte", makeAnswer("byte", Byte.MIN_VALUE));
		samplesMap.put("double", makeAnswer("double", Double.MIN_VALUE));
		samplesMap.put("float", makeAnswer("float", Float.MIN_VALUE));
		samplesMap.put("int", makeAnswer("int", Integer.MIN_VALUE));
		samplesMap.put("long", makeAnswer("long", Long.MIN_VALUE));
		samplesMap.put("short", makeAnswer("short", Short.MIN_VALUE));
		samplesMap.put("Date", makeAnswer("Date", sampleDate));
		samplesMap.put("Number", makeAnswer("Number", sampleNumber));
		samplesMap.put("String", makeAnswer("String", "sampleString"));
	}

	private Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	private Collection<Answer> makeNonNumberSamples() {
		Collection<Answer> result = new ArrayList<Answer>();
		result.add(get("boolean"));
		result.add(get("Date"));
		result.add(get("String"));
		return result;
	}
	
	private Answer makeAnswer(String id, Object content) {
		Answer result = new AnswerImpl();
		result.setId(id);
		result.setContent(content);
		return result;
	}

	public Answer get(String type) {
		return ALL.get(type);
	}

	public Collection<Answer> getAllExcept(Object accepted) {
		Collection<Answer> result = new ArrayList<Answer>(ALL.values());
		return removeAcceptedValues(accepted, result);
	}

	private Collection<Answer> removeAcceptedValues(Object accepted, Collection<Answer> result) {
		if (accepted == get("Number")) return NON_NUMBER_SAMPLES;
		
		result.remove(accepted);
		return result;
	}
	
}
