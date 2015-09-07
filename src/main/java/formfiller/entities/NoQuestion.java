package formfiller.entities;

public class NoQuestion implements Prompt {
	private static final String START_PROMPT = "You have reached the start of this form.";
	private static final String END_PROMPT = "You have reached the end of this form.";
	private static final String NULL_PROMPT = "No such question exists.";
	public static final NoQuestion START = makeNullPrompt(START_PROMPT, "start");
	public static final NoQuestion END = makeNullPrompt(END_PROMPT, "end");
	public static final NoQuestion NULL = makeNullPrompt(NULL_PROMPT, "null");

	private static NoQuestion makeNullPrompt(final String content, final String id) {
		return new NoQuestion(){
			public String getContent() {
				return content;
			}
			public String getId() {
				return id;
			}
		};
	}
	
	public String getContent() {
		return "";
	}
	
	public String getId() {
		return "";
	}
	
	public boolean hasAnswer() {
		return false;
	}
	
	public boolean requiresAnswer() {
		return false;
	}
}
