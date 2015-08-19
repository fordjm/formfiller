package formfiller.request;

import formfiller.usecases.handleUnfoundController.HandleUnfoundControllerRequest;

public class HandleUnfoundControllerRequestImpl extends AbstractRequest 
		implements HandleUnfoundControllerRequest{
	String name = "HandleUnfoundControllerRequest";
	private String message;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}