package fitnesse.fixtures;

import java.util.Date;

import formfiller.entities.constrainable.ValueBoundary;
import formfiller.entities.constrainable.ValueMaximum;
import formfiller.utilities.StringToTypeConverter;
import formfiller.utilities.TemporaryDateParser;

public class AddValueConstraint {
	private ValueBoundary boundary;
	private Class<?> type;
	
	//	TODO:	Generalize to "AddsTheBoundary"
	public void whenTheUserAddsTheValueMaximumToComponent(String typeString, 
			Object maximum, String componentId){
		executeUseCaseBehavior(typeString, maximum, componentId);
	}
	
	private void executeUseCaseBehavior(String typeString, Object maximum, 
			String componentId) {
		type = new StringToTypeConverter().convert(typeString);
		Object parsedMaximum = parseToType(maximum, type);
		this.boundary = new ValueMaximum(parsedMaximum);
		
	}

	//	TODO:	Use Natty Date parser for multiple formats.
	//			Find less terrible type parsing solution.
	private Object parseToType(Object maximum, Class<?> type) {
		String maxString = maximum.toString();
		if (type.equals(char.class))
			return maxString.charAt(0);
		else if (type.equals(double.class))
			return Double.parseDouble(maxString);
		else if (type.equals(int.class))
			return Integer.parseInt(maxString);
		else if (type.equals(Date.class))
			return TemporaryDateParser.parseDateFromStringDateFirst(maxString);
		else
			return null;
	}

	public boolean componentAcceptsValue(String componentId, 
			Object value){
		Object parsedValue = parseToType(value, type);
		return boundary.isSatisfiedBy(parsedValue);
	}
	
}
