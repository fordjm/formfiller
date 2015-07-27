package formfiller.entities;

import java.lang.reflect.Type;

public class ResponseType<T> extends ConstraintDecorator<T> {
	Type type;

	public ResponseType(AbstractResponse<T> response, Type type) {
		super(response);
		this.type = type;
	}

	public boolean satisfiesConstraint(T response) {
		return response.getClass().equals(type);
	}

	public boolean satisfiesConstraint() {
		return content().getClass().equals(type);
	}

	@Override
	public T content() {
		return response.content();
	}
}
