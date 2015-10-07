package formfiller.entities.constrainable;

import java.lang.reflect.Type;

import formfiller.enums.ContentConstraint;

public class AnswerType implements Constrainable {
	ContentConstraint name = ContentConstraint.TYPE;	
	Type type;

	public AnswerType(Type type) {
		this.type = type;
	}

	//	TODO:	Make this work for primitives.
	public boolean isSatisfiedBy(Object content) {
		if (content == null) return false;
		
		Class<?> contentClass = content.getClass();
		return contentClass.equals(type) || canParseToType(content);
	}

	private boolean canParseToType(Object content) {
		try{
			unwrapWrappedPrimitive(content);
			return true;
		} catch (Exception e){
			return false;
		}		
	}

	private void unwrapWrappedPrimitive(Object content) {
		if (type.equals(byte.class)) ((Byte)content).byteValue();
		else if (type.equals(boolean.class)) ((Boolean)content).booleanValue();
		else if (type.equals(char.class)) ((Character)content).charValue();
		else if (type.equals(double.class)) ((Double)content).doubleValue();
		else if (type.equals(float.class)) ((Float)content).floatValue();
		else if (type.equals(int.class)) ((Integer)content).intValue();
		else if (type.equals(long.class)) ((Long)content).longValue();
		else if (type.equals(short.class)) ((Short)content).shortValue();
		else throw new IllegalArgumentException("Cannot unwrap " + content + 
					" to Java primitive!");
	}

	public boolean requiresType(Type type){
		return this.type.equals(type);
	}
	
}
