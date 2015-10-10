package formfiller.entities;

import formfiller.utilities.StringUtilities;

public class QuestionImpl implements Question {
	private static final String END_PROMPT = "You have reached the end of this form.";
	private static final String NULL_PROMPT = "No such question exists.";
	private static final String START_PROMPT = "You have reached the start of this form.";
	
	public static final Question NULL = new QuestionImpl().new NullQuestion("null", NULL_PROMPT);
	public static final Question END = new QuestionImpl().new NullQuestion("end", END_PROMPT);
	public static final Question START = new QuestionImpl().new NullQuestion("start", START_PROMPT);
	
	private String id = "";
	private String content = "";
	
	private class NullQuestion implements Question {
		public String id;
		public String content;
		
		public NullQuestion(String id, String content) {
			this.id = id;
			this.content = content;
		}
		
		public String getId() {
			return id;
		}
		
		public String getContent() {
			return content;
		}
		
		public boolean isValid() {
			return false;
		}
		
		public void setId(String id) {
			throw new NullObjectFieldValueSet();
		}
		
		public void setContent(String content) {
			throw new NullObjectFieldValueSet();
		}
		
	}

	public QuestionImpl(String id, String content) {
		this.id = id;
		this.content = content;
	}	
	
	public QuestionImpl() {	}
	

	
	public class NullObjectFieldValueSet extends RuntimeException {
		private static final long serialVersionUID = 1L; }

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	
	public boolean isValid() {
		return !StringUtilities.isStringNullOrEmpty(id) && 
				!StringUtilities.isStringNullOrEmpty(content);
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
