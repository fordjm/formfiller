package formfiller.boundaryCrossers;

import formfiller.delivery.PresentableResponseImpl;

public class PresentableAnswer extends PresentableResponseImpl {
	int id = -1;

	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}