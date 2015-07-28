package formfiller.persistence;

import java.util.HashMap;
import java.util.Map;

import formfiller.entities.NullPrompt;
import formfiller.entities.NullResponse;
import formfiller.entities.Prompt;
import formfiller.entities.Response;
import formfiller.entities.Constrainable;
import formfiller.utilities.ConstraintName;

public class FormWidget {

	private static Prompt prompt = new NullPrompt();
	private static Response<?> response = new NullResponse();
	private static Map<ConstraintName, Constrainable<?>> constraints = 
			new HashMap<ConstraintName, Constrainable<?>>();

	public static Prompt getPrompt() {
		return prompt;
	}

	public static Response<?> getResponse() {
		return response;
	}

	public static void setPrompt(Prompt prompt) {
		if (prompt == null)
			FormWidget.clearPrompt();
		else
			FormWidget.prompt = prompt;
	}

	private static void clearPrompt() {
		prompt = new NullPrompt();
	}
	
	private static void clearConstraints() {
		constraints = new HashMap<ConstraintName, Constrainable<?>>();
	}

	public static void setResponse(Response<?> content) {
		if (content == null)
			clearResponse();
		else
			response = content;
	}

	public static void clear() {
		clearPrompt();
		clearConstraints();
		clearResponse();
	}

	public static void clearResponse() {
		response = new NullResponse();
	}

	public static Map<ConstraintName, Constrainable<?>> constraints() {
		return constraints;
	}

	public static void addConstraint(ConstraintName constraintName, Constrainable<?> constraint) {
		constraints.put(constraintName, constraint);
	}
}
