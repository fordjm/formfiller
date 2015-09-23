package formfiller.entities.formComponent;

import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.answerFormat.Unstructured;
import formfiller.usecases.addAnswer.AnswerValidator;

public class FormComponent {		
	public String id = "";
	public boolean requiresAnswer = false;
	public Question question = new Question();
	public Answer answer = Answer.NONE;
	public AnswerFormat format = new Unstructured();
	public AnswerValidator validator = new AnswerValidator();
}
