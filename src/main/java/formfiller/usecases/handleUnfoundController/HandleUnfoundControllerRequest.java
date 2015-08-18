package formfiller.usecases.handleUnfoundController;

import formfiller.request.Request;

public interface HandleUnfoundControllerRequest extends Request {
	public String getMessage();
	public void setMessage(String message);
}
