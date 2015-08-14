package formfiller.entities;

public interface Prompt {
	String getContent();
	String getId();
	boolean hasAnswer();
	boolean requiresAnswer();
}