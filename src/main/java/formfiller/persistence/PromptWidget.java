package formfiller.persistence;

import formfiller.entities.PromptFunctions;
import formfiller.entities.NullPrompt;

public class PromptWidget {

	private static PromptFunctions prompt = new NullPrompt();
	private static String response = "";

	public static PromptFunctions getPrompt() {
		return prompt;
	}

	public static String getResponse() {
		return response;
	}

	public static void setPrompt(PromptFunctions prompt) {
		if (prompt == null)
			clearPrompt();
		else
			PromptWidget.prompt = prompt;
	}

	private static void clearPrompt() {
		PromptWidget.prompt = new NullPrompt();
	}

	public static void setResponse(String content) {
		if (content == null || content.length() == 0)
			clearResponse();
		else
			PromptWidget.response = content;
	}

	public static void clearResponse() {
		PromptWidget.response = "";
	}

	public static void clear() {
		clearPrompt();
		clearResponse();
	}
}
