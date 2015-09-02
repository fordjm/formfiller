package formfiller.request.builders;

import java.util.HashMap;

import formfiller.request.models.Request;

public interface RequestBuilder {	
	public <K,V> Request build(String requestName, HashMap<K,V> args);	
}
