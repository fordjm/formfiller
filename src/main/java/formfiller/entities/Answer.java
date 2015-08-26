package formfiller.entities;

public interface Answer extends Constrainable{
	public int getId();
	public <T> T getContent();	// TODO:	Change this to Object
}
