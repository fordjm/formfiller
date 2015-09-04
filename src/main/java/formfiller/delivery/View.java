package formfiller.delivery;

import java.util.Observer;

public interface View extends Observer {

	public void output(String message);
}
