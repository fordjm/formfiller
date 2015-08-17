package formfiller.usecases.handleUnfoundController;

import formfiller.usecases.Request;

public interface HandleUnfoundControllerRequest extends Request {
	public String getMessage();
	public void setMessage(String message);
}
