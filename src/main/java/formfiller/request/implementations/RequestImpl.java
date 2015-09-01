package formfiller.request.implementations;

import formfiller.request.interfaces.Request;

public class RequestImpl implements Request {
	String name;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
