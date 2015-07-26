package formfiller.transactions;

import formfiller.entities.FreeEntryFormat;
import formfiller.entities.ResponseConstraint;

public class AddFreeEntryPrompt<T> extends AddPrompt {

	public AddFreeEntryPrompt(String id, String content) {
		super(id, content);
	}

	public ResponseConstraint<T> makeFormat(){
		return new FreeEntryFormat<T>();
	}
}
