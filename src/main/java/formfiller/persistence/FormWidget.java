package formfiller.persistence;

import java.util.HashMap;
import java.util.Map;

import formfiller.entities.NullPrompt;
import formfiller.entities.NullResponse;
import formfiller.entities.Prompt;
import formfiller.entities.Response;
import formfiller.enums.Cardinality;
import formfiller.enums.ContentConstraint;
import formfiller.entities.Constrainable;

public class FormWidget {

	private static Prompt prompt = new NullPrompt();
	private static Response<?> response = new NullResponse();
	private static Map<ContentConstraint, Constrainable<?>> contentConstraints = 
			new HashMap<ContentConstraint, Constrainable<?>>();
	private static Cardinality responseCardinality = Cardinality.SINGLE;
	private static boolean responseRequired = false;

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

	public static void addPrompt(Prompt prompt) throws IllegalStateException, IllegalArgumentException {
		if (responseRequired && response instanceof NullResponse)
			throw new IllegalStateException("Previous prompt requires a response!");
		if (prompt == null) 
			throw new IllegalArgumentException("Cannot add nulls to FormWidget!");
		FormWidget.prompt = prompt;
	}

	public static void addResponse(Response<?> response) {
		if (!canSetResponse(response)) return;
		FormWidget.response = response;
	}
	
	private static boolean canSetResponse(Response<?> response){
		return widgetHasPrompt() && responseIsValid(response);
	}
	
	private static boolean responseIsValid(Response<?> response){
		return response != null && !(response instanceof NullResponse);
	}
	
	private static boolean widgetHasPrompt(){
		return !(prompt instanceof NullPrompt);
	}

	public static void clear() {
		clearPrompt();
		clearRequired();
		clearConstraints();
		clearResponse();
	}

	private static void clearPrompt() {
		prompt = new NullPrompt();
	}

	private static void clearRequired() {
		responseRequired = false;
	}
	
	private static void clearConstraints() {
		contentConstraints = new HashMap<ContentConstraint, Constrainable<?>>();
	}

	public static void clearResponse() {
		response = new NullResponse();
	}

	public static Map<ContentConstraint, Constrainable<?>> getConstraints() {
		return contentConstraints;
	}

	public static void addConstraint(ContentConstraint constraintName, Constrainable<?> constraint) {
		contentConstraints.put(constraintName, constraint);
	}

	public static boolean isResponseRequired() {
		return responseRequired;
	}

	public static void setRequired(boolean responseRequired) {
		FormWidget.responseRequired = responseRequired;
	}
}
