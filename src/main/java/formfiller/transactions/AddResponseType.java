package formfiller.transactions;

import java.lang.reflect.Type;

import formfiller.entities.Constraint;
import formfiller.entities.ResponseType;
import formfiller.enums.ContentConstraint;

public class AddResponseType<T> extends AddConstraint<T> {
	Type type;
	public AddResponseType(Type type) {
		super(ContentConstraint.TYPE);
		this.type = type;
	}
	protected Constraint<T> makeConstraint() {
		return new ResponseType<T>(type);
	}
}
