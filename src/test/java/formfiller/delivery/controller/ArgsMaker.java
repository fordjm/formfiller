package formfiller.delivery.controller;

import java.util.HashMap;

public class ArgsMaker {
	
	public static <K,V> HashMap<K,V> makeArgs(K key, V value){
		return new ArgsMaker().makeArgsHashmap(key, value);
	}
	
	private ArgsMaker() { }
	
	private <K,V> HashMap<K,V> makeArgsHashmap(K key, V value){
		HashMap<K,V> result = new HashMap<K,V>();
		result.put(key, value);
		return result;
	}
}
