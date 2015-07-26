package formfiller.persistence;

import formfiller.entities.IPrompt;
import formfiller.entities.NullPrompt;

public class PromptWidget {

	private static IPrompt prompt = new NullPrompt();
	private static String response = "";

	public static IPrompt getPrompt() {
		return prompt;
	}

	public static String getResponse() {
		return response;
	}

	public static void setPrompt(IPrompt prompt) {
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
