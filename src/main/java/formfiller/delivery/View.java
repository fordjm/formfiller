package formfiller.delivery;

import java.util.Observer;

public interface View extends Observer {
	//	TODO:	Take a VM instead of a String.
	public void generateView(String message);
}
