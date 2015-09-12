package formfiller.entities.formComponent;

import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.answerFormat.AnswerFormat;

public class FormComponent {		
	public String id = "";
	public boolean requiresAnswer;
	public Question question;
	public Answer answer;
	public AnswerFormat format;
}
