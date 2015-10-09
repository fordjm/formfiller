package formfiller.usecases.addAnswer;

import java.util.HashMap;
import java.util.Map;

import formfiller.entities.constrainable.Constrainable;

public class Constraints {
	private Map<String, Constrainable> itsConstraints;
	
	public Constraints() {
		itsConstraints = new HashMap<String, Constrainable>();
	}

	public boolean areSatisfiedBy(Object content) {
		if (isEmpty()) return true;
		
		for (Constrainable constraint : itsConstraints.values()){
			if (!constraint.isSatisfiedBy(content))
				return false;
		}
		return true;
	}
	
	public Constrainable get(String constraintKey){
		return itsConstraints.get(constraintKey);
	}

	public boolean isEmpty() {
		return itsConstraints.size() == 0;
	}

	public void add(Constrainable constraint) {
		String key = constraint.getClass().getSimpleName();
		itsConstraints.put(key, constraint);
	}

	public void remove(String key) {
		itsConstraints.remove(key);
	}
	
}
