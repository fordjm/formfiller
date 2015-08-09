package formfiller.entities;

public interface Response extends Constrainable{
	public int getId();
	public <T> T getContent();
}
