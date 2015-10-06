package formfiller.entities.format;

import java.util.ArrayList;
import java.util.List;

//	TODO:	Make abstract.  Add subclasses Single- and MultiOptionVariable
public class OptionVariable extends Format {
	//	TODO:	Make this variable private.
	//			Consider changing to a List<String>
	public List<Object> options = new ArrayList<Object>();
	
	public OptionVariable() {
		super();
		name = "OptionVariable";
	}

	public boolean matchesFormat(Object content) {
		return options.contains(content);
	}
	
}
