package formfiller.persistence;

import java.util.HashSet;
import java.util.Set;

import formfiller.entities.NullPrompt;
import formfiller.entities.Prompt;
import formfiller.entities.Response;
import formfiller.entities.ResponseConstraint;
import formfiller.entities.ResponseImpl;

public class FormWidget {

	private static Prompt prompt = new NullPrompt();
	private static Response response = new ResponseImpl(-1, "");
	private static Set<ResponseConstraint> constraints = new HashSet<ResponseConstraint>();

	public static Prompt getPrompt() {
		return prompt;
	}

	public static Response getResponse() {
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

	public static void setResponse(Response content) {
		if (content.content() == null)
			clearResponse();
		else
			response = content;
	}

	public static void clearResponse() {
		response = new ResponseImpl(-1, "");
	}

	public static void clear() {
		clearPrompt();
		clearResponse();
	}

	public static Set<ResponseConstraint> constraints() {
		return constraints;
	}
}
