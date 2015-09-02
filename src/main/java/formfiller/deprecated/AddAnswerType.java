package formfiller.deprecated;

import java.lang.reflect.Type;

import formfiller.entities.Constraint;
import formfiller.entities.AnswerType;
import formfiller.enums.ContentConstraint;

public class AddAnswerType extends AddConstraint {
	Type type;
	
	public AddAnswerType(Type type) {
		super(ContentConstraint.TYPE);
		this.type = type;
	}
	
	protected Constraint makeConstraint() {
		return new AnswerType(type);
	}
}
