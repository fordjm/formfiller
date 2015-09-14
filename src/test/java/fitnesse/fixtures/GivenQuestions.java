package fitnesse.fixtures;

import formfiller.FormFillerContext;
import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.formComponent.FormComponent;

public class GivenQuestions {
	public String id;
	public String questionContent;
	public boolean required;
	public String answerContent;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setQuestionContent(String qContent) {
		this.questionContent = qContent;
	}
	
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	
	public void execute() {
		FormComponent component = new FormComponent();
		component.id = id;
		component.question = makeQuestion();
		component.requiresAnswer = required;
		component.answer = makeAnswer();
		FormFillerContext.formComponentGateway.save(component);
	}

	private Question makeQuestion() {
		Question result = new Question();
		result.id = id;
		result.content = questionContent;		
		return result;
	}

	private Answer makeAnswer() {
		if (answerContent.length() == 0) return Answer.NONE;
		
		Answer result = new Answer();		
		result.id = 0;
		result.content = answerContent;
		return result;
	}
}
