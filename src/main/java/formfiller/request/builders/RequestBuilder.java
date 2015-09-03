package formfiller.request.builders;

import formfiller.delivery.controller.Arguments;
import formfiller.request.models.Request;

public interface RequestBuilder {	
	public Request build(String requestName, Arguments args);
}
