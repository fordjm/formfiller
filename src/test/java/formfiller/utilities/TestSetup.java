package formfiller.utilities;

import formfiller.ExecutedUseCases;
import formfiller.FormFillerContext;
import formfiller.delivery.View;
import formfiller.delivery.presenter.FormComponentPresenter;
import formfiller.delivery.presenter.ResponsePresenter;
import formfiller.delivery.ui.consoleUi.ConsoleView;
import formfiller.delivery.viewModel.PresentableResponseViewModel;
import formfiller.entities.Answer;
import formfiller.entities.FormComponent;
import formfiller.entities.Question;
import formfiller.gateways.InMemoryFormComponentState;
import formfiller.gateways.InMemoryFormComponentGateway;

// TODO:  Credit CleanCoders JCS TestSetup
public class TestSetup {
	
	public static void setupContext(){
		View consoleView = new ConsoleView();
		
		FormFillerContext.formComponentState = new InMemoryFormComponentState();
		FormFillerContext.formComponentGateway = new InMemoryFormComponentGateway();
		FormFillerContext.executedUseCases = new ExecutedUseCases();
		FormFillerContext.answerPresenter = makeResponsePresenter(consoleView);
		FormFillerContext.responsePresenter = makeResponsePresenter(consoleView);
		FormFillerContext.formComponentPresenter = makeFormComponentPresenter(consoleView);
		FormFillerContext.questionPresenter = makeResponsePresenter(consoleView);
	}

	private static ResponsePresenter makeResponsePresenter(View view) {
		return new ResponsePresenter(makePresentableResponseViewModel(view));
	}

	private static PresentableResponseViewModel makePresentableResponseViewModel(View view) {
		PresentableResponseViewModel result = new PresentableResponseViewModel();
		result.addObserver(view);
		return result;
	}

	private static FormComponentPresenter makeFormComponentPresenter(View view) {
		return new FormComponentPresenter(makePresentableResponseViewModel(view));
	}
	
	public static void setupSampleFormComponents(){
		setupContext();
		FormFillerContext.formComponentGateway.save(
				makeFormComponent(
						makeQuestion("name", "What is your name?", false)));
		FormFillerContext.formComponentGateway.save(
				makeFormComponent(
						makeQuestion("birthDate", "What is your birth date?", false), 
						makeAnswer("November 12, 1955")));
		FormFillerContext.formComponentGateway.save(
				makeFormComponent(
						makeQuestion("age", "What is your age?", true)));
	}
	
	private static FormComponent makeFormComponent(Question question){		
		return makeFormComponent(question, Answer.NONE);
	}
	
	private static FormComponent makeFormComponent(Question question, Answer answer){
		FormComponent result = new FormComponent();
		result.id = question.getId();
		result.question = question;
		result.answer = answer;		
		return result;
	}
	
	private static Question makeQuestion(String id, String content, boolean isRequired){
		Question result = new Question(id, content);
		result.setResponseRequired(isRequired);
		return result;
	}
	
	private static Answer makeAnswer(String content){
		Answer result = new Answer();
		result.content = content;
		return result;
	}
}