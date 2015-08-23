package formfiller.request.implementations;

import formfiller.request.interfaces.HandleUnfoundControllerRequest;

public class HandleUnfoundControllerRequestImpl extends RequestImpl 
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