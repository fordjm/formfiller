package formfiller.utilities;
import java.util.Stack;

import formfiller.ApplicationContext;
import formfiller.delivery.presenter.AnswerPresenter;
import formfiller.delivery.presenter.FailedUseCasePresenter;
import formfiller.delivery.presenter.FormComponentPresenter;
import formfiller.delivery.presenter.QuestionPresenter;
import formfiller.entities.AnswerImpl;
import formfiller.entities.ExecutedUseCase;
import formfiller.entities.FormComponent;
import formfiller.entities.Question;
import formfiller.gateways.InMemoryFormComponentGateway;

// TODO:  Credit CleanCoders JCS TestSetup
public class TestSetup {
	
	public static void setupContext(){
		ApplicationContext.formComponentGateway = new InMemoryFormComponentGateway();
		ApplicationContext.executedUseCases = new Stack<ExecutedUseCase>();
		ApplicationContext.answerPresenter = new AnswerPresenter();
		ApplicationContext.failedUseCasePresenter = new FailedUseCasePresenter();
		ApplicationContext.formComponentPresenter = new FormComponentPresenter();
		ApplicationContext.questionPresenter = new QuestionPresenter();
	}
	
	public static void setupSampleFormComponents(){
		setupContext();
		ApplicationContext.formComponentGateway.save(
				makeFormComponent("name", "What is your name?", false));
		ApplicationContext.formComponentGateway.save(
				makeFormComponent("age", "What is your age?", true));
		ApplicationContext.formComponentGateway.save(
				makeFormComponent("birthDate", "What is your birth date?", false));
	}
	
	private static FormComponent makeFormComponent(String id, String content, boolean isRequired){
		FormComponent result = new FormComponent();
		result.id = id;
		result.question = makeQuestion(id, content, isRequired);
		result.answer = AnswerImpl.NONE;
		return result;
	}
	
	private static Question makeQuestion(String id, String content, boolean isRequired){
		Question result = new Question(id, content);
		result.setResponseRequired(isRequired);
		return result;
	}
}