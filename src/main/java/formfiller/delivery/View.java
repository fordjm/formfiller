package formfiller.delivery;

import java.util.Observer;

public interface View extends Observer {
	//	TODO:	Generalize
	public void output(String message);
}
