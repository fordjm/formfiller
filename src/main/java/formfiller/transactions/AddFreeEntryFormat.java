package formfiller.transactions;

import formfiller.entities.AbstractResponse;
import formfiller.entities.FreeEntryFormat;
import formfiller.entities.ResponseFormat;

public class AddFreeEntryFormat<T> extends AddFormat<T> implements Transaction {
	AbstractResponse<T> response;
	
	public AddFreeEntryFormat(AbstractResponse<T> response){
		this.response = response;
	}

	@Override
	protected ResponseFormat<T> makeFormat() {
		return new FreeEntryFormat<T>(response);
	}
}
