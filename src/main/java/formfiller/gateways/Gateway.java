package formfiller.gateways;

public interface Gateway<T> {
	T find(String id);
	
	void save(T formComponent);
}
