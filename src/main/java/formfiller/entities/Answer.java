package formfiller.entities;

/**
 * The interface for Answer objects.
 */
public interface Answer {
	String getId();

	Object getContent();

	boolean isValid();

	void setId(String id);

	void setContent(Object content);
	
}