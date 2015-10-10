package formfiller.entities;

import formfiller.utilities.StringUtilities;

public class AnswerImpl implements Answer {
	public static final Answer NONE = new AnswerImpl().new NullAnswer();	
	private String questionId = "";
	private Object content = "";
	
	private class NullAnswer implements Answer {
		public String getId() {
			return "";
		}

		public Object getContent() {
			return "";
		}

		public boolean isValid() {
			return false;
		}

		public void setId(String id) {
			throw new NullFieldValueSet();
		}

		public void setContent(Object content) {
			throw new NullFieldValueSet();
		}
	}
	
	public String getId() {
		return questionId;
	}

	public Object getContent() {
		return content;
	}

	public boolean isValid() {
		return hasValidFieldValues();
	}

	//	TODO:	Fix answer content condition.
	private boolean hasValidFieldValues() {
		return !StringUtilities.isStringNullOrEmpty(questionId) && 
				content != "";
	}

	public void setId(String id) {
		questionId = id;
	}

	public void setContent(Object content) {
		this.content = content;		
	}
	
	public class NullFieldValueSet extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L; }
}
