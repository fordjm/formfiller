package formfiller.entities;

import java.lang.reflect.Type;

public class ResponseType<T> implements ResponseConstraint<T> {
	Type type;

	public ResponseType(Type type) {
		this.type = type;
	}

	public boolean satisfiesConstraint(T response) {
		return response.getClass().equals(type);
	}

}
