package formfiller.transactions;

import java.lang.reflect.Type;

import formfiller.entities.Constraint;
import formfiller.entities.ResponseType;
import formfiller.enums.ContentConstraint;

public class AddResponseType extends AddConstraint {
	Type type;
	public AddResponseType(Type type) {
		super(ContentConstraint.TYPE);
		this.type = type;
	}
	protected Constraint makeConstraint() {
		return new ResponseType(type);
	}
}
