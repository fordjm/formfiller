package formfiller;

import java.util.Stack;

import formfiller.boundaries.Presenter;
import formfiller.entities.AnswerState;
import formfiller.entities.ExecutedUseCase;
import formfiller.entities.QuestionState;
import formfiller.gateways.AnswerGateway;
import formfiller.gateways.InMemoryFormComponentGateway;
import formfiller.gateways.QuestionGateway;

// As presented in the Clean Coders Java Case Study codecast
// https://cleancoders.com/episode/case-study-episode-1/show
// Retrieved 2015-08-06
public class ApplicationContext {
	public static QuestionGateway questionGateway;
	public static AnswerGateway answerGateway;
	public static Presenter answerPresenter;
	public static Stack<ExecutedUseCase> executedUseCases;
	public static Presenter handleUnfoundControllerPresenter;
	public static Presenter navigationPresenter;
	public static Presenter questionPresenter;
	public static QuestionState currentQuestionState;
	public static AnswerState currentAnswerState;
	public static InMemoryFormComponentGateway formComponentGateway;
}
