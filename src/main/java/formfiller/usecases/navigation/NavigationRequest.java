package formfiller.usecases.navigation;

import formfiller.request.Request;

public interface NavigationRequest extends Request {
	public int getOffset();
	public void setOffset(int offset);
}
