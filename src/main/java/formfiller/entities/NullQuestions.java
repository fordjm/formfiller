package formfiller.entities;

public class NullQuestions {
	private static final String START_PROMPT = "You have reached the start of this form.";
	private static final String END_PROMPT = "You have reached the end of this form.";
	private static final String NULL_PROMPT = "No such question exists.";
	
	public static final Question START = makeNullPrompt(START_PROMPT, "start");
	public static final Question END = makeNullPrompt(END_PROMPT, "end");
	public static final Question NULL = makeNullPrompt(NULL_PROMPT, "null");
	
	private NullQuestions(){ }

	private static Question makeNullPrompt(final String content, final String id) {
		Question result = new Question();
		result.id = id;
		result.content = content;
		return result;
	}
	
	//	TODO:	Move these to FormComponent
	public boolean hasAnswer() {
		return false;
	}
	
	public boolean requiresAnswer() {
		return false;
	}
}
