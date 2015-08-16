package formfiller.usecases.presentQuestion;

public class PresentableQuestionImpl implements PresentableQuestion {
	
	public String id;
	public String content;
	public PresentableQuestionImpl() { }

	public String getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}