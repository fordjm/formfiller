package formfiller.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;

public class SampleAnswers {
	private final double SAMPLE_DOUBLE;
	private final int SAMPLE_INT;
	private final Map<String, Answer> ALL;
	
	public SampleAnswers() {
		SAMPLE_DOUBLE = 13.5;
		SAMPLE_INT = 13;
		ALL = makeAllSamples();
	}

	private Map<String, Answer> makeAllSamples() {
		Map<String, Answer> result = new HashMap<String, Answer>();
		addAllSamples(result);
		return result;
	}
	
	private void addAllSamples(Map<String, Answer> samplesMap){
		samplesMap.put("double", makeAnswer("double", SAMPLE_DOUBLE));
		samplesMap.put("int", makeAnswer("int", SAMPLE_INT));
		samplesMap.put("String", makeAnswer("String", "sampleString"));
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
		result.remove(accepted);
		return result;
	}
	
}
