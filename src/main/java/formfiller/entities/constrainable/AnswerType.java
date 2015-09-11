package formfiller.entities.constrainable;

import java.lang.reflect.Type;

import formfiller.enums.ContentConstraint;

public class AnswerType implements Constrainable {
	ContentConstraint name = ContentConstraint.TYPE;	
	Type type;

	public AnswerType(Type type) {
		this.type = type;
	}

	public boolean isSatisfiedBy(Object objectUnderTest) {
		if (objectUnderTest == null) return false;
		
		Class<? extends Object> objectClass = objectUnderTest.getClass();
		return objectClass.equals(type);
	}
}
