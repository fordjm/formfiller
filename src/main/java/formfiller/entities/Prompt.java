package formfiller.entities;

import java.util.ArrayList;
import java.util.List;

public class Prompt implements PromptFunctions {
	String id;
	String content;
	ResponseConstraint format;
	List<String> selections;

	public Prompt(String id, String content) {
		this.id = id;
		this.content = content;
		selections = new ArrayList<String>();
	}
	
	public String content(){
		return content;
	}

	public <T> void setFormat(ResponseConstraint<T> f) {
		format = f;
	}

	public <T> ResponseConstraint<T> format() {
		return format;
	}

	public String id() {
		return id;
	}
}
