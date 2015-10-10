package formfiller.entities.formComponent;

import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.entities.Question;
import formfiller.entities.QuestionImpl;
import formfiller.entities.format.Format;
import formfiller.entities.format.Unstructured;
import formfiller.usecases.addAnswer.AnswerValidator;

public class FormComponent {		
	public String id = "";
	public boolean requiresAnswer = false;
	public Question question = new QuestionImpl();
	public Answer answer = AnswerImpl.NONE;
	public Format format = new Unstructured();
	public AnswerValidator validator = new AnswerValidator();
}
