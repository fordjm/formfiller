package formfiller.request;

public class NavigationRequestImpl extends AbstractRequest implements NavigationRequest{
	public int offset;

	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}