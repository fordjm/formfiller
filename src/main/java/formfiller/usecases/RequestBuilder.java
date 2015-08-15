package formfiller.usecases;

import java.util.HashMap;

public interface RequestBuilder {
	
	public Request build(String requestName, HashMap args);
	
}
