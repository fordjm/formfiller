package formfiller.entities;

import java.util.ArrayList;
import java.util.List;

public class Prompt implements IPrompt {
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

	public void setFormat(ResponseConstraint f) {
		format = f;
	}

	public ResponseConstraint format() {
		return format;
	}

	public String id() {
		return id;
	}
}
