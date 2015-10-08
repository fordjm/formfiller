package formfiller.entities.constrainable;

import java.lang.reflect.Type;

import formfiller.enums.ContentConstraint;
import formfiller.utilities.ParseTester;

public class AnswerType implements Constrainable {
	//	TODO:	Determine whether to replace enum with String.
	ContentConstraint name = ContentConstraint.TYPE;	
	Type type;

	public AnswerType(Type type) {
		this.type = type;
	}

	public boolean isSatisfiedBy(Object content) {
		if (content == null) return false;
		
		Class<?> contentClass = content.getClass();
		return contentClass.equals(type) || 
				ParseTester.canParseToType(type, content);
	}

	public boolean requiresType(Type type){
		return this.type.equals(type);
	}
	
}
