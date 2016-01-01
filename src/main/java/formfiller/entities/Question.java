package formfiller.entities;

/**
 * The interface for Question objects.
 */
public interface Question {

	String getId();

	String getContent();

	boolean isValid();

	void setId(String id);

	void setContent(String content);
}