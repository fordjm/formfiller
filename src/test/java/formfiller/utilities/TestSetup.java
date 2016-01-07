package formfiller.utilities;

import formfiller.ExecutedUseCases;

import java.util.ArrayList;
import java.util.List;

import formfiller.Context;
import formfiller.delivery.presenter.FormComponentPresenter;
import formfiller.delivery.presenter.NotificationPresenter;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.entities.Question;
import formfiller.entities.QuestionImpl;
import formfiller.entities.formComponent.FormComponent;
import formfiller.gateways.impl.InMemoryFormComponentGateway;
import formfiller.gateways.impl.InMemoryFormComponentState;
import formfiller.utilities.valueMatcher.CaseIgnoringStringMatcher;

// TODO:  Credit CleanCoders JCS TestSetup
public class TestSetup {
	
	public static void setupContext(){
		Context.stringMatcher = new CaseIgnoringStringMatcher();
		Context.formComponentState = new InMemoryFormComponentState();
		Context.formComponentGateway = new InMemoryFormComponentGateway();
		Context.executedUseCases = new ExecutedUseCases();
		Context.answerPresenter = makeResponsePresenter();
		Context.outcomePresenter = makeResponsePresenter();
		Context.formComponentPresenter = makeFormComponentPresenter();
		Context.questionPresenter = makeResponsePresenter();
	}

	private static NotificationPresenter makeResponsePresenter() {
		return new NotificationPresenter();
	}

	private static FormComponentPresenter makeFormComponentPresenter() {
		return new FormComponentPresenter();
	}
	
	public static void setupSampleFormComponents(){
		setupContext();
		FormComponent[] components = makeTestComponents();
		saveFormComponentsAtGateway(components);
	}
	
	private static FormComponent[] makeTestComponents() {
		List<FormComponent> result = new ArrayList<FormComponent>();
		result.add(makeFormComponent(true, 
				makeQuestion("name", "What is your name?")));
		result.add(makeFormComponent(false, 
						makeQuestion("birthDate", "What is your date of birth?")));
		result.add(makeFormComponent(false, 
				makeQuestion("age", "What is your age?")));
		result.add(makeFormComponent(false, 
				makeQuestion("race", "What is your race?")));
		result.add(makeFormComponent(false, 
				makeQuestion("gender", "What is your gender?")));
		result.add(makeFormComponent(false, 
				makeQuestion("city", "What city do you live in?")));
		result.add(makeFormComponent(false, 
				makeQuestion("referred_by", "Who referred you?")));
		result.add(makeFormComponent(false, 
				makeQuestion("purpose", "What is the purpose of your "
						+ "visit today?")));
		return result.toArray(new FormComponent[0]);
	}

	private static void saveFormComponentsAtGateway(FormComponent... components) {
		for (FormComponent component : components)
			Context.formComponentGateway.save(component);
	}
	
	private static FormComponent makeFormComponent(boolean requiresAnswer, 
			Question question){		
		return makeFormComponent(requiresAnswer, question, AnswerImpl.NONE);
	}
	
	private static FormComponent makeFormComponent(boolean requiresAnswer, 
			Question question, Answer answer){
		FormComponent result = new FormComponent();
		result.requiresAnswer = requiresAnswer;
		result.id = question.getId();
		result.question = question;
		result.answer = answer;		
		return result;
	}
	
	private static Question makeQuestion(String id, String content){
		Question result = new QuestionImpl(id, content);
		return result;
	}
	
	private static Answer makeAnswer(String content){
		Answer result = new AnswerImpl();
		result.setContent(content);
		return result;
	}
}