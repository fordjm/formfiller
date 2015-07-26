package formfiller.entities;

import java.util.List;

public class SelectionFormat<T> extends ResponseFormat<T> {
	private List<T> selections;
	
	public SelectionFormat(List<T> selections){
		this.selections = selections;
	}

	public boolean satisfiesConstraint(T response) {
		return selections.contains(response);
	}

	public List<T> selections() {
		return selections;
	}
}
