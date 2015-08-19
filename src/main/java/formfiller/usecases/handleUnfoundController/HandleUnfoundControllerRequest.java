package formfiller.usecases.handleUnfoundController;

import formfiller.request.interfaces.Request;

public interface HandleUnfoundControllerRequest extends Request {
	public String getMessage();
	public void setMessage(String message);
}
