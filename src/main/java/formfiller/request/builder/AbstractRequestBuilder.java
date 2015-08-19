package formfiller.request.builder;

import formfiller.request.interfaces.Request;

public interface AbstractRequestBuilder {
	void buildName();
	Request getRequest();	
}
