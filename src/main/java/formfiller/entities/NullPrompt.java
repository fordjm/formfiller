package formfiller.entities;

public class NullPrompt implements PromptFunctions {

	public String content() {
		return "";
	}

	public <T> void setFormat(ResponseConstraint<T> f) {}

	public <T> ResponseConstraint<T> format() {
		return new FreeEntryFormat<T>();
	}

	public String id() {
		return "";
	}
}
