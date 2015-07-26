package formfiller.entities;

public class PromptImpl<T> implements Prompt<T> {
	String id;
	String content;
	ResponseFormat<T> format;

	public PromptImpl(String id, String content) {
		this.id = id;
		this.content = content;
	}
	
	public String content(){
		return content;
	}

	public String id() {
		return id;
	}

	public void setFormat(ResponseFormat<T> f) {
		format = f;
	}

	public ResponseFormat<T> format() {
		return format;
	}
}
