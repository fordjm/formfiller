package formfiller.entities;

public interface Prompt<T> {
	String content();
	String id();
	void setFormat(ResponseFormat<T> f);
	ResponseFormat<T> format();
}