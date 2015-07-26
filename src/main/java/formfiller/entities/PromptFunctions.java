package formfiller.entities;

public interface PromptFunctions {
	String content();
	String id();
	<T> void setFormat(ResponseConstraint<T> f);
	<T> ResponseConstraint<T> format();
}