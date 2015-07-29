package formfiller.entities;

import java.util.List;

public class SelectionFormat<T> extends ResponseFormat<T> {
	private List<T> selections;
	
	public SelectionFormat(AbstractResponse<T> component, List<T> selections){
		super(component);
		this.selections = selections;
	}

	public boolean satisfiesConstraint() {
		return selections.contains(response.getContent()) && 
				response.satisfiesConstraint();
	}

	public List<T> selections() {
		return selections;
	}
}
