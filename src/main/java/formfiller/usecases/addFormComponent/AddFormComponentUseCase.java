package formfiller.usecases.addFormComponent;

import java.util.ArrayList;
import java.util.Collection;

import formfiller.FormFillerContext;
import formfiller.entities.Question;
import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.constrainable.AnswerType;
import formfiller.entities.constrainable.Constrainable;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.AddUnstructuredFormComponentRequest;
import formfiller.request.models.Request;
import formfiller.usecases.addAnswer.AnswerValidator;
import formfiller.usecases.undoable.UndoableUseCase;

public abstract class AddFormComponentUseCase implements UndoableUseCase {
	
	protected abstract AnswerFormat makeAnswerFormat(int minAnswers, int maxAnswers);
	
	public void execute(Request request) {
		if (request == null) return;
		
		AddFormComponentRequest castRequest = 
				(AddFormComponentRequest) request;
		
		FormComponent newComponent = makeNewFormComponent(castRequest.questionId, castRequest.questionContent);
		newComponent.format = makeAnswerFormat(0, 1);
		newComponent.validator = new AnswerValidator(makeAnswerConstraints(castRequest));
		
		FormFillerContext.formComponentGateway.save(newComponent);
	}

	private FormComponent makeNewFormComponent(String questionId, String questionContent) {
		FormComponent result = new FormComponent();
		result.id = questionId;
		result.question = makeNewQuestion(questionId, questionContent);
		return result;
	}

	private Collection<Constrainable> makeAnswerConstraints(AddFormComponentRequest castRequest) {
		Collection<Constrainable> result = new ArrayList<Constrainable>();
		result.add(new AnswerType(castRequest.answerType));
		return result;
	}

	private Question makeNewQuestion(String questionId, String questionContent) {
		Question result = new Question();
		result.id = questionId;
		result.content = questionContent;
		return result;
	}

	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
