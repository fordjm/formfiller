package formfiller.persistence;

import java.util.HashMap;
import java.util.Map;

import formfiller.entities.NullPrompt;
import formfiller.entities.NullResponse;
import formfiller.entities.Prompt;
import formfiller.entities.Response;
import formfiller.enums.Cardinality;
import formfiller.enums.ConstraintName;
import formfiller.entities.Constrainable;

public class FormWidget {

	private static Prompt prompt = new NullPrompt();
	private static Response<?> response = new NullResponse();
	private static Map<ConstraintName, Constrainable<?>> constraints = 
			new HashMap<ConstraintName, Constrainable<?>>();
	private static Cardinality responseCardinality = Cardinality.SINGLE;

	public static Cardinality getCardinality() {
		return responseCardinality;
	}

	public static Prompt getPrompt() {
		return prompt;
	}

	public static Response<?> getResponse() {
		return response;
	}
	
	public static boolean hasPrompt(){
		return !(prompt instanceof NullPrompt);
	}
	
	public static void setCardinality(Cardinality c){
		responseCardinality = c;
	}

	public static void addPrompt(Prompt prompt) {
		if (prompt == null) return;
		FormWidget.prompt = prompt;
	}

	public static void addResponse(Response<?> response) {
		if (!canSetResponse(response)) return;
		FormWidget.response = response;
	}
	
	private static boolean canSetResponse(Response<?> response){
		return widgetHasPrompt() && response != null;
	}
	
	private static boolean widgetHasPrompt(){
		return !(prompt instanceof NullPrompt);
	}

	public static void clear() {
		clearPrompt();
		clearConstraints();
		clearResponse();
	}

	private static void clearPrompt() {
		prompt = new NullPrompt();
	}
	
	private static void clearConstraints() {
		constraints = new HashMap<ConstraintName, Constrainable<?>>();
	}

	public static void clearResponse() {
		response = new NullResponse();
	}

	public static Map<ConstraintName, Constrainable<?>> getConstraints() {
		return constraints;
	}

	public static void addConstraint(ConstraintName constraintName, Constrainable<?> constraint) {
		constraints.put(constraintName, constraint);
	}
}
