package formfiller.request.builder;

import java.util.HashMap;

import formfiller.request.interfaces.Request;

public interface RequestBuilder {
	
	public <K,V> Request build(String requestName, HashMap<K,V> args);
	
}
