package formfiller.deprecated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import formfiller.entities.NoQuestion;
import formfiller.entities.Prompt;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.enums.Cardinality;
import formfiller.enums.ContentConstraint;
import formfiller.entities.Constraint;

public class FormWidget {
	private static Prompt prompt = new NoQuestion();
	private static Answer answer = AnswerImpl.NONE;
	private static Map<ContentConstraint, Constraint> contentConstraints = 
			new HashMap<ContentConstraint, Constraint>();
	private static Cardinality answerCardinality = Cardinality.SINGLE;
	private static boolean answerRequired = false;

	public static Cardinality getCardinality() {
		return answerCardinality;
	}

	public static Prompt getPrompt() {
		return prompt;
	}
	
	public static int getNextAnswerId(){
		if (!hasAnswer()) 
			return 0;
		else if (answer.getContent() instanceof List){
			List<?> responses = (List<Answer>) answer.getContent();
			return responses.size();
		}
		return -1;
	}

	public static Answer getAnswer() {
		return answer;
	}
	
	public static boolean hasQuestion(){
		return !(prompt instanceof NoQuestion);
	}
	
	public static void setCardinality(Cardinality c){
		answerCardinality = c;
	}

	public static void addPrompt(Prompt prompt) throws IllegalStateException, IllegalArgumentException {
		if (answerRequired && answer.equals(AnswerImpl.NONE))
			throw new IllegalStateException("Previous question requires an answer!");
		else if (prompt == null) 
			throw new IllegalArgumentException("Cannot add nulls to FormWidget!");
		else
			FormWidget.prompt = prompt;
	}

	// TODO:  It appears this belongs in AddResponseTransaction...
	public static void addAnswer(Answer answer) {
		if (!hasQuestion())
			throw new IllegalStateException(
					"Must have a question before adding an answer!");
		else if (!isValidAnswer(answer))
			throw new IllegalArgumentException("Answer is not valid!");
		else if (!hasRoomForAnswer())
			throw new IllegalStateException("This question only takes one answer!");
		else
			addAnswerToWidget(answer);
	}
	
	// TODO:  And this belongs here in the widget.
	private static void addAnswerToWidget(Answer answer){
		if (answerCardinality == Cardinality.SINGLE)
			FormWidget.answer = answer;
		else if (FormWidget.answer.getContent() instanceof List){
			List<Answer> content = (List<Answer>) FormWidget.answer.getContent();
			content.add(answer);
		}
		else{
			List<Answer> content = new ArrayList<Answer>();
			content.add(answer);
			Answer toAdd = new AnswerImpl(0, content);
			FormWidget.answer = toAdd;
		}
	}
	
	private static boolean hasRoomForAnswer() throws IllegalStateException {
		if (!hasAnswer()) return true;
		return answerCardinality == Cardinality.MULTI;
	}
	
	public static boolean hasAnswer(){
		return !isANullAnswer(answer);
	}
	
	private static boolean isANullAnswer(Answer answer){
		return (answer.equals(AnswerImpl.NONE));
	}
	
	private static boolean isValidAnswer(Answer answer){
		return answer != null && 
				!isANullAnswer(answer) && 
				answer.getContent() != null;
	}

	public static void clear() {
		clearPrompt();
		clearRequired();
		clearCardinality();
		clearConstraints();
		clearAnswer();
	}

	private static void clearPrompt() {
		prompt = new NoQuestion();
	}

	private static void clearRequired() {
		answerRequired = false;
	}

	private static void clearCardinality() {
		answerCardinality = Cardinality.SINGLE;
	}
	
	private static void clearConstraints() {
		contentConstraints = new HashMap<ContentConstraint, Constraint>();
	}

	public static void clearAnswer() {
		answer = AnswerImpl.NONE;
	}

	public static Map<ContentConstraint, Constraint> getConstraints() {
		return contentConstraints;
	}

	public static void addConstraint(Constraint constraint) {
		contentConstraints.put(constraint.getName(), constraint);
	}

	public static boolean isAnswerRequired() {
		return answerRequired;
	}

	public static void setRequired(boolean answerRequired) {
		FormWidget.answerRequired = answerRequired;
	}
}
