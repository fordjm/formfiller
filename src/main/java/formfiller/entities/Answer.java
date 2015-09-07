package formfiller.entities;

import java.util.ArrayList;
import java.util.Collection;

public class Answer {
	public int id = -1;
	public Object content = "";
	public Collection<Constrainable> constraints = new ArrayList<Constrainable>();
}
