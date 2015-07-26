package formfiller.transactions;

import formfiller.entities.FreeEntryFormat;
import formfiller.entities.ResponseFormat;

public class AddFreeEntryPrompt<T> extends AddPrompt<T> {

	public AddFreeEntryPrompt(String id, String content) {
		super(id, content);
	}

	public ResponseFormat<T> makeFormat(){
		return new FreeEntryFormat<T>();
	}
}
