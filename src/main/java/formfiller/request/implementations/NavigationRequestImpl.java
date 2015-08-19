package formfiller.request.implementations;

import formfiller.request.interfaces.NavigationRequest;

public class NavigationRequestImpl extends RequestImpl implements NavigationRequest{
	public int offset;

	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}