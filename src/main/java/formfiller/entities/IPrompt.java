package formfiller.entities;

public interface IPrompt {
	String content();
	void setFormat(ResponseConstraint f);
	ResponseConstraint format();
	String id();
}