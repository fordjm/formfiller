package formfiller.entities.formComponent;

import formfiller.entities.Answer;
import formfiller.entities.Question;

public class FormComponent {		
	public String id = "";
	public boolean requiresAnswer;
	public Question question;
	public Answer answer;
}
