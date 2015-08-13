package formfiller.usecases;

public interface PresentableQuestion {
	public String getId();
	public String getContent();
	public void setId(String id);
	public void setContent(String content);
}
