package formfiller.entities.format;

import java.util.ArrayList;
import java.util.List;

public abstract class OptionVariable extends Format {
	//	TODO:	Make this variable private.
	//			Consider changing to a List<String>
	public List<Object> options;
	
	public OptionVariable() {
		super();
		options = new ArrayList<Object>();
	}

	public boolean matchesFormat(Object content) {
		return options.contains(content);
	}
	
}
