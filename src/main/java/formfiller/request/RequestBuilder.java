package formfiller.request;

import java.util.HashMap;

public interface RequestBuilder {
	
	public <K,V> Request build(String requestName, HashMap<K,V> args);
	
}
