package formfiller.entities;

public class NullPrompt implements Prompt {
	 ResponseFormat format;
	
	public NullPrompt(){
		format = new FreeEntryFormat();
	}

	public String content() {
		return "";
	}

	public void setFormat(ResponseFormat f) {}

	public ResponseFormat format() {
		return format;
	}

	public String id() {
		return "";
	}
}
