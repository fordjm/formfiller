package formfiller.delivery.controller;

import java.util.HashMap;
import java.util.Map;

public class Arguments {
	private  Map<String,Object> arguments = new HashMap<String,Object>();
	
	public void add(String key, Object value){
		arguments.put(key, value);
	}
	
	public Object getById(String key){
		return arguments.get(key);
	}
}
