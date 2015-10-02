package formfiller.entities.answerFormat;

import java.util.ArrayList;
import java.util.List;

//	TODO:	Make abstract.  Add subclasses Single- and MultiOptionVariable
public class OptionVariable extends AnswerFormat {
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

	public void addOption(String option) {
		options.add(option);
	}	
	
}
