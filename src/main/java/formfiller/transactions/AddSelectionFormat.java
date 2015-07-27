package formfiller.transactions;

import java.util.List;

import formfiller.entities.AbstractResponse;
import formfiller.entities.ResponseFormat;
import formfiller.entities.SelectionFormat;

public class AddSelectionFormat<T> extends AddFormat<T> {
	private AbstractResponse<T> response;
	private List<T> selections;
	
	public AddSelectionFormat(AbstractResponse<T> response){
		this.response = response;
	}

	public AddSelectionFormat(List<T> selections) {
		this.selections = selections;
	}

	@Override
	protected ResponseFormat<T> makeFormat() {
		return new SelectionFormat<T>(response, selections);
	}
}
