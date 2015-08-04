package formfiller.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import formfiller.entities.NullPrompt;
import formfiller.entities.NullResponse;
import formfiller.entities.Prompt;
import formfiller.entities.Response;
import formfiller.entities.ResponseImpl;
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
			throw new IllegalStateException("Previous question requires a response!");
		else if (prompt == null) 
			throw new IllegalArgumentException("Cannot add nulls to FormWidget!");
		else
			FormWidget.prompt = prompt;
	}

	// TODO:  It appears this belongs in AddResponseTransaction...
	public static void addResponse(Response<?> response) {
		if (!hasPrompt())
			throw new IllegalStateException(
					"Must have a question before adding a response!");
		else if (!hasAValidResponse(response))
			throw new IllegalArgumentException("Response is not valid!");
		else if (!hasRoomForResponse())
			throw new IllegalStateException("This question only takes one response!");
		else
			addResponseToWidget(response);
	}
	
	// TODO:  And this belongs here in the widget.
	private static void addResponseToWidget(Response<?> response){
		if (responseCardinality == Cardinality.SINGLE)
			FormWidget.response = response;
		else if (FormWidget.response.getContent() instanceof List){
			List<Response<?>> content = (List<Response<?>>) FormWidget.response.getContent();
			content.add(response);
		}
		else{
			List<Response<?>> content = new ArrayList<Response<?>>();
			content.add(response);
			Response<?> toAdd = new ResponseImpl(0, content);
			FormWidget.response = toAdd;
		}
	}
	
	private static boolean hasRoomForResponse() throws IllegalStateException {
		if (!hasResponse()) return true;
		return responseCardinality == Cardinality.MULTI;
	}
	
	private static boolean hasResponse(){
		return !isANullResponse(response);
	}
	
	private static boolean isANullResponse(Response<?> response){
		return (response instanceof NullResponse);
	}
	
	private static boolean hasAValidResponse(Response<?> response){
		return response != null && 
				!isANullResponse(response) && 
				response.getContent() != null;
	}

	public static void clear() {
		clearPrompt();
		clearRequired();
		clearCardinality();
		clearConstraints();
		clearResponse();
	}

	private static void clearPrompt() {
		prompt = new NullPrompt();
	}

	private static void clearRequired() {
		responseRequired = false;
	}

	private static void clearCardinality() {
		responseCardinality = Cardinality.SINGLE;
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
