package formfiller.entities.formComponent;

import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.format.Format;
import formfiller.entities.format.Unstructured;
import formfiller.usecases.addAnswer.AnswerValidator;

public class FormComponent {		
	public String id = "";
	public boolean requiresAnswer = false;
	public Question question = new Question();
	public Answer answer = Answer.NONE;
	public Format format = new Unstructured();
	public AnswerValidator validator = new AnswerValidator();
}
